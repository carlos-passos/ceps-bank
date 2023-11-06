package br.com.ceps.cepsbank.application.web.controllers;

import br.com.ceps.cepsbank.application.web.request.AccountEventRequest;
import br.com.ceps.cepsbank.application.web.request.AccountEventTypeRequest;
import br.com.ceps.cepsbank.domain.account.model.Account;
import br.com.ceps.cepsbank.domain.account.model.AccountEvent;
import br.com.ceps.cepsbank.domain.account.model.AccountEventType;
import br.com.ceps.cepsbank.domain.account.service.AccountService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.net.URI;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;

@ExtendWith({MockitoExtension.class})
class AccountControllerTest {

    @Mock
    private AccountService accountService;
    @InjectMocks
    private AccountController accountController;


    @Test
    void whenPostReset_thenSuccess_200_OK() throws Exception {

       ResponseEntity responseEntity = accountController.postReset();

       Assertions.assertEquals(
               ResponseEntity.ok().build().getStatusCode(),
               responseEntity.getStatusCode()
       );
    }

    @Test
    void whenGetBalance_thenNonExistAccount_404_0() {

        Long accountId = 1234L;
        doReturn(BigDecimal.ZERO).when(accountService).retrieveBalance(String.valueOf(accountId));

        ResponseEntity responseEntity = accountController.getBalance(accountId);

        Assertions.assertEquals(
                ResponseEntity.notFound().build().getStatusCode(),
                responseEntity.getStatusCode()
        );
    }

    @Test
    void whenGetBalance_thenExistAccount_200_20() {

        Long accountId = 100L;
        doReturn(new BigDecimal(20)).when(accountService).retrieveBalance(String.valueOf(accountId));

        ResponseEntity responseEntity = accountController.getBalance(accountId);

        Assertions.assertEquals(
                ResponseEntity.ok(new BigDecimal(20)).getStatusCode(),
                responseEntity.getStatusCode()
        );
    }

    @Test
    void whenPostEvent_thenCreateAccountInitialBalance_201() {

        int depositOrder = 1;
        Account account = Account.builder()
                .type(AccountEventType.deposit)
                .destination(AccountEvent.builder()
                        .id("100")
                        .balance(new BigDecimal(10))
                        .build())
                .build();

        doReturn(account).when(accountService).makeTransaction(any(), eq(depositOrder));

        ResponseEntity responseEntity = accountController.postEvent(
                AccountEventRequest.builder()
                        .type(AccountEventTypeRequest.deposit)
                        .destination("100")
                        .amount(new BigDecimal(10))
                        .build()
        );

        Assertions.assertEquals(
                ResponseEntity.created(URI.create("/event")).build().getStatusCode(),
                responseEntity.getStatusCode()
        );
    }

    @Test
    void whenPostEvent_thenDepositIntoExistingAccount_201() {

        int depositOrder = 1;
        Account account = Account.builder()
                .type(AccountEventType.deposit)
                .destination(AccountEvent.builder()
                        .id("100")
                        .balance(new BigDecimal(10))
                        .build())
                .build();

        doReturn(account).when(accountService).makeTransaction(any(), eq(depositOrder++));

        ResponseEntity responseEntity = accountController.postEvent(
                AccountEventRequest.builder()
                        .type(AccountEventTypeRequest.deposit)
                        .destination("100")
                        .amount(new BigDecimal(10))
                        .build()
        );

        Assertions.assertEquals(
                ResponseEntity.created(URI.create("/event")).build().getStatusCode(),
                responseEntity.getStatusCode()
        );
    }

    @Test
    void whenPostEvent_thenWithdrawNonExistingAccount_404_0() {

        int depositOrder = 1;

        doReturn(null).when(accountService).makeTransaction(any(), eq(depositOrder));

        ResponseEntity responseEntity = accountController.postEvent(
                AccountEventRequest.builder()
                        .type(AccountEventTypeRequest.withdraw)
                        .origin("200")
                        .amount(new BigDecimal(10))
                        .build()
        );

        Assertions.assertEquals(
                ResponseEntity.notFound().build().getStatusCode(),
                responseEntity.getStatusCode()
        );
    }

    @Test
    void whenPostEvent_thenWithdrawExistingAccount_201() {

        int depositOrder = 1;
        Account account = Account.builder()
                .type(AccountEventType.withdraw)
                .origin(AccountEvent.builder()
                        .id("100")
                        .balance(new BigDecimal(15))
                        .build())
                .build();

        doReturn(account).when(accountService).makeTransaction(any(), eq(depositOrder));

        ResponseEntity responseEntity = accountController.postEvent(
                AccountEventRequest.builder()
                        .type(AccountEventTypeRequest.withdraw)
                        .origin("100")
                        .amount(new BigDecimal(15))
                        .build()
        );

        Assertions.assertEquals(
                ResponseEntity.created(URI.create("/event")).build().getStatusCode(),
                responseEntity.getStatusCode()
        );
    }

    @Test
    void whenPostEvent_thenTransferExistingAccount_201() {

        int depositOrder = 1;
        Account account = Account.builder()
                .type(AccountEventType.transfer)
                .origin(AccountEvent.builder()
                        .id("100")
                        .balance(new BigDecimal(15))
                        .build())
                .destination(AccountEvent.builder()
                        .id("300")
                        .build())
                .build();

        doReturn(account).when(accountService).makeTransaction(any(), eq(depositOrder));

        ResponseEntity responseEntity = accountController.postEvent(
                AccountEventRequest.builder()
                        .type(AccountEventTypeRequest.transfer)
                        .origin("100")
                        .amount(new BigDecimal(15))
                        .destination("300")
                        .build()
        );

        Assertions.assertEquals(
                ResponseEntity.created(URI.create("/event")).build().getStatusCode(),
                responseEntity.getStatusCode()
        );
    }

    @Test
    void whenPostEvent_thenTransferNonExistingAccount_404_0() {

        int depositOrder = 1;

        doReturn(null).when(accountService).makeTransaction(any(), eq(depositOrder));

        ResponseEntity responseEntity = accountController.postEvent(
                AccountEventRequest.builder()
                        .type(AccountEventTypeRequest.transfer)
                        .origin("200")
                        .amount(new BigDecimal(15))
                        .destination("300")
                        .build()
        );

        Assertions.assertEquals(
                ResponseEntity.notFound().build().getStatusCode(),
                responseEntity.getStatusCode()
        );
    }

}