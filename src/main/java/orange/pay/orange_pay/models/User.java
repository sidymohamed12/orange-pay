package orange.pay.orange_pay.models;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class User extends AbstractEntity {
    private String email;
    private String password;

}
