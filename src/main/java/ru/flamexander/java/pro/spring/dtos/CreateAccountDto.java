package ru.flamexander.java.pro.spring.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CreateAccountDto {
    private Long initialBalance;
}
