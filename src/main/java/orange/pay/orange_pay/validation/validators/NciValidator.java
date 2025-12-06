package orange.pay.orange_pay.validation.validators;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import orange.pay.orange_pay.validation.annotations.NciValid;

public class NciValidator implements ConstraintValidator<NciValid, String> {

    @Override
    public boolean isValid(String nci, ConstraintValidatorContext context) {
        if (nci == null || !nci.matches("\\d{17}")) {
            return false;
        }

        int sexe = Integer.parseInt(nci.substring(0, 1));
        int region = Integer.parseInt(nci.substring(1, 3));
        String dateNaissanceStr = nci.substring(3, 11);
        int sequence = Integer.parseInt(nci.substring(11, 15));
        int cle = Integer.parseInt(nci.substring(16, 17));

        if (sexe != 1 && sexe != 2)
            return false;

        if (region < 1 || region > 14)
            return false;

        if (!dateValide(dateNaissanceStr))
            return false;

        if (sequence < 1 || sequence > 9999)
            return false;

        // 5. Clé de contrôle (ex : somme digits % 10)
        int expectedCle = calculCle(nci.substring(0, 16));
        if (expectedCle != cle)
            return false;

        return true;
    }

    private boolean dateValide(String dateStr) {
        try {
            LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyyMMdd"));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Exemple simple de clé : (somme des 16 chiffres % 10)
    private int calculCle(String first16) {
        int sum = 0;
        for (char c : first16.toCharArray()) {
            sum += c - '0';
        }
        return sum % 10;
    }
}