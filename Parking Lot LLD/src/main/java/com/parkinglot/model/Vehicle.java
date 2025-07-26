package com.parkinglot.model;

import com.parkinglot.enums.VehicleType;

public class Vehicle {
    private String licensePlate;
    private VehicleType vehicleType;
    private String ownerName;
    private String contactNumber;

    public Vehicle(String licensePlate, VehicleType vehicleType, String ownerName, String contactNumber) {
        this.licensePlate = licensePlate;
        this.vehicleType = vehicleType;
        this.ownerName = ownerName;
        this.contactNumber = contactNumber;
    }

    // Getters and Setters
    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "licensePlate='" + licensePlate + '\'' +
                ", vehicleType=" + vehicleType +
                ", ownerName='" + ownerName + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                '}';
    }
} 