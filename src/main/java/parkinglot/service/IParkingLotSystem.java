package parkinglot.service;

import parkinglot.exception.ParkingLotSystemException;
import parkinglot.model.Car;

public interface IParkingLotSystem {
    void park(Car car) throws ParkingLotSystemException;
    boolean isVehicleParked(Car car);
    void unPark(Car car) throws ParkingLotSystemException;
    boolean isVehicleUnParked(Car car);

}
