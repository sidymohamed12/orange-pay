package orange.pay.orange_pay.web.dto.response;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import orange.pay.orange_pay.models.enums.TypeTransaction;

@Getter
@Setter
public class TransactionOneResponse {
    private Long id;
    private TypeTransaction typeTransaction;
    private Double montant;
    private String numeroDestinataire;
    private String nomDestinataire;
    private LocalDateTime date;
    private String ref;
}
