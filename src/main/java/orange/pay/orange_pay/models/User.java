package orange.pay.orange_pay.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@Table(name = "users")
@EqualsAndHashCode(callSuper = true)
public class User extends AbstractEntity {
    private String prenom;
    private String nom;
    private String addresse;
    private String email;
    private String password;
    private String nci;
}
