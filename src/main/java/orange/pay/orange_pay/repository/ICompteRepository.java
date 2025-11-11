package orange.pay.orange_pay.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import orange.pay.orange_pay.models.Compte;

public interface ICompteRepository extends JpaRepository<Compte, Long> {

}
