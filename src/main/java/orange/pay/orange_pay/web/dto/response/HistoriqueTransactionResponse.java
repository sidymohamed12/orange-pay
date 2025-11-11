package orange.pay.orange_pay.web.dto.response;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HistoriqueTransactionResponse {
    private String typeTransaction;
    private Double montant;
    private String numeroDestinataire;
    private LocalDateTime date;
}
