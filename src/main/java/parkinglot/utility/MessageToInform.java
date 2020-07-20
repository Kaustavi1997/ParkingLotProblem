package parkinglot.utility;

public enum MessageToInform {
    FULL("Parking lot is full"),
    HAVE_SPACE("Again space in parkingLot to park car");

    public String message;

    MessageToInform(String message) {
        this.message = message;
    }
}
