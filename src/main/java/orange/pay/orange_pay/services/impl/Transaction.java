package orange.pay.orange_pay.services.impl;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import orange.pay.orange_pay.repository.ITransactionRepository;
import orange.pay.orange_pay.services.ITransactionService;

@Service
@RequiredArgsConstructor
public class Transaction implements ITransactionService {
    private final ITransactionRepository transactionRepository;
}
