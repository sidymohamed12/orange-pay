package orange.pay.orange_pay.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import orange.pay.orange_pay.models.Transaction;

public interface ITransactionRepository extends JpaRepository<Transaction, Long> {

}
