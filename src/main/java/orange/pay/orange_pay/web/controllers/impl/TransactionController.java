package orange.pay.orange_pay.web.controllers.impl;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import orange.pay.orange_pay.core.ErrorManagementController;
import orange.pay.orange_pay.services.ITransactionService;
import orange.pay.orange_pay.web.controllers.ITransactionController;
import orange.pay.orange_pay.web.dto.request.TransactionRequest;

@RestController
@RequiredArgsConstructor
public class TransactionController extends ErrorManagementController implements ITransactionController {
    private final ITransactionService transactionService;

    @Override
    public ResponseEntity<Map<String, Object>> getTransactionById(Long id) {
        var transaction = transactionService.findById(id);
        return okResponse(transaction, "TransactionOneResponse");
    }

    @Override
    public ResponseEntity<Map<String, Object>> createTransaction(TransactionRequest transactionRequest) {
        var transaction = transactionService.createTransaction(transactionRequest);
        return createdResponse(transaction, "HistoriqueTransactionResponse");
    }
}
