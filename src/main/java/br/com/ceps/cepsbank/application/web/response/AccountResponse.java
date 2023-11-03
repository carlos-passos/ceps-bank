package br.com.ceps.cepsbank.application.web.response;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record AccountResponse(
        AccountEventTypeResponse type,
        AccountEventResponse origin,
        AccountEventResponse destination
){}
