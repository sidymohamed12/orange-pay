package orange.pay.orange_pay.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import orange.pay.orange_pay.models.Compte;

public interface ICompteRepository extends JpaRepository<Compte, Long> {
    Optional<Compte> findByTelephone(String telephone);
}
