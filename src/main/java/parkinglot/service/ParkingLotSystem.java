package parkinglot.service;

import parkinglot.exception.ParkingLotSystemException;
import parkinglot.model.Car;
import parkinglot.observer.AirportSecurity;
import parkinglot.observer.IObserver;
import parkinglot.observer.Owner;

import java.util.HashMap;
import java.util.Map;

public class ParkingLotSystem implements IParkingLotSystem {
    private final int PARKING_LOT_SIZE = 3;
    Owner owner = new Owner();
    AirportSecurity airportSecurity = new AirportSecurity();

    private Map<Integer, Car> parkingLotMap = new HashMap<Integer, Car>();

    public void park(Car car) throws ParkingLotSystemException {
        if(parkingLotMap.containsKey(car.getId())){
            throw new ParkingLotSystemException(ParkingLotSystemException.ExceptionType.ALREADY_EXISTS);}
        else if(parkingLotMap.size() < PARKING_LOT_SIZE){
            parkingLotMap.put(car.getId(), car);}
        if(parkingLotMap.size() == PARKING_LOT_SIZE){
            notifyObserver();
            throw new ParkingLotSystemException(ParkingLotSystemException.ExceptionType.NO_SPACE);}
    }
    public boolean isVehicleParked(Car car) {
        return parkingLotMap.containsKey(car.getId());
    }

    public void unPark(Car car) throws ParkingLotSystemException {
        if(car == null)
            throw new ParkingLotSystemException(ParkingLotSystemException.ExceptionType.NULL_VALUE);
        if(!parkingLotMap.containsKey(car.getId()))
            throw new ParkingLotSystemException(ParkingLotSystemException.ExceptionType.NOT_FOUND);
        parkingLotMap.remove(car.getId());
    }

    public boolean isVehicleUnParked(Car car) {
        return (!parkingLotMap.containsKey(car.getId()));
    }

    public void addOwner(Owner owner, AirportSecurity airportSecurity) {
        this.owner = owner;
        this.airportSecurity = airportSecurity;
    }
    public void notifyObserver() {
        owner.setMessage("Parking lot is full");
        airportSecurity.setMessage("Parking lot is full");
    }
}

