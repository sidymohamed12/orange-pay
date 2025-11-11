package orange.pay.orange_pay.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import orange.pay.orange_pay.models.Transaction;

public interface ITransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findAllBySource_Telephone(String sourceTelephone);
    
}
