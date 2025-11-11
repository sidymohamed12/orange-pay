package orange.pay.orange_pay.web.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public class RestResponse {
    public static Map<String, Object> response(HttpStatus status, Object results, Object type) {
        Map<String, Object> reponse = new HashMap<>();
        reponse.put("status", status.value());
        reponse.put("results", results);
        reponse.put("type", type);
        return reponse;
    }

    public static Map<String, Object> responsePaginate(HttpStatus status,
            Object results,
            Object currentPage,
            Integer totalPages,
            Object totalItems,
            Boolean first,
            Boolean last,
            String type) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", status.value());
        response.put("results", results);
        response.put("pages", new int[totalPages]);
        response.put("currentPage", currentPage);
        response.put("totalPages", totalPages);
        response.put("totalItems", totalItems);
        response.put("first", first);
        response.put("last", last);
        response.put("type", type);
        return response;
    }

    public static Map<String, String> extractFieldErrors(BindingResult bindingResult) {
        Map<String, String> errors = new HashMap<>();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        fieldErrors
                .forEach(fieldError -> errors.put(fieldError.getField().toLowerCase(), fieldError.getDefaultMessage()));
        return errors;
    }
}