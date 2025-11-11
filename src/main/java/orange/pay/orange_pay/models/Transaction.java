package orange.pay.orange_pay.models;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import orange.pay.orange_pay.models.enums.TypeTransaction;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Transaction extends AbstractEntity {
    private TypeTransaction typeTransaction;
    private Double montant;
    private String numeroDestinataire;
}
