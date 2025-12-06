package orange.pay.orange_pay.utils.mocks;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import orange.pay.orange_pay.models.User;
import orange.pay.orange_pay.repository.IUserRepository;

@Component
@RequiredArgsConstructor
@Order(1)
public class UserMock implements CommandLineRunner {

    private final IUserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {

        if (userRepository.count() == 0) {

            User u1 = new User();
            u1.setPrenom("Sidy Mohamed");
            u1.setNom("Saizonou");
            u1.setAddresse("Dakar");
            u1.setEmail("sidy@example.com");
            u1.setPassword("pass1");
            u1.setNci("NCI001");

            User u2 = new User();
            u2.setPrenom("Said");
            u2.setNom("Saizonou");
            u2.setAddresse("Thiès");
            u2.setEmail("said@example.com");
            u2.setPassword("pass2");
            u2.setNci("NCI002");

            User u3 = new User();
            u3.setPrenom("Faycal");
            u3.setNom("Saizonou");
            u3.setAddresse("Saint-Louis");
            u3.setEmail("faycal@example.com");
            u3.setPassword("pass3");
            u3.setNci("NCI003");

            User u4 = new User();
            u4.setPrenom("Abou");
            u4.setNom("Bathily");
            u4.setAddresse("Kaolack");
            u4.setEmail("abou@example.com");
            u4.setPassword("pass4");
            u4.setNci("NCI004");

            User u5 = new User();
            u5.setPrenom("Mariama");
            u5.setNom("Diallo");
            u5.setAddresse("Ziguinchor");
            u5.setEmail("mariama@example.com");
            u5.setPassword("pass5");
            u5.setNci("NCI005");

            userRepository.saveAll(List.of(u1, u2, u3, u4, u5));
        }
    }
}