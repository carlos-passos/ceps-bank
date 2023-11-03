package br.com.ceps.cepsbank.domain.account.service;

import br.com.ceps.cepsbank.domain.account.model.Account;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AccountBalanceService {

    public Account retrieveBalance(Long id) {
        return new Account();
    }
}
