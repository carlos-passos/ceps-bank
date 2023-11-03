package br.com.ceps.cepsbank.application.web.controllers;

import br.com.ceps.cepsbank.application.web.response.AccountResponse;
import br.com.ceps.cepsbank.domain.account.service.AccountBalanceService;
import br.com.ceps.cepsbank.infrastructure.mappers.AccountMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RequestMapping("balance")
@RestController
@RequiredArgsConstructor
public class AccountBalanceController {

    private final AccountBalanceService accountBalanceService;

    @GetMapping
    public ResponseEntity<AccountResponse> getBalance(@RequestParam(value = "account_id") Long accountId) {

        var response = AccountMapper.MAPPER.toResponse(
                accountBalanceService.retrieveBalance(accountId)
        );

        //if null return 404

        return ResponseEntity.ok(response);
    }
}
