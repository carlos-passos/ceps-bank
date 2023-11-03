package br.com.ceps.cepsbank.application.web.controllers;

import br.com.ceps.cepsbank.application.web.request.AccountRequest;
import br.com.ceps.cepsbank.application.web.response.AccountResponse;
import br.com.ceps.cepsbank.domain.account.service.AccountEventService;
import br.com.ceps.cepsbank.infrastructure.mappers.AccountMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@CrossOrigin("*")
@RequestMapping("event")
@RestController
@RequiredArgsConstructor
public class AccountEventController {

    private final AccountEventService accountEventService;

    @PostMapping
    public ResponseEntity<AccountResponse> postEvent(@RequestBody AccountRequest request) {
        var account = accountEventService.makeTransaction(
                AccountMapper.MAPPER.toDomain(request)
        );
        var response = AccountMapper.MAPPER.toResponse(account);
        return ResponseEntity.created(URI.create("/ceps-bank/api/v1/event")).body(response);
    }
}
