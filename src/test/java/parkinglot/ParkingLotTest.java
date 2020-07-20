package parkinglot;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import parkinglot.exception.ParkingLotSystemException;
import parkinglot.model.Car;
import parkinglot.observer.AirportSecurity;
import parkinglot.observer.Owner;
import parkinglot.service.ParkingLotSystem;

public class ParkingLotTest {
    ParkingLotSystem parkingLotSystem;
    Owner owner;
    AirportSecurity airportSecurity;

    @Before
    public void setUp() throws Exception {
        parkingLotSystem = new ParkingLotSystem();
        owner = new Owner();
        airportSecurity = new AirportSecurity();
    }

    @Test
    public void givenAVehicle_WhenParked_ShouldReturnTrue() {
        try {
            Car car = new Car(1, "Kaustavi", "WB-9056");
            parkingLotSystem.park(car);
            boolean isVehicleParked = parkingLotSystem.isVehicleParked(car);
            Assert.assertTrue(isVehicleParked);
        } catch (ParkingLotSystemException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenAVehicle_WhenUnParked_ShouldReturnTrue(){
        try {
            Car car = new Car(1, "Kaustavi", "WB-9056");
            parkingLotSystem.park(car);
            parkingLotSystem.unPark(car);
            boolean isUnPark = parkingLotSystem.isVehicleUnParked(car);
            Assert.assertTrue(isUnPark);
        } catch (ParkingLotSystemException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenAVehicle_WhenAlreadyParked_ShouldReturnFalse(){
        try {
            Car car = new Car(1, "Kaustavi", "WB-9056");
            parkingLotSystem.park(car);
            parkingLotSystem.park(new Car(2, "Sagnik", "WB-9066"));
        } catch (ParkingLotSystemException e) {
            Assert.assertEquals(ParkingLotSystemException.ExceptionType.NO_SPACE, e.exceptionType);
        }
    }

    @Test
    public void givenSameCar_CannotBeParked_ShouldThrowException(){
        try {
            Car car = new Car(1, "Kaustavi", "WB-9056");
            parkingLotSystem.park(car);
            parkingLotSystem.park(car);
        } catch (ParkingLotSystemException e) {
            Assert.assertEquals(ParkingLotSystemException.ExceptionType.ALREADY_EXISTS, e.exceptionType);
        }
    }

    @Test
    public void notGivenAVehicle_WhenUnParked_ShouldThrowException() throws ParkingLotSystemException {
        try {
            parkingLotSystem.unPark(null);
        } catch (ParkingLotSystemException e) {
            Assert.assertEquals(ParkingLotSystemException.ExceptionType.NULL_VALUE, e.exceptionType);
        }
    }

    @Test
    public void givenAVehicle_NotPresent_ShouldThrowException() throws ParkingLotSystemException {
        try {
            Car car = new Car(1, "Kaustavi", "WB-9056");
            parkingLotSystem.park(car);
            parkingLotSystem.unPark(new Car(2, "Sagnik", "WB-9066"));
        } catch (ParkingLotSystemException e) {
            Assert.assertEquals(ParkingLotSystemException.ExceptionType.NOT_FOUND, e.exceptionType);
        }
    }

    @Test
    public void givenAVehicleParked_ButAskingIsVehicleParkedWithOtherObject_ShouldReturnFalse() throws ParkingLotSystemException {
        Car car = new Car(1, "Kaustavi", "WB-9056");
        parkingLotSystem.park(car);
        boolean isParked = parkingLotSystem.isVehicleParked(new Car(2, "Sagnik", "WB-9066"));
        Assert.assertFalse(isParked);
    }

    @Test
    public void givenAVehicle_NotUnParked_ButAskingForIsVehicleUnParked_ShouldReturnFalse() throws ParkingLotSystemException {
        Car car = new Car(1, "Kaustavi", "WB-9056");
        parkingLotSystem.park(car);
        boolean isUnPark = parkingLotSystem.isVehicleUnParked(car);
        Assert.assertFalse(isUnPark);
    }
    @Test
    public void givenAVehicleToPark_ShouldInformOwnerParkingLotFull(){
        try {
            parkingLotSystem.addOwner(owner, airportSecurity);
            Car car = new Car(1, "Kaustavi", "WB-9056");
            Car car1 = new Car(2, "Sagnik", "WB-9045");
            Car car2 = new Car(3, "Riya", "WB-9023");
            Car car3 = new Car(4, "Gowri", "WB-9011");
            parkingLotSystem.park(car);
            parkingLotSystem.park(car1);
            parkingLotSystem.park(car2);
            parkingLotSystem.park(car3);
            Assert.assertEquals("Parking lot is full", owner.getMessage());
            Assert.assertEquals("Parking lot is full", airportSecurity.getMessage());
        } catch(ParkingLotSystemException e){
            e.printStackTrace();
        }
    }
}