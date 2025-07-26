package com.parkinglot.model;

import com.parkinglot.enums.ParkingSpotType;
import com.parkinglot.enums.ParkingStatus;

public class ParkingSpot {
    private String spotId;
    private ParkingSpotType spotType;
    private ParkingStatus status;
    private Vehicle parkedVehicle;
    private int floorNumber;
    private int rowNumber;
    private int spotNumber;

    public ParkingSpot(String spotId, ParkingSpotType spotType, int floorNumber, int rowNumber, int spotNumber) {
        this.spotId = spotId;
        this.spotType = spotType;
        this.status = ParkingStatus.AVAILABLE;
        this.floorNumber = floorNumber;
        this.rowNumber = rowNumber;
        this.spotNumber = spotNumber;
    }

    public boolean isAvailable() {
        return status == ParkingStatus.AVAILABLE;
    }

    public void parkVehicle(Vehicle vehicle) {
        this.parkedVehicle = vehicle;
        this.status = ParkingStatus.OCCUPIED;
    }

    public void removeVehicle() {
        this.parkedVehicle = null;
        this.status = ParkingStatus.AVAILABLE;
    }

    // Getters and Setters
    public String getSpotId() {
        return spotId;
    }

    public void setSpotId(String spotId) {
        this.spotId = spotId;
    }

    public ParkingSpotType getSpotType() {
        return spotType;
    }

    public void setSpotType(ParkingSpotType spotType) {
        this.spotType = spotType;
    }

    public ParkingStatus getStatus() {
        return status;
    }

    public void setStatus(ParkingStatus status) {
        this.status = status;
    }

    public Vehicle getParkedVehicle() {
        return parkedVehicle;
    }

    public void setParkedVehicle(Vehicle parkedVehicle) {
        this.parkedVehicle = parkedVehicle;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }

    public int getSpotNumber() {
        return spotNumber;
    }

    public void setSpotNumber(int spotNumber) {
        this.spotNumber = spotNumber;
    }

    @Override
    public String toString() {
        return "ParkingSpot{" +
                "spotId='" + spotId + '\'' +
                ", spotType=" + spotType +
                ", status=" + status +
                ", parkedVehicle=" + parkedVehicle +
                ", floorNumber=" + floorNumber +
                ", rowNumber=" + rowNumber +
                ", spotNumber=" + spotNumber +
                '}';
    }
} 