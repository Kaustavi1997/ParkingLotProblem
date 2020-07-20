package parkinglot.service;

import parkinglot.exception.ParkingLotSystemException;

public interface IParkingLotSystem {
    void park(Object Vehicle) throws ParkingLotSystemException;
    boolean isVehicleParked(Object vehicle);
    void unPark(Object vehicle) throws ParkingLotSystemException;
    boolean isVehicleUnParked(Object vehicle);

}
