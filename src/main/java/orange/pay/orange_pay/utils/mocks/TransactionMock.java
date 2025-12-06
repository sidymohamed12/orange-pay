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
@Order(3)
public class TransactionMock implements CommandLineRunner {

    private final ITransactionRepository transactionRepository;
    private final ICompteRepository compteRepository;

    @Override
    public void run(String... args) throws Exception {

        if (transactionRepository.count() == 0) {

            List<Compte> comptes = compteRepository.findAll();

            if (comptes.isEmpty()) {
                System.out.println("⚠️ Aucun compte trouvé !");
                return;
            }

            List<Transaction> transactions = new ArrayList<>();
            int size = comptes.size();

            for (int i = 0; i < size; i++) {

                Compte source = comptes.get(i);
                Compte dest1 = comptes.get((i + 1) % size);

                // 🔹 transaction 1 : TRANSFERT
                Transaction t1 = new Transaction();
                t1.setTypeTransaction(TypeTransaction.TRANSFERT);
                t1.setMontant(15000.0 + (i * 5000));
                t1.setSource(source);
                t1.setDestinataire(dest1);
                t1.setRef("TRF-" + UUID.randomUUID().toString().substring(0, 8));

                transactions.add(t1);

                // 🔹 transaction 2 : PAYEMENT
                Transaction t2 = new Transaction();
                t2.setTypeTransaction(TypeTransaction.PAYEMENT);
                t2.setMontant(7000.0 + (i * 3000));
                t2.setSource(dest1);
                t2.setDestinataire(source);
                t2.setRef("PAY-" + UUID.randomUUID().toString().substring(0, 8));

                transactions.add(t2);
            }

            transactionRepository.saveAll(transactions);
            System.out.println("✅ " + transactions.size() + " transactions mock générées !");
        }
    }
}
