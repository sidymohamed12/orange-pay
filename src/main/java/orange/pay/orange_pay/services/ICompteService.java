package orange.pay.orange_pay.services;

import orange.pay.orange_pay.models.Compte;

public interface ICompteService {
    void updateCompteSource(Compte compte, Double montant);

    void updateCompteDestinataire(Compte compte, Double montant);

    void verifySolde(Compte compte, Double montant);

    Compte getCompteById(Long id);
}
