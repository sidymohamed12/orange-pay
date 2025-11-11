package orange.pay.orange_pay.utils.mocks;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import orange.pay.orange_pay.models.Compte;
import orange.pay.orange_pay.repository.ICompteRepository;

@Component
@RequiredArgsConstructor
@Order(1)
public class CompteMock implements CommandLineRunner {

    private final ICompteRepository compteRepository;

    @Override
    public void run(String... args) throws Exception {
        if (compteRepository.count() == 0) {

            Compte c1 = new Compte();
            c1.setPrenom("Sidy Mohamed");
            c1.setNom("Saizonou");
            c1.setAddresse("Dakar");
            c1.setTelephone("761823698");
            c1.setCodeSecret("CODE1");
            c1.setActif(true);
            c1.setSolde(150000.0);
            compteRepository.save(c1);

            Compte c2 = new Compte();
            c2.setPrenom("Said");
            c2.setNom("Saizonou");
            c2.setAddresse("Thiès");
            c2.setTelephone("776492133");
            c2.setCodeSecret("CODE2");
            c2.setActif(true);
            c2.setSolde(200000.0);
            compteRepository.save(c2);

            Compte c3 = new Compte();
            c3.setPrenom("Faycal");
            c3.setNom("Saizonou");
            c3.setAddresse("Saint-Louis");
            c3.setTelephone("775853110");
            c3.setCodeSecret("CODE3");
            c3.setActif(true);
            c3.setSolde(175000.0);
            compteRepository.save(c3);

            Compte c4 = new Compte();
            c4.setPrenom("Abou");
            c4.setNom("Bathily");
            c4.setAddresse("Kaolack");
            c4.setTelephone("788930107");
            c4.setCodeSecret("CODE4");
            c4.setActif(false);
            c4.setSolde(120000.0);
            compteRepository.save(c4);

            Compte c5 = new Compte();
            c5.setPrenom("Mariama");
            c5.setNom("Diallo");
            c5.setAddresse("Ziguinchor");
            c5.setTelephone("770688172");
            c5.setCodeSecret("CODE5");
            c5.setActif(true);
            c5.setSolde(95000.0);
            compteRepository.save(c5);
        }
    }

}
