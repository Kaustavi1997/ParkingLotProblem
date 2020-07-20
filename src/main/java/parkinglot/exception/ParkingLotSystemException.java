package parkinglot.exception;

public class ParkingLotSystemException extends Exception {
    public enum ExceptionType {
        NULL_VALUE("No Vehicle Present"), NOT_FOUND("Vehicle Not Found"), NO_SPACE("Parking lot is full"), ALREADY_EXISTS("Vehicle already present");
        String message;

        ExceptionType(String message) {
            this.message = message;
        }
    }

    public ExceptionType exceptionType;

    public ParkingLotSystemException(ExceptionType exceptionType) {
        this.exceptionType = exceptionType;
    }
}
