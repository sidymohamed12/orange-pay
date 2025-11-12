package orange.pay.orange_pay.web.controllers.impl;

import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import orange.pay.orange_pay.core.ErrorManagementController;
import orange.pay.orange_pay.services.ITransactionService;
import orange.pay.orange_pay.utils.exceptions.ResourceNotFound;
import orange.pay.orange_pay.utils.exceptions.SoldeInsufisant;
import orange.pay.orange_pay.web.controllers.ITransactionController;
import orange.pay.orange_pay.web.dto.request.TransactionRequest;

@RestController
@RequiredArgsConstructor
public class TransactionController extends ErrorManagementController implements ITransactionController {
    private final ITransactionService transactionService;

    @Override
    public ResponseEntity<Map<String, Object>> getTransactionById(Long id) {
        try {

            var transaction = transactionService.findById(id);

            return okResponse(transaction, "TransactionOneResponse");

        } catch (ResourceNotFound e) {
            return notFoundResponse("Aucune transaction trouvée avec l'id " + id);

        } catch (Exception e) {
            return internalErrorResponse(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<Map<String, Object>> createTransaction(TransactionRequest transactionRequest) {
        try {

            var transaction = transactionService.createTransaction(transactionRequest);

            return createdResponse(transaction, "HistoriqueTransactionResponse");

        } catch (SoldeInsufisant | DataIntegrityViolationException e) {
            return badRequestResponse(BAD_REQUEST + e.getMessage());

        } catch (Exception e) {
            return internalErrorResponse(e.getMessage());
        }
    }
}
