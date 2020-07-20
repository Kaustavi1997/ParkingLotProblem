package parkinglot;

import org.junit.Assert;
import org.junit.Test;

public class ParkingLotTest {
    @Test
    public void givenAVehicle_WhenParked_ShouldReturnTrue(){
        ParkingLotSystem parkingLotSystem = new ParkingLotSystem();
        boolean isPark = parkingLotSystem.park(new Object());
        Assert.assertTrue(isPark);

    }
}
