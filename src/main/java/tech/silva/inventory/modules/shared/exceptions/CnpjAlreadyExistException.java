package tech.silva.inventory.modules.shared.exceptions;

public class CnpjAlreadyExistException extends RuntimeException {
    public CnpjAlreadyExistException(String message) {
        super(message);
    }
}
