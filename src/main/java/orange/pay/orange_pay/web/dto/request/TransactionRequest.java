package orange.pay.orange_pay.web.dto.request;

import lombok.Getter;
import lombok.Setter;
import orange.pay.orange_pay.models.enums.TypeTransaction;

@Getter
@Setter
public class TransactionRequest {
    private TypeTransaction typeTransaction;
    private Double montant;
    private Long sourceId;
    private Long destinataireId;
}
