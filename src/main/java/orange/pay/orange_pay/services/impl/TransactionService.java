package orange.pay.orange_pay.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import orange.pay.orange_pay.repository.ITransactionRepository;
import orange.pay.orange_pay.services.ITransactionService;
import orange.pay.orange_pay.web.dto.response.HistoriqueTransactionResponse;
import orange.pay.orange_pay.web.mappers.TransactionMapper;

@Service
@RequiredArgsConstructor
public class TransactionService implements ITransactionService {
    private final ITransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;

    @Override
    public List<HistoriqueTransactionResponse> getHistoriqueTransaction(String numero) {
        return transactionRepository.findAllBySource_Telephone(numero)
                .stream()
                .map(transactionMapper::toHistoriqueTransactionResponse)
                .toList();
    }
}
