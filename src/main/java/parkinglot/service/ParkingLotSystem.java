package parkinglot.service;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

import parkinglot.exception.ParkingLotSystemException;
import parkinglot.model.Car;
import parkinglot.observer.AirportSecurity;
import parkinglot.observer.Owner;
import parkinglot.utility.MessageToInform;

import java.util.HashMap;
import java.util.Map;

public class ParkingLotSystem implements IParkingLotSystem {
    private final int PARKING_LOT_SIZE = 3;
    Owner owner = new Owner();
    AirportSecurity airportSecurity = new AirportSecurity();
    int currentAvailableSlot = 0;

    private Map<Integer, Car> parkingLotMap = new HashMap<>();
    private Map<Integer, Integer> parkingCarMap = new HashMap<>();
    private Map<Car, String> parkingTimeMap = new HashMap<>();

    public void park(Car car, int slot) throws ParkingLotSystemException {
        if (parkingLotMap.size() == PARKING_LOT_SIZE) {
            notifyObserverWhenFull();
            throw new ParkingLotSystemException(ParkingLotSystemException.ExceptionType.NO_SPACE);
        }
        if (parkingLotMap.containsKey(car.getId())) {
            throw new ParkingLotSystemException(ParkingLotSystemException.ExceptionType.ALREADY_EXISTS);
        } else if (parkingLotMap.size() < PARKING_LOT_SIZE) {
            parkingLotMap.put(car.getId(), car);
            storeParkingTime(car);
            notifyOwnerParkingTime(car);
            parkingCarMap.put(car.getId(), currentAvailableSlot);
        }
    }

    public boolean isVehicleParked(Car car) {
        return parkingLotMap.containsKey(car.getId());
    }

    public void unPark(Car car) throws ParkingLotSystemException {
        if (car == null)
            throw new ParkingLotSystemException(ParkingLotSystemException.ExceptionType.NULL_VALUE);
        if (!parkingLotMap.containsKey(car.getId()))
            throw new ParkingLotSystemException(ParkingLotSystemException.ExceptionType.NOT_FOUND);
        parkingLotMap.remove(car.getId());
        int slot = parkingCarMap.get(car.getId());
        if (slot < currentAvailableSlot)
            currentAvailableSlot = slot;
        parkingCarMap.remove(car.getId());
        notifyOwnerWhenSpace();
    }

    public boolean isVehicleUnParked(Car car) {
        return (!parkingLotMap.containsKey(car.getId()));
    }

    public void addObserver(Owner owner, AirportSecurity airportSecurity) {
        this.owner = owner;
        this.airportSecurity = airportSecurity;
    }

    public void notifyObserverWhenFull() {
        owner.setMessage(MessageToInform.FULL.message);
        airportSecurity.setMessage(MessageToInform.FULL.message);
    }

    public void notifyOwnerWhenSpace() {
        owner.setMessage(MessageToInform.HAVE_SPACE.message);
    }

    public void notifyOwnerParkingTime(Car car) {
        owner.setMessage(MessageToInform.PARK_TIME.message + parkingTimeMap.get(car));
    }

    public boolean isSlotNotAvailable() {
        int slot = 0;
        for (Map.Entry<Integer, Integer> entry : parkingCarMap.entrySet()) {
            slot = entry.getValue();
            if (slot == currentAvailableSlot)
                return true;
        }
        return false;
    }

    public void allotParkingSlot(Car car) throws ParkingLotSystemException {
        while (isSlotNotAvailable())
            currentAvailableSlot++;
        park(car, currentAvailableSlot);
        currentAvailableSlot++;
    }

    public int checkCarSlot(Car car) {
        return parkingCarMap.get(car.getId());
    }

    public boolean isCarAddedToSlot(Car car) {
        return parkingCarMap.containsKey(car.getId());
    }

    public int findCar(Car car) throws ParkingLotSystemException {
        if (isCarAddedToSlot(car))
            return parkingCarMap.get(car.getId());
        throw new ParkingLotSystemException(ParkingLotSystemException.ExceptionType.NOT_FOUND);
    }

    public void storeParkingTime(Car car) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        parkingTimeMap.put(car, dtf.format(now));
    }

    public boolean isTimeStored(Car car) {
        return parkingTimeMap.containsKey(car);
    }

    public String getTime(Car car) {
        return parkingTimeMap.get(car);
    }

}

