package orange.pay.orange_pay.web.controllers;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import orange.pay.orange_pay.web.dto.request.TransactionRequest;

@RequestMapping("/api/transactions")
public interface ITransactionController {

    @GetMapping("/{id}")
    ResponseEntity<Map<String, Object>> getTransactionById(@PathVariable(value = "id") Long id);

    @PostMapping()
    ResponseEntity<Map<String, Object>> createTransaction(@RequestBody TransactionRequest transactionRequest);
}
