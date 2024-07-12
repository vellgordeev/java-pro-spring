package ru.flamexander.java.pro.spring.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AccountDto {
    private Long id;
    private Long clientId;
    private Long balance;
}