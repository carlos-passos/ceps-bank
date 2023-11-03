package br.com.ceps.cepsbank.application.web.request;

import br.com.ceps.cepsbank.domain.account.model.AccountEventType;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
public class AccountRequest {

    private AccountEventTypeRequest type;
    private AccountEventRequest origin;
    private AccountEventRequest destination;
}
