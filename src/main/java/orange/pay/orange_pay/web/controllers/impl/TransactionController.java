package orange.pay.orange_pay.web.controllers.impl;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import orange.pay.orange_pay.core.ErrorManagementController;
import orange.pay.orange_pay.services.ITransactionService;
import orange.pay.orange_pay.web.controllers.ITransactionController;
import orange.pay.orange_pay.web.dto.response.HistoriqueTransactionResponse;

@RestController
@RequiredArgsConstructor
public class TransactionController extends ErrorManagementController implements ITransactionController {
    private final ITransactionService transactionService;

    @Override
    public ResponseEntity<Map<String, Object>> getAllHistoriqueTransaction() {
        try {
            List<HistoriqueTransactionResponse> transactions = transactionService.getHistoriqueTransaction();

            if (transactions == null || transactions.isEmpty()) {
                return noContentResponse("liste vide");
            }

            return okResponse(transactions, "HistoriqueTransactionResponse");

        } catch (Exception e) {
            return internalErrorResponse(e.getMessage());
        }
    }
}
