package br.com.ceps.cepsbank.infrastructure.mappers;

import br.com.ceps.cepsbank.application.web.request.AccountRequest;
import br.com.ceps.cepsbank.application.web.response.AccountResponse;
import br.com.ceps.cepsbank.domain.account.model.Account;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccountMapper {

    AccountMapper MAPPER = Mappers.getMapper(AccountMapper.class);

    Account toDomain(AccountRequest request);
    AccountResponse toResponse(Account domain);

}
