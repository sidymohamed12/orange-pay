package orange.pay.orange_pay.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import orange.pay.orange_pay.models.enums.TypeTransaction;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Transaction extends AbstractEntity {
    private TypeTransaction typeTransaction;
    private Double montant;
    @ManyToOne
    private Compte source;
    @ManyToOne
    private Compte destinataire;
    private String ref;
}
