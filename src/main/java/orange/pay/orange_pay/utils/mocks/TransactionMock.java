package orange.pay.orange_pay.utils.mocks;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import orange.pay.orange_pay.models.Compte;
import orange.pay.orange_pay.models.Transaction;
import orange.pay.orange_pay.models.enums.TypeTransaction;
import orange.pay.orange_pay.repository.ICompteRepository;
import orange.pay.orange_pay.repository.ITransactionRepository;

@Component
@RequiredArgsConstructor
@Slf4j
@Order(2)
public class TransactionMock implements CommandLineRunner {

    private final ITransactionRepository transactionRepository;
    private final ICompteRepository compteRepository;

    @Override
    public void run(String... args) throws Exception {

        if (transactionRepository.count() == 0) {

            List<Compte> comptes = compteRepository.findAll();

            if (comptes.isEmpty()) {
                log.info("⚠️ Aucun compte trouvé en base. Impossible de créer des transactions !");
                return;
            }

            List<Transaction> transactions = new ArrayList<>();
            int size = comptes.size();

            for (int i = 0; i < size; i++) {
                Compte source = comptes.get(i);
                Compte dest1 = comptes.get((i + 1) % size);
                Compte dest2 = comptes.get((i + 2) % size);

                // 🔹 1er transfert (source → dest1)
                Transaction t1 = new Transaction();
                t1.setTypeTransaction(TypeTransaction.TRANFERT);
                t1.setMontant(10000.0 + (i * 2000));
                t1.setSource(source);
                t1.setDestinataire(dest1);
                t1.setRef("TRF-" + UUID.randomUUID().toString().substring(0, 8));
                transactions.add(t1);

                // 🔹 2e transfert (dest2 → source)
                Transaction t2 = new Transaction();
                t2.setTypeTransaction(TypeTransaction.PAYEMENT);
                t2.setMontant(8000.0 + (i * 1500));
                t2.setSource(dest2);
                t2.setDestinataire(source);
                t2.setRef("TRF-" + UUID.randomUUID().toString().substring(0, 8));
                transactions.add(t2);
            }

            transactionRepository.saveAll(transactions);
            log.info("✅ " + transactions.size() + " transactions mock sauvegardées en base !");
        }
    }

}