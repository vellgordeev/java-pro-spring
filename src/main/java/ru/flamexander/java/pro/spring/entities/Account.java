package ru.flamexander.java.pro.spring.entities;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Account {
    private String id;
    private String clientId;
    private Long balance;

    public Account(String clientId, Long balance) {
        this.clientId = clientId;
        this.balance = balance;
    }
}
