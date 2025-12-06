package orange.pay.orange_pay.utils.mocks;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import orange.pay.orange_pay.models.Compte;
import orange.pay.orange_pay.models.User;
import orange.pay.orange_pay.repository.ICompteRepository;
import orange.pay.orange_pay.repository.IUserRepository;

@Component
@RequiredArgsConstructor
@Order(2)
public class CompteMock implements CommandLineRunner {

    private final ICompteRepository compteRepository;
    private final IUserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {

        if (compteRepository.count() == 0) {

            List<User> users = userRepository.findAll();
            if (users.isEmpty()) {
                System.out.println("⚠️ Aucun user trouvé !");
                return;
            }

            List<Compte> comptes = new ArrayList<>();
            int index = 1;

            for (User u : users) {

                Compte c = new Compte();
                c.setTelephone("77000000" + index);
                c.setCodeSecret("CODE" + index);
                c.setActif(true);
                c.setSolde(100000.0 + (index * 25000));
                c.setUser(u);

                comptes.add(c);
                index++;
            }

            compteRepository.saveAll(comptes);
            System.out.println("✅ " + comptes.size() + " comptes mock créés !");
        }
    }
}
