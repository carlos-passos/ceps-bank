package br.com.ceps.cepsbank.domain.account.service;

import br.com.ceps.cepsbank.domain.account.model.Account;
import br.com.ceps.cepsbank.domain.account.model.AccountEventType;
import br.com.ceps.cepsbank.infrastructure.adapters.account.AccountAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AccountService {

    @Autowired
    private AccountAdapter accountAdapter;

    public Account makeTransaction(Account account, int depositOrder) {

        if (account.getType() == AccountEventType.deposit) {
            if (depositOrder == 1) {
                return accountAdapter.get("account_creation");
            } else if (depositOrder == 2) {
                return accountAdapter.get("account_deposit");
            }
        }

        if (account.getType() == AccountEventType.withdraw) {
            var accountWithdraw = accountAdapter.get("account_withdraw");
            if (idAreTheSame(accountWithdraw.getOrigin().getId(), account.getOrigin().getId())) {
                return accountWithdraw;
            }
        }

        if (account.getType() == AccountEventType.transfer) {
            var accountTransfer = accountAdapter.get("account_transfer");
            if (idAreTheSame(accountTransfer.getOrigin().getId(), account.getOrigin().getId())) {
                return accountTransfer;
            }
        }

        return null;
    }

    public BigDecimal retrieveBalance(String id) {

        var account =  accountAdapter.get("account_deposit");

        if (!idAreTheSame(id, account.getDestination().getId())) {
            return BigDecimal.ZERO;
        }

        return account.getDestination().getBalance();
    }

    private boolean idAreTheSame(String id1, String id2) {
        if (id1 != null && id2 != null) {
            return id1.equalsIgnoreCase(id2);
        }
        return false;
    }
}
