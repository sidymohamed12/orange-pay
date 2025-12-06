package orange.pay.orange_pay.core;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
// @Schema(description = "Structure standard de réponse d’erreur")
public class ApiError {

    // @Schema(description = "Code HTTP", example = "400")
    private int status;

    // @Schema(description = "Message d’erreur détaillé", example = "Solde
    // insuffisant")
    private String message;

    // @Schema(description = "Type ou catégorie d’erreur", example = "BAD_REQUEST")
    private String errorType;

    // @Schema(description = "Timestamp de l’erreur", example =
    // "2025-12-06T12:42:10Z")
    private String timestamp;

    // @Schema(description = "ID unique de la requête (utile pour debug)", example =
    // "REQ-8f2da91e")
    private String correlationId;
}
