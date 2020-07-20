package parkinglot.service;

import parkinglot.exception.ParkingLotSystemException;

public class ParkingLotSystem implements IParkingLotSystem {
    public Object vehicle;

    public ParkingLotSystem() {
    }

    public void park(Object vehicle) throws ParkingLotSystemException {
        if (this.vehicle != null)
            throw new ParkingLotSystemException(ParkingLotSystemException.ExceptionType.NO_SPACE);
        this.vehicle = vehicle;
    }

    @Override
    public boolean isVehicleParked(Object vehicle) {
        if (this.vehicle.equals(vehicle))
            return true;
        return false;
    }

    public void unPark(Object vehicle) throws ParkingLotSystemException {
        if (vehicle == null)
            throw new ParkingLotSystemException(ParkingLotSystemException.ExceptionType.NULL_VALUE);
        if (this.vehicle.equals(vehicle))
            this.vehicle = null;
        else
            throw new ParkingLotSystemException(ParkingLotSystemException.ExceptionType.NOT_FOUND);
    }

    @Override
    public boolean isVehicleUnParked(Object vehicle) {
        if (this.vehicle == null)
            return true;
        return false;
    }
}

