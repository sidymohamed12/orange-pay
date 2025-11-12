package orange.pay.orange_pay.web.controllers.impl;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import orange.pay.orange_pay.core.ErrorManagementController;
import orange.pay.orange_pay.services.ITransactionService;
import orange.pay.orange_pay.web.controllers.ICompteController;
import orange.pay.orange_pay.web.dto.response.HistoriqueTransactionResponse;

@RestController
@RequiredArgsConstructor
public class CompteController extends ErrorManagementController implements ICompteController {
    private final ITransactionService transactionService;

    @Override
    public ResponseEntity<Map<String, Object>> getHistoriqueTransactionCompte(String numero) {
        try {
            List<HistoriqueTransactionResponse> transactions = transactionService.getHistoriqueTransaction(numero);

            if (transactions == null || transactions.isEmpty()) {
                return noContentResponse("liste vide");
            }

            return okResponse(transactions, "HistoriqueTransactionResponse");

        } catch (Exception e) {
            return internalErrorResponse(e.getMessage());
        }
    }
}
