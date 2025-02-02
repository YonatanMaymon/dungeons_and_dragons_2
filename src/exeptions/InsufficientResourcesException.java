package exeptions;

public class InsufficientResourcesException extends RuntimeException {
    public InsufficientResourcesException() {
        super("you dont have the resources to cast this ability");
    }
}
