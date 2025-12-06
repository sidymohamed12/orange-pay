package orange.pay.orange_pay.core;

import java.net.ConnectException;
import java.time.Instant;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;

import orange.pay.orange_pay.utils.exceptions.ResourceNotFound;
import orange.pay.orange_pay.utils.exceptions.SoldeInsufisant;

@RestControllerAdvice
public class GlobalExceptionHandler extends ErrorManagementController {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // ================================================================================================
    // 🔥 Utility: Build ApiError
    // ================================================================================================
    private ApiError buildApiError(HttpStatus status, String message, String errorType) {

        return ApiError.builder()
                .status(status.value())
                .message(message)
                .errorType(errorType)
                .timestamp(Instant.now().toString())
                .correlationId("REQ-" + UUID.randomUUID().toString().substring(0, 8))
                .build();
    }

    private ResponseEntity<ApiError> response(HttpStatus status, String message, String type) {

        ApiError error = buildApiError(status, message, type);

        // 🔥 Logging avancé
        log.error("""
                ================= ERROR INTERNAL LOG =================
                Correlation ID: {}
                Type: {}
                Message: {}
                =======================================
                """,
                error.getCorrelationId(), type, message);

        return new ResponseEntity<>(error, status);
    }

    private void logError(Exception ex) {
        log.error("================= log interne =================", ex);
    }

    // ===================================================================
    // HANDLERS
    // ===================================================================

    // 404 - ResourceNotFound
    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<ApiError> handleResourceNotFound(ResourceNotFound ex) {
        logError(ex);
        return response(HttpStatus.NOT_FOUND, ex.getMessage(), "NOT_FOUND");
    }

    // 400 - Solde insuffisant
    @ExceptionHandler(SoldeInsufisant.class)
    public ResponseEntity<ApiError> handleSoldeInsuffisant(SoldeInsufisant ex) {
        logError(ex);
        return response(HttpStatus.BAD_REQUEST, ex.getMessage(), "BAD_REQUEST");
    }

    // 400 - Violations d’intégrité (données invalides, contrainte DB)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiError> handleDataIntegrity(DataIntegrityViolationException ex) {
        logError(ex);
        return response(HttpStatus.BAD_REQUEST, BAD_REQUEST + ex.getMessage(), "DATA_INTEGRITY");
    }

    // 403 - AccessDenied
    @ExceptionHandler(HttpClientErrorException.Forbidden.class)
    public ResponseEntity<ApiError> handleAccessDenied(HttpClientErrorException.Forbidden ex) {
        logError(ex);
        return response(HttpStatus.FORBIDDEN, NO_ACCESS + ex.getMessage(), "FORBIDDEN");
    }

    // 401 - Authentication failed
    @ExceptionHandler(HttpClientErrorException.Unauthorized.class)
    public ResponseEntity<ApiError> handleBadCredentials(HttpClientErrorException.Unauthorized ex) {
        logError(ex);
        return response(HttpStatus.UNAUTHORIZED, NO_AUTHENTIFICATE + ex.getMessage(), "UNAUTHORIZED");
    }

    // 503 - Service indisponible (microservice down)
    @ExceptionHandler(ConnectException.class)
    public ResponseEntity<ApiError> handleServiceUnavailable(ConnectException ex) {
        logError(ex);
        return response(HttpStatus.SERVICE_UNAVAILABLE, SERVICE_UNAVAILABLE + ex.getMessage(), "SERVICE_UNAVAILABLE");
    }

    @ExceptionHandler(DataAccessResourceFailureException.class)
    public ResponseEntity<ApiError> handleDataAccessResourceFailure(DataAccessResourceFailureException ex) {
        logError(ex);
        return response(HttpStatus.SERVICE_UNAVAILABLE, SERVICE_UNAVAILABLE + ex.getMessage(), "SERVICE_UNAVAILABLE");
    }

    @ExceptionHandler(CannotCreateTransactionException.class)
    public ResponseEntity<ApiError> handleCannotCreateTransaction(CannotCreateTransactionException ex) {
        logError(ex);
        return response(HttpStatus.SERVICE_UNAVAILABLE, SERVICE_UNAVAILABLE + ex.getMessage(), "SERVICE_UNAVAILABLE");
    }

    // 408 - timeout
    @ExceptionHandler(ResourceAccessException.class)
    public ResponseEntity<ApiError> handleResourceAccess(ResourceAccessException ex) {
        logError(ex);
        return response(HttpStatus.REQUEST_TIMEOUT, TIME_OUT + ex.getMessage(), "REQUEST_TIMEOUT");
    }

    // 500 - Toutes les autres exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGlobalException(Exception ex) {
        logError(ex);
        return response(HttpStatus.INTERNAL_SERVER_ERROR, INTERNAL_ERROR + ex.getMessage(), "INTERNAL_ERROR");
    }
}