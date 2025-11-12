package orange.pay.orange_pay.utils.exceptions;

public class SoldeInsufisant extends RuntimeException {

    public SoldeInsufisant() {
        super("400 - Solde insufisant pour effectuer cette transaction");
    }

    public SoldeInsufisant(String message) {
        super("400 - " + message);
    }
}