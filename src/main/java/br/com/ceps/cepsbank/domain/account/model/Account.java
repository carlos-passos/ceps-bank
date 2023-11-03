package br.com.ceps.cepsbank.domain.account.model;

import lombok.Data;

@Data
public class Account {

    private AccountEventType type;
    private AccountEvent origin;
    private AccountEvent destination;
}
