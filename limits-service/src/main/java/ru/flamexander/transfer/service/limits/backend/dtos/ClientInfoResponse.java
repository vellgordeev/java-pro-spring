package ru.flamexander.transfer.service.limits.backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientInfoResponse {
    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String status;
}
