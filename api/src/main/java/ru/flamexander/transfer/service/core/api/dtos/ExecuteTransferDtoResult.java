package ru.flamexander.transfer.service.core.api.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Schema(description = "ДТО результат выполнения перевода")
public class ExecuteTransferDtoResult {
    private Long transferId;
    private String sourceAccountNumber;
    private String targetAccountNumber;
    private BigDecimal amount;
    private TransferStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
