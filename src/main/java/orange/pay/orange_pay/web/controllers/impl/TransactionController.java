package orange.pay.orange_pay.web.controllers.impl;

import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import orange.pay.orange_pay.services.ITransactionService;
import orange.pay.orange_pay.web.controllers.ITransactionController;

@RestController
@RequiredArgsConstructor
public class TransactionController implements ITransactionController {
    private final ITransactionService transactionService;
}
