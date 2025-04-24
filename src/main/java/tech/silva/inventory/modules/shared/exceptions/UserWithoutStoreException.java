package tech.silva.inventory.modules.shared.exceptions;

public class UserWithoutStoreException extends RuntimeException {
    public UserWithoutStoreException(String message) {
        super(message);
    }
}
