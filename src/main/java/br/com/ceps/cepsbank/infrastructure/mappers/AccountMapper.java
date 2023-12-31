package br.com.ceps.cepsbank.infrastructure.mappers;

import br.com.ceps.cepsbank.application.web.request.AccountEventRequest;
import br.com.ceps.cepsbank.application.web.response.AccountResponse;
import br.com.ceps.cepsbank.domain.account.model.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccountMapper {

    AccountMapper MAPPER = Mappers.getMapper(AccountMapper.class);

    @Mappings({
            @Mapping(target = "origin.id", source = "origin"),
            @Mapping(target = "destination.id", source = "destination"),
            @Mapping(target = "origin.balance", source = "amount"),
            @Mapping(target = "destination.balance", source = "amount")
    })
    Account toDomain(AccountEventRequest request);
    AccountResponse toResponse(Account domain);

}
