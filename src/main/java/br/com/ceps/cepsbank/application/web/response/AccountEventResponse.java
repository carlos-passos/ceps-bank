package br.com.ceps.cepsbank.application.web.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record AccountEventResponse(
        String id,
        BigDecimal balance){}
