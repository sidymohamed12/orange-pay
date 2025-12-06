package orange.pay.orange_pay.web.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import orange.pay.orange_pay.models.enums.TypeTransaction;

@Getter
@Setter
public class TransactionRequest {
    @NotNull(message = "le type de transaction est obligatoire")
    private TypeTransaction typeTransaction;

    @NotNull(message = "le montant est obligatoire")
    @Min(value = 1, message = "la quantite doit etre superieur a 0")
    @Positive(message = "le montant doit etre positif")
    private Double montant;

    @NotNull(message = "l'id du compte source est obligatoire")
    private Long sourceId;

    @NotNull(message = "l'id du compte destinataire est obligatoire")
    private Long destinataireId;
}
