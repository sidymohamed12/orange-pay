package orange.pay.orange_pay.validation.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import orange.pay.orange_pay.validation.annotations.TelephoneValid;

public class TelephoneValidator implements ConstraintValidator<TelephoneValid, String> {

    private static final String REGEX = "^(70|76|77|78)\\d{7}$";

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null)
            return false;
        return value.matches(REGEX);
    }
}