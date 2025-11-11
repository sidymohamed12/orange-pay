package orange.pay.orange_pay.web.controllers;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/transactions")
public interface ITransactionController {

    @GetMapping("/historique/{numero}")
    ResponseEntity<Map<String, Object>> getAllHistoriqueTransaction(@PathVariable(value = "numero") String numero);
}
