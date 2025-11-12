package orange.pay.orange_pay.services;

import java.util.List;

import orange.pay.orange_pay.core.ServiceCore;
import orange.pay.orange_pay.web.dto.request.TransactionRequest;
import orange.pay.orange_pay.web.dto.response.HistoriqueTransactionResponse;
import orange.pay.orange_pay.web.dto.response.TransactionOneResponse;

public interface ITransactionService extends ServiceCore<TransactionOneResponse> {
    List<HistoriqueTransactionResponse> getHistoriqueTransaction(String numero);

    HistoriqueTransactionResponse createTransaction(TransactionRequest transactionRequest);
}
