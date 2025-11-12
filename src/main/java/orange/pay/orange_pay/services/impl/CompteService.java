package orange.pay.orange_pay.services.impl;

import org.springframework.stereotype.Service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import orange.pay.orange_pay.models.Compte;
import orange.pay.orange_pay.repository.ICompteRepository;
import orange.pay.orange_pay.services.ICompteService;
import orange.pay.orange_pay.utils.exceptions.ResourceNotFound;
import orange.pay.orange_pay.utils.exceptions.SoldeInsufisant;

@Service
@RequiredArgsConstructor
public class CompteService implements ICompteService {
    private final ICompteRepository compteRepository;

    @Override
    public Compte findById(@NonNull Long id) {
        return compteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Compte not found with id " + id));
    }

    @Override
    public void updateCompteSource(Compte compte, Double montant) {
        compte.setSolde(compte.getSolde() - montant);
        compteRepository.save(compte);
    }

    @Override
    public void updateCompteDestinataire(Compte compte, Double montant) {
        compte.setSolde(compte.getSolde() + montant);
        compteRepository.save(compte);
    }

    @Override
    public void verifySolde(Compte compte, Double montant) {
        if (compte.getSolde() < montant) {
            throw new SoldeInsufisant();
        }
    }

}
