package br.com.ceps.cepsbank.application.web.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record AccountEventResponse(
        String token,
        UUID id,
        String name,
        String cnpj,
        String email,
        String description){}
