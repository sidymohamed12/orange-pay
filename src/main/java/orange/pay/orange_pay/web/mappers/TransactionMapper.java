package orange.pay.orange_pay.web.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import orange.pay.orange_pay.models.Transaction;
import orange.pay.orange_pay.web.dto.response.HistoriqueTransactionResponse;
import orange.pay.orange_pay.web.dto.response.TransactionOneResponse;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    TransactionMapper INTANCE = Mappers.getMapper(TransactionMapper.class);

    @Mapping(target = "date", source = "createdAt")
    HistoriqueTransactionResponse toHistoriqueTransactionResponse(Transaction transaction);

    @Mapping(target = "date", source = "createdAt")
    @Mapping(target = "nomDestinataire", ignore = true)
    TransactionOneResponse toTransactionOneResponse(Transaction transaction);

}
