package orange.pay.orange_pay.services;

import java.util.List;

import orange.pay.orange_pay.web.dto.response.HistoriqueTransactionResponse;

public interface ITransactionService {
    List<HistoriqueTransactionResponse> getHistoriqueTransaction();
}
