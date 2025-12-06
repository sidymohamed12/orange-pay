package orange.pay.orange_pay.web.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import orange.pay.orange_pay.validation.annotations.TelephoneValid;

@Getter
@Setter
public class CompteRequest {
    @NotBlank(message = "le telephone est obligatoire")
    @TelephoneValid(message = "Numéro de téléphone invalide, ex : 77 123 45 67")
    private String telephone;

    @NotBlank(message = "le code secret est obligatoire")
    private String codeSecret;

    @NotBlank(message = "le type de compte est obligatoire")
    private String typeCompte;

    @NotNull(message = "l'id de l'utilisateur est obligatoire")
    private Long userId;
}
