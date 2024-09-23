package ru.flamexander.transfer.service.core.backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LimitsInfoResponseDto {
    private Long userId;
    private BigDecimal limitAmount;
}