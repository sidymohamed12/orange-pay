package orange.pay.orange_pay.validation.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import orange.pay.orange_pay.validation.validators.TelephoneValidator;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TelephoneValidator.class)
public @interface TelephoneValid {
    String message() default "Numéro de téléphone invalide";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}