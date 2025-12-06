package orange.pay.orange_pay.services.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import orange.pay.orange_pay.models.Compte;
import orange.pay.orange_pay.models.Transaction;
import orange.pay.orange_pay.repository.ITransactionRepository;
import orange.pay.orange_pay.services.ICompteService;
import orange.pay.orange_pay.services.ITransactionService;
import orange.pay.orange_pay.utils.exceptions.ResourceNotFound;
import orange.pay.orange_pay.web.dto.request.TransactionRequest;
import orange.pay.orange_pay.web.dto.response.HistoriqueTransactionResponse;
import orange.pay.orange_pay.web.dto.response.TransactionOneResponse;
import orange.pay.orange_pay.web.mappers.TransactionMapper;

@Service
@RequiredArgsConstructor
public class TransactionService implements ITransactionService {
    private final ICompteService compteService;
    private final ITransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;

    @Override
    public List<HistoriqueTransactionResponse> getHistoriqueTransaction(String numero) {
        return transactionRepository.findAllBySource_Telephone(numero)
                .stream()
                .map(transactionMapper::toHistoriqueTransactionResponse)
                .toList();
    }

    @Override
    public TransactionOneResponse findById(@NonNull Long id) {
        return transactionRepository.findById(id)
                .map(transactionMapper::toTransactionOneResponse)
                .orElseThrow(() -> new ResourceNotFound("Transaction not found with id " + id));
    }

    @Override
    @Transactional
    public HistoriqueTransactionResponse createTransaction(TransactionRequest transactionRequest) {
        Long sourceId = transactionRequest.getSourceId();
        Long destinataireId = transactionRequest.getDestinataireId();

        if (sourceId == null || destinataireId == null) {
            throw new IllegalArgumentException("Source and destinataire IDs must not be null");
        }

        Compte destinataire = compteService.findById(destinataireId);
        Compte source = compteService.findById(sourceId);

        compteService.verifySolde(source, transactionRequest.getMontant());
        compteService.updateCompteDestinataire(destinataire, transactionRequest.getMontant());
        compteService.updateCompteSource(source, transactionRequest.getMontant());

        Transaction transaction = transactionMapper.toTransactionEntity(transactionRequest);
        transaction.setSource(source);
        transaction.setDestinataire(destinataire);
        transaction.setRef(genererReferenceTransaction());

        return transactionMapper.toHistoriqueTransactionResponse(transactionRepository.save(transaction));
    }

    private String genererReferenceTransaction() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");

        String timestampPart = LocalDateTime.now().format(formatter);

        return "TRF-" + timestampPart;
    }

}
