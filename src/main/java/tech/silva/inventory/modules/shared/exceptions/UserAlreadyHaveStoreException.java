package tech.silva.inventory.modules.shared.exceptions;

public class UserAlreadyHaveStoreException extends RuntimeException {
    public UserAlreadyHaveStoreException() {
        super("User already have a Store registered");
    }
}
