package ru.flamexander.java.pro.spring.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.flamexander.java.pro.spring.errors.ErrorDto;
import ru.flamexander.java.pro.spring.errors.ResourceNotFoundException;
import ru.flamexander.java.pro.spring.services.TransferService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/transfers")
public class TransferController {
    private final TransferService transferService;

    @PostMapping("/execute")
    public void executeTransfer() {
        transferService.transfer(2L, 3L);
    }
}
