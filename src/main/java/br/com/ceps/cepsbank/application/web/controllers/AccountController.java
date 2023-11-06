package br.com.ceps.cepsbank.application.web.controllers;

import br.com.ceps.cepsbank.application.web.request.AccountEventRequest;
import br.com.ceps.cepsbank.domain.account.service.AccountService;
import br.com.ceps.cepsbank.infrastructure.mappers.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.net.URI;

@CrossOrigin("*")
@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    private int depositOrder = 1;

    @PostMapping("/reset")
    public ResponseEntity<String> postReset() {
        depositOrder = 1;
        return ResponseEntity.ok("OK");
    }

    @GetMapping("/balance")
    public ResponseEntity<BigDecimal> getBalance(@RequestParam(value = "account_id") Long accountId) {

        var response = accountService.retrieveBalance(String.valueOf(accountId));

        if (response == BigDecimal.ZERO) {
            return ResponseEntity.status(404).body(response);
        }

        return ResponseEntity.ok(response);
    }

    @PostMapping("/event")
    public ResponseEntity<?> postEvent(@RequestBody AccountEventRequest request) {

        var account = accountService.makeTransaction(
                AccountMapper.MAPPER.toDomain(request), depositOrder++
        );

        if (account == null) {
            return ResponseEntity.status(404).body(0);
        }

        var response = AccountMapper.MAPPER.toResponse(account);
        return ResponseEntity.created(URI.create("/event")).body(response);
    }
}
