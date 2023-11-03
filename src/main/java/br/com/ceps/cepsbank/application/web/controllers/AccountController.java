package br.com.ceps.cepsbank.application.web.controllers;

import br.com.ceps.cepsbank.application.web.request.AccountEventRequest;
import br.com.ceps.cepsbank.application.web.response.AccountResponse;
import br.com.ceps.cepsbank.domain.account.service.AccountBalanceService;
import br.com.ceps.cepsbank.domain.account.service.AccountEventService;
import br.com.ceps.cepsbank.infrastructure.mappers.AccountMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
public class AccountController {

    private final AccountBalanceService accountBalanceService;
    private final AccountEventService accountEventService;

    @PostMapping("/reset")
    public ResponseEntity<Void> postReset() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("balance")
    public ResponseEntity<AccountResponse> getBalance(@RequestParam(value = "account_id") Long accountId) {

        var response = AccountMapper.MAPPER.toResponse(
                accountBalanceService.retrieveBalance(accountId)
        );

        //if null return 404

        return ResponseEntity.ok(response);
    }

    @PostMapping("event")
    public ResponseEntity<AccountResponse> postEvent(@RequestBody AccountEventRequest request) {

        if (request.getOrigin() != null && request.getDestination() != null) {
            // throw illegal argument...
        }

        var account = accountEventService.makeTransaction(
                AccountMapper.MAPPER.toDomain(request)
        );
        var response = AccountMapper.MAPPER.toResponse(account);
        return ResponseEntity.created(URI.create("/ceps-bank/api/v1/event")).body(response);
    }
}
