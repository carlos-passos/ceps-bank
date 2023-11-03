package br.com.ceps.cepsbank.application.web.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountEventRequest {

    private Long id;
    private BigDecimal balance;
}
