package parkinglot.model;

public class Car {
    private int uniqueId;
    private String userName;
    private String carNO;

    public Car(int uniqueId, String userName, String carNO) {
        this.uniqueId = uniqueId;
        this.userName = userName;
        this.carNO = carNO;
    }

    public int getId() {
        return uniqueId;
    }
}
