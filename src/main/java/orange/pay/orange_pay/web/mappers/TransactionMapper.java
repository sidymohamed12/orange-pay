package orange.pay.orange_pay.web.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    TransactionMapper INTANCE = Mappers.getMapper(TransactionMapper.class);

}
