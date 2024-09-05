package ru.flamexander.transfer.service.core.backend.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.flamexander.transfer.service.core.api.dtos.ExecuteTransferDtoRequest;
import ru.flamexander.transfer.service.core.api.dtos.ExecuteTransferDtoResult;
import ru.flamexander.transfer.service.core.api.dtos.TransferDto;
import ru.flamexander.transfer.service.core.api.dtos.TransferStatus;
import ru.flamexander.transfer.service.core.backend.entities.Account;
import ru.flamexander.transfer.service.core.backend.entities.Transfer;
import ru.flamexander.transfer.service.core.backend.errors.AppLogicException;
import ru.flamexander.transfer.service.core.backend.repositories.TransferRepository;
import ru.flamexander.transfer.service.core.backend.validators.ExecuteTransferValidator;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransferService {
    private final AccountsService accountsService;
    private final TransferRepository transferRepository;
    private final ExecuteTransferValidator executeTransferValidator;

    @Transactional
    public ExecuteTransferDtoResult transfer(ExecuteTransferDtoRequest request) {
        executeTransferValidator.validate(request);

        Account source = accountsService.getAccountByClientIdAndAccountNumber(request.getSourceClientId(), request.getSourceAccountNumber())
                .orElseThrow(() -> new AppLogicException("TRANSFER_SOURCE_ACCOUNT_NOT_FOUND", "Счет отправителя не найден"));

        Account target = accountsService.getAccountByClientIdAndAccountNumber(request.getTargetClientId(), request.getTargetAccountNumber())
                .orElseThrow(() -> new AppLogicException("TRANSFER_TARGET_ACCOUNT_NOT_FOUND", "Счет получателя не найден"));

        Transfer transfer = new Transfer();
        transfer.setSourceAccount(source);
        transfer.setTargetAccount(target);
        transfer.setSourceClientId(source.getClientId());
        transfer.setTargetClientId(target.getClientId());
        transfer.setAmount(request.getAmount());
        transfer.setStatus(TransferStatus.CREATED);
        transferRepository.save(transfer);

        try {
            transfer.setStatus(TransferStatus.PROCESSING);
            transferRepository.save(transfer);

            accountsService.updateAccountBalance(source, source.getBalance().subtract(request.getAmount()));
            accountsService.updateAccountBalance(target, target.getBalance().add(request.getAmount()));

            transfer.setStatus(TransferStatus.COMPLETED);
            transferRepository.save(transfer);
        } catch (Exception e) {
            transfer.setStatus(TransferStatus.ERROR);
            transferRepository.save(transfer);
            throw new AppLogicException("TRANSFER_FAILED", "Ошибка при выполнении перевода");
        }

        return new ExecuteTransferDtoResult(
                transfer.getId(),
                source.getAccountNumber(),
                target.getAccountNumber(),
                transfer.getAmount(),
                transfer.getStatus(),
                transfer.getCreatedAt(),
                transfer.getUpdatedAt()
        );
    }

    public List<TransferDto> getTransfersByClientId(Long clientId) {
        List<Transfer> transfers = transferRepository.findAllBySourceClientId(clientId);

        if (transfers.isEmpty()) {
            throw new AppLogicException("NO_TRANSFERS_FOUND", "У клиента с id = " + clientId + " нет переводов");
        }

        return transfers.stream()
                .map(this::convertToDto)
                .toList();
    }

    private TransferDto convertToDto(Transfer transfer) {
        return new TransferDto(
                transfer.getId(),
                transfer.getSourceAccount().getAccountNumber(),
                transfer.getTargetAccount().getAccountNumber(),
                transfer.getAmount(),
                transfer.getStatus(),
                transfer.getCreatedAt()
        );
    }
}
