package orange.pay.orange_pay.web.controllers.impl;

import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import orange.pay.orange_pay.services.ICompteService;
import orange.pay.orange_pay.services.ITransactionService;

@RestController
@RequiredArgsConstructor
public class CompteController {
    private final ICompteService compteService;
    private final ITransactionService transactionService;
}
