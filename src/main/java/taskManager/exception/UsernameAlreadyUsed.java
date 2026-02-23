package taskManager.exception;

public class UsernameAlreadyUsed extends RuntimeException {
    public UsernameAlreadyUsed(String message) {
        super(message);
    }
}
