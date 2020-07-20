package parkinglot;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import parkinglot.exception.ParkingLotSystemException;
import parkinglot.service.ParkingLotSystem;

public class ParkingLotTest {
    ParkingLotSystem parkingLotSystem = null;
    Object vehicle = null;

    @Before
    public void setUp() throws Exception {
        vehicle = new Object();
        parkingLotSystem = new ParkingLotSystem();
    }

    @Test
    public void givenAVehicle_WhenParked_ShouldReturnTrue() throws ParkingLotSystemException {
        try {
            parkingLotSystem.park(vehicle);
            boolean isVehicleParked = parkingLotSystem.isVehicleParked(vehicle);
            Assert.assertTrue(isVehicleParked);
        } catch (ParkingLotSystemException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenAVehicle_WhenUnParked_ShouldReturnTrue() throws ParkingLotSystemException {
        try {
            parkingLotSystem.park(vehicle);
            parkingLotSystem.unPark(vehicle);
            boolean isUnPark = parkingLotSystem.isVehicleUnParked(vehicle);
            Assert.assertTrue(isUnPark);
        } catch (ParkingLotSystemException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenAVehicle_WhenAlreadyParked_ShouldReturnFalse() throws ParkingLotSystemException {
        try {
            parkingLotSystem.park(vehicle);
            parkingLotSystem.park(new Object());
        } catch (ParkingLotSystemException e) {
            Assert.assertEquals(ParkingLotSystemException.ExceptionType.NO_SPACE, e.exceptionType);
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
            parkingLotSystem.park(vehicle);
            parkingLotSystem.unPark(new Object());
        } catch (ParkingLotSystemException e) {
            Assert.assertEquals(ParkingLotSystemException.ExceptionType.NOT_FOUND, e.exceptionType);
        }
    }

    @Test
    public void givenAVehicleParked_ButAskingIsVehicleParkedWithOtherObject_ShouldReturnFalse() throws ParkingLotSystemException {
        parkingLotSystem.park(vehicle);
        boolean isParked = parkingLotSystem.isVehicleParked(new Object());
        Assert.assertFalse(isParked);
    }

    @Test
    public void givenAVehicle_NotUnParked_ButAskingForIsVehicleUnParked_ShouldReturnFalse() throws ParkingLotSystemException {
        parkingLotSystem.park(vehicle);
        boolean isUnPark = parkingLotSystem.isVehicleUnParked(vehicle);
        Assert.assertFalse(isUnPark);
    }
}