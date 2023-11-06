package br.com.ceps.cepsbank.domain.account.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    private AccountEventType type;
    private AccountEvent origin;
    private AccountEvent destination;
}
