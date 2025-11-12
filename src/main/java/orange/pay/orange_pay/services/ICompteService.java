package orange.pay.orange_pay.services;

import orange.pay.orange_pay.core.ServiceCore;
import orange.pay.orange_pay.models.Compte;

public interface ICompteService extends ServiceCore<Compte> {
    void updateCompteSource(Compte compte, Double montant);

    void updateCompteDestinataire(Compte compte, Double montant);

    void verifySolde(Compte compte, Double montant);

}
