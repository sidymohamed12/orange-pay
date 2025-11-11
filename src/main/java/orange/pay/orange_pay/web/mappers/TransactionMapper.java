package orange.pay.orange_pay.web.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import orange.pay.orange_pay.models.Transaction;
import orange.pay.orange_pay.web.dto.response.HistoriqueTransactionResponse;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    TransactionMapper INTANCE = Mappers.getMapper(TransactionMapper.class);

    @Mapping(target = "date", source = "createdAt")
    HistoriqueTransactionResponse toHistoriqueTransactionResponse(Transaction transaction);

}
