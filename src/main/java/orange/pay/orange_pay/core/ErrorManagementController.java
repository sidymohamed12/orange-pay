package orange.pay.orange_pay.core;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import orange.pay.orange_pay.web.dto.RestResponse;

public class ErrorManagementController {

    protected static final String NO_ACCESS = "Accès refusé à cette ressource : ";
    protected static final String NO_AUTHENTIFICATE = "Utilisateur non authentifié : ";
    protected static final String INTERNAL_ERROR = "Erreur interne du serveur : ";
    protected static final String SERVICE_UNAVAILABLE = "Service dépendant indisponible : ";
    protected static final String BAD_REQUEST = "Données invalides ou contrainte violée : ";
    protected static final String TIME_OUT = "Le service n’a pas répondu à temps : ";

    // ✅ 200 - OK
    protected ResponseEntity<Map<String, Object>> okResponse(Object data, String type) {
        return new ResponseEntity<>(
                RestResponse.response(HttpStatus.OK, data, type),
                HttpStatus.OK);
    }

    // ✅ 200 - OK Paginate
    protected ResponseEntity<Map<String, Object>> okResponsePaginate(Page<?> page, String type) {
        if (page.isEmpty()) {
            return new ResponseEntity<>(
                    RestResponse.response(HttpStatus.NO_CONTENT, null, "Aucun élément trouvé"),
                    HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(
                RestResponse.responsePaginate(
                        HttpStatus.OK,
                        page.getContent(),
                        page.getNumber(),
                        page.getTotalPages(),
                        page.getTotalElements(),
                        page.isFirst(),
                        page.isLast(),
                        type),
                HttpStatus.OK);
    }

    // ✅ 201 - created
    protected ResponseEntity<Map<String, Object>> createdResponse(Object data, String type) {
        return new ResponseEntity<>(
                RestResponse.response(HttpStatus.CREATED, data, type),
                HttpStatus.CREATED);
    }

    // ✅ 204 - Aucun contenu
    protected ResponseEntity<Map<String, Object>> noContentResponse(String type) {
        return new ResponseEntity<>(
                RestResponse.response(HttpStatus.NO_CONTENT, null, type),
                HttpStatus.NO_CONTENT);
    }

    // ✅ 400 - Mauvaise requête (erreur de validation, paramètre invalide, etc.)
    protected ResponseEntity<Map<String, Object>> badRequestResponse(String type) {
        return new ResponseEntity<>(
                RestResponse.response(HttpStatus.BAD_REQUEST, null, type),
                HttpStatus.BAD_REQUEST);
    }

    // ✅ 401 - Non autorisé
    protected ResponseEntity<Map<String, Object>> unauthorizedResponse(String type) {
        return new ResponseEntity<>(
                RestResponse.response(HttpStatus.UNAUTHORIZED, null, type),
                HttpStatus.UNAUTHORIZED);
    }

    // ✅ 403 - Accès refusé
    protected ResponseEntity<Map<String, Object>> forbiddenResponse(String type) {
        return new ResponseEntity<>(
                RestResponse.response(HttpStatus.FORBIDDEN, null, type),
                HttpStatus.FORBIDDEN);
    }

    // ✅ 404 - Not found
    protected ResponseEntity<Map<String, Object>> notFoundResponse(String type) {
        return new ResponseEntity<>(
                RestResponse.response(HttpStatus.NOT_FOUND, null, type),
                HttpStatus.NOT_FOUND);
    }

    // ✅ 408 - Timeout
    protected ResponseEntity<Map<String, Object>> timeoutResponse(String type) {
        return new ResponseEntity<>(
                RestResponse.response(HttpStatus.REQUEST_TIMEOUT, null, type),
                HttpStatus.REQUEST_TIMEOUT);
    }

    // ✅ 409 - Conflit
    protected ResponseEntity<Map<String, Object>> conflictResponse(String type) {
        return new ResponseEntity<>(
                RestResponse.response(HttpStatus.CONFLICT, null, type),
                HttpStatus.CONFLICT);
    }

    // ✅ 500 - Erreur interne
    protected ResponseEntity<Map<String, Object>> internalErrorResponse(String type) {
        return new ResponseEntity<>(
                RestResponse.response(HttpStatus.INTERNAL_SERVER_ERROR, null, type),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // ✅ 503 - Service indisponible
    protected ResponseEntity<Map<String, Object>> serviceUnavailableResponse(String type) {
        return new ResponseEntity<>(
                RestResponse.response(HttpStatus.SERVICE_UNAVAILABLE, null, type),
                HttpStatus.SERVICE_UNAVAILABLE);
    }

}
