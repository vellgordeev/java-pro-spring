package ru.flamexander.transfer.service.core.api.dtos;

//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;

import java.math.BigDecimal;

//@NoArgsConstructor
//@AllArgsConstructor
//@Data
//@Schema(description = "Описание счета клиента")
public class AccountDto {
//    @Schema(description = "Внутренний ID счета", required = true)
    private Long id;

//    @Schema(description = "Номер счета", required = true, minLength = 16, maxLength = 16, example = "1234123412341234")
    private String accountNumber;

//    @Schema(description = "ID клиента владельца", required = true)
    private Long clientId;

//    @Schema(description = "Баланс на счету", required = true, example = "1000.99")
    private BigDecimal balance;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public AccountDto() {
    }

    public AccountDto(Long id, String accountNumber, Long clientId, BigDecimal balance) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.clientId = clientId;
        this.balance = balance;
    }
}