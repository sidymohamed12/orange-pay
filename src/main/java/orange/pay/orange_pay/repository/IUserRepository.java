package orange.pay.orange_pay.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import orange.pay.orange_pay.models.User;

public interface IUserRepository extends JpaRepository<User, Long> {

}
