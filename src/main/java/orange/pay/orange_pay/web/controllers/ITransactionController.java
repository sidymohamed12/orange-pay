package orange.pay.orange_pay.web.controllers;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/transaction")
public interface ITransactionController {
    ResponseEntity<Map<String, Object>> getAllHistoriqueTransaction();
}
