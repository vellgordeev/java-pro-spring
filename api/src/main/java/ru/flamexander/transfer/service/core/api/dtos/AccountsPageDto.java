package ru.flamexander.transfer.service.core.api.dtos;

//import io.swagger.v3.oas.annotations.media.Schema;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;

import java.util.List;

//@NoArgsConstructor
//@AllArgsConstructor
//@Data
//@Schema(description = "Информация о счетах клиента")
public class AccountsPageDto {
//    @Schema(description = "Список счетов клиента")
    private List<AccountDto> items;

    public List<AccountDto> getItems() {
        return items;
    }

    public void setItems(List<AccountDto> items) {
        this.items = items;
    }

    public AccountsPageDto() {
    }

    public AccountsPageDto(List<AccountDto> items) {
        this.items = items;
    }
}