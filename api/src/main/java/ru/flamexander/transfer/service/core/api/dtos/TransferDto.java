package ru.flamexander.transfer.service.core.api.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TransferDto {
    private Long id;
    private String sourceAccount;
    private String targetAccount;
    private BigDecimal amount;
    private TransferStatus status;
    private LocalDateTime createdAt;
}
