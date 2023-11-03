package br.com.ceps.cepsbank.application.web.response;

import br.com.ceps.cepsbank.domain.account.model.AccountEvent;
import br.com.ceps.cepsbank.domain.account.model.AccountEventType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record AccountResponse(
        AccountEventTypeResponse type,
        AccountEventResponse origin,
        AccountEventResponse destination
){}
