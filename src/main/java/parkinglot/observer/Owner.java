package parkinglot.observer;

public class Owner implements IObserver {
    private String message = null;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
