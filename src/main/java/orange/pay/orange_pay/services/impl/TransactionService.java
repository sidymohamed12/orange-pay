package orange.pay.orange_pay.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import orange.pay.orange_pay.repository.ICompteRepository;
import orange.pay.orange_pay.repository.ITransactionRepository;
import orange.pay.orange_pay.services.ITransactionService;
import orange.pay.orange_pay.utils.exceptions.ResourceNotFound;
import orange.pay.orange_pay.web.dto.response.HistoriqueTransactionResponse;
import orange.pay.orange_pay.web.dto.response.TransactionOneResponse;
import orange.pay.orange_pay.web.mappers.TransactionMapper;

@Service
@RequiredArgsConstructor
public class TransactionService implements ITransactionService {
    private final ITransactionRepository transactionRepository;
    private final ICompteRepository compteRepository;
    private final TransactionMapper transactionMapper;

    @Override
    public List<HistoriqueTransactionResponse> getHistoriqueTransaction(String numero) {
        return transactionRepository.findAllBySource_Telephone(numero)
                .stream()
                .map(transactionMapper::toHistoriqueTransactionResponse)
                .toList();
    }

    @Override
    public TransactionOneResponse getTransactionById(@NonNull Long id) {
        return transactionRepository.findById(id)
                .map(transac -> {
                    TransactionOneResponse transaction = transactionMapper.toTransactionOneResponse(transac);
                    transaction.setNomDestinataire(getNomCompteByNumero(transac.getNumeroDestinataire()));
                    return transaction;
                })
                .orElseThrow(() -> new ResourceNotFound("Transaction not found with id " + id));
    }

    private String getNomCompteByNumero(@NonNull String numero) {
        return compteRepository.findByTelephone(numero)
                .map(cpt -> cpt.getNom() + " " + cpt.getPrenom())
                .orElseThrow(() -> new ResourceNotFound("Compte not found with numero " + numero));
    }
}
