package br.com.ceps.cepsbank.application.web.request;

import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountEventRequest {

    private AccountEventTypeRequest type;
    private String destination;
    private String origin;
    private BigDecimal amount;
}
