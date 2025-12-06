package orange.pay.orange_pay.core;

import java.net.ConnectException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;

import orange.pay.orange_pay.utils.exceptions.ResourceNotFound;
import orange.pay.orange_pay.utils.exceptions.SoldeInsufisant;

@RestControllerAdvice
public class GlobalExceptionHandler2 extends ErrorManagementController {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler2.class);

    private void logError(Exception message) {
        log.error("================= log interne =================", message);
    }

    // 404 - ResourceNotFound
    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<Map<String, Object>> handleResourceNotFound(ResourceNotFound ex) {
        logError(ex);
        return notFoundResponse(ex.getMessage());
    }

    // 400 - Solde insuffisant
    @ExceptionHandler(SoldeInsufisant.class)
    public ResponseEntity<Map<String, Object>> handleSoldeInsuffisant(SoldeInsufisant ex) {
        logError(ex);
        return badRequestResponse(ex.getMessage());
    }

    // 400 - Violations d’intégrité (données invalides, contrainte DB)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, Object>> handleDataIntegrity(DataIntegrityViolationException ex) {
        logError(ex);
        return badRequestResponse(BAD_REQUEST + ex.getMessage());
    }

    // 403 - AccessDenied
    @ExceptionHandler(HttpClientErrorException.Forbidden.class)
    public ResponseEntity<Map<String, Object>> handleAccessDenied(
            HttpClientErrorException.Forbidden ex) {
        logError(ex);
        return forbiddenResponse(NO_ACCESS + ex.getMessage());
    }

    // 403 - AccessDenied
    // @ExceptionHandler(org.springframework.security.access.AccessDeniedException.class)
    // public ResponseEntity<Map<String, Object>>
    // handleAccessDenied(org.springframework.security.access.AccessDeniedException
    // ex) {
    // return forbiddenResponse(NO_ACCESS + ex.getMessage());
    // }

    // 401 - Authentication
    // @ExceptionHandler(org.springframework.security.authentication.BadCredentialsException.class)
    // public ResponseEntity<Map<String, Object>>
    // handleBadCredentials(org.springframework.security.authentication.BadCredentialsException
    // ex) {
    // return unauthorizedResponse(NO_AUTHENTIFICATE + ex.getMessage());
    // }

    // 401 - Authentication
    @ExceptionHandler(HttpClientErrorException.Unauthorized.class)
    public ResponseEntity<Map<String, Object>> handleBadCredentials(
            HttpClientErrorException.Unauthorized ex) {
        logError(ex);
        return unauthorizedResponse(NO_AUTHENTIFICATE + ex.getMessage());
    }

    // 503 - Service indisponible (ex: microservice down)
    @ExceptionHandler(java.net.ConnectException.class)
    public ResponseEntity<Map<String, Object>> handleServiceUnavailable(ConnectException ex) {
        logError(ex);
        return serviceUnavailableResponse(SERVICE_UNAVAILABLE + ex.getMessage());
    }

    // 503 - Service indisponible
    @ExceptionHandler(DataAccessResourceFailureException.class)
    public ResponseEntity<Map<String, Object>> handleDataAccessResourceFailure(DataAccessResourceFailureException ex) {
        logError(ex);
        return serviceUnavailableResponse(SERVICE_UNAVAILABLE + ex.getMessage());
    }

    // 503 - Service indisponible
    @ExceptionHandler(CannotCreateTransactionException.class)
    public ResponseEntity<Map<String, Object>> handleCannotCreateTransaction(CannotCreateTransactionException ex) {
        logError(ex);
        return serviceUnavailableResponse(SERVICE_UNAVAILABLE + ex.getMessage());
    }

    // 408 - timeout
    @ExceptionHandler(ResourceAccessException.class)
    public ResponseEntity<Map<String, Object>> handleResourceAccess(ResourceAccessException ex) {
        logError(ex);
        return timeoutResponse(TIME_OUT + ex.getMessage());
    }

    // 500 - Toutes les autres exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGlobalException(Exception ex) {
        logError(ex);
        return internalErrorResponse(INTERNAL_ERROR + ex.getMessage());
    }

}