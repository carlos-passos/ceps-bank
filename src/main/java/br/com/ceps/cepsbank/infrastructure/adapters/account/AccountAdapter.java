package br.com.ceps.cepsbank.infrastructure.adapters.account;

import br.com.ceps.cepsbank.domain.account.model.Account;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
public class AccountAdapter {


    public Account get(String fileName) {
        InputStream file = getClass()
                .getClassLoader()
                .getResourceAsStream("data/"+fileName+".json");
        try {
            return new ObjectMapper().readValue(file, Account.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
