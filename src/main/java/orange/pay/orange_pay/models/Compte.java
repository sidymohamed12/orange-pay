package orange.pay.orange_pay.models;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Compte extends AbstractEntity {
    private String prenom;
    private String nom;
    private String addresse;
    private String telephone;
    private String codeSecret;
    private Boolean actif;
    private Double solde;
}
