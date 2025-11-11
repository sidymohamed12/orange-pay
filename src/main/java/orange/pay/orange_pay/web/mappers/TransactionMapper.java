package orange.pay.orange_pay.web.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import orange.pay.orange_pay.models.Transaction;
import orange.pay.orange_pay.web.dto.request.TransactionRequest;
import orange.pay.orange_pay.web.dto.response.HistoriqueTransactionResponse;
import orange.pay.orange_pay.web.dto.response.TransactionOneResponse;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    TransactionMapper INTANCE = Mappers.getMapper(TransactionMapper.class);

    @Mapping(target = "numeroDestinataire", source = "destinataire.telephone")
    @Mapping(target = "date", source = "createdAt")
    HistoriqueTransactionResponse toHistoriqueTransactionResponse(Transaction transaction);

    @Mapping(target = "date", source = "createdAt")
    @Mapping(target = "nomDestinataire", source = "destinataire.nom")
    @Mapping(target = "numeroDestinataire", source = "destinataire.telephone")
    TransactionOneResponse toTransactionOneResponse(Transaction transaction);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "source", ignore = true)
    @Mapping(target = "destinataire", ignore = true)
    Transaction toTransactionEntity(TransactionRequest transactionRequest);

}
