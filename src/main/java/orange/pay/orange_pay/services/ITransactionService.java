package orange.pay.orange_pay.services;

import java.util.List;

import orange.pay.orange_pay.web.dto.request.TransactionRequest;
import orange.pay.orange_pay.web.dto.response.HistoriqueTransactionResponse;
import orange.pay.orange_pay.web.dto.response.TransactionOneResponse;

public interface ITransactionService {
    List<HistoriqueTransactionResponse> getHistoriqueTransaction(String numero);

    TransactionOneResponse getTransactionById(Long id);

    HistoriqueTransactionResponse createTransaction(TransactionRequest transactionRequest);
}
