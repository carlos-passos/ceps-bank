package br.com.ceps.cepsbank.domain.account.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountEvent {

    private Long id;
    private BigDecimal balance;
}
