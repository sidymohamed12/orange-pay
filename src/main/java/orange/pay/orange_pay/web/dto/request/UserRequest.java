package orange.pay.orange_pay.web.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import orange.pay.orange_pay.validation.annotations.NciValid;

@Getter
@Setter
public class UserRequest {
    @NotBlank(message = "le prenom est obligatoire")
    private String prenom;

    @NotBlank(message = "le nom est obligatoire")
    private String nom;

    @NotBlank(message = "l'addresse est obligatoire")
    private String addresse;

    @NotBlank(message = "l'email est obligatoire")
    private String email;

    @NotBlank(message = "le password est obligatoire")
    private String password;

    @NotBlank(message = "le nci est obligatoire")
    @NciValid(message = "NCI invalide")
    private String nci;
}
