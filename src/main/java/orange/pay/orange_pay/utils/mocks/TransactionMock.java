package orange.pay.orange_pay.utils.mocks;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import orange.pay.orange_pay.models.Transaction;
import orange.pay.orange_pay.models.enums.TypeTransaction;
import orange.pay.orange_pay.repository.ITransactionRepository;

@Component
@RequiredArgsConstructor
@Slf4j
public class TransactionMock implements CommandLineRunner {

    private final ITransactionRepository transactionRepository;

    @Override
    public void run(String... args) throws Exception {
        if (transactionRepository.count() == 0) {

            for (int i = 1; i <= 5; i++) {
                Transaction transaction = new Transaction();
                transaction.setTypeTransaction(i % 2 == 0 ? TypeTransaction.TRANFERT : TypeTransaction.PAYEMENT);
                transaction.setMontant(1000.0 * i);
                transaction.setNumeroSource("770000" + String.format("%03d", i));
                transaction.setNumeroDestinataire("780000" + String.format("%03d", i));
                transaction.setRef("REF-" + UUID.randomUUID().toString().substring(0, 8));

                transactionRepository.save(transaction);
            }
        }

        log.info("========= Transactions initialisées ======");

    }

}