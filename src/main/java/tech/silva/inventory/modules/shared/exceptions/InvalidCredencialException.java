package tech.silva.inventory.modules.shared.exceptions;

public class InvalidCredencialException extends RuntimeException {
    public InvalidCredencialException(String message) {
        super(message);
    }
}
