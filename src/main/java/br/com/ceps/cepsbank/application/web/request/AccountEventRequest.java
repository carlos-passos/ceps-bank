package br.com.ceps.cepsbank.application.web.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountEventRequest {

    private AccountEventTypeRequest type;
    private String destination;
    private String origin;
    private BigDecimal balance;
}
