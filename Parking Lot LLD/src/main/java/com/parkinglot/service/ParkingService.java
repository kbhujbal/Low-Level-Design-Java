package com.parkinglot.service;

import com.parkinglot.ParkingLot;
import com.parkinglot.model.*;
import com.parkinglot.enums.*;
import com.parkinglot.strategy.PricingStrategy;
import com.parkinglot.strategy.PaymentStrategy;
import java.util.*;

public class ParkingService {
    private ParkingLot parkingLot;
    private PricingStrategy pricingStrategy;
    private PaymentStrategy defaultPaymentStrategy;

    public ParkingService(ParkingLot parkingLot, PricingStrategy pricingStrategy, PaymentStrategy defaultPaymentStrategy) {
        this.parkingLot = parkingLot;
        this.pricingStrategy = pricingStrategy;
        this.defaultPaymentStrategy = defaultPaymentStrategy;
    }

    public ParkingTicket parkVehicle(Vehicle vehicle) throws Exception {
        // Set pricing strategy
        parkingLot.setPricingStrategy(pricingStrategy);
        
        return parkingLot.parkVehicle(vehicle);
    }

    public double exitVehicle(String ticketId) throws Exception {
        return parkingLot.exitVehicle(ticketId, defaultPaymentStrategy);
    }

    public double exitVehicle(String ticketId, PaymentStrategy paymentStrategy) throws Exception {
        return parkingLot.exitVehicle(ticketId, paymentStrategy);
    }

    public Map<VehicleType, Integer> getAvailableSpotsByType() {
        Map<VehicleType, Integer> availableSpots = new HashMap<>();
        
        for (VehicleType vehicleType : VehicleType.values()) {
            int count = parkingLot.getAvailableSpotsCount(vehicleType);
            availableSpots.put(vehicleType, count);
        }
        
        return availableSpots;
    }

    public ParkingTicket getTicketById(String ticketId) {
        return parkingLot.getActiveTickets().get(ticketId);
    }

    public List<ParkingTicket> getAllActiveTickets() {
        return new ArrayList<>(parkingLot.getActiveTickets().values());
    }

    public double calculateParkingFee(String ticketId) throws Exception {
        ParkingTicket ticket = getTicketById(ticketId);
        if (ticket == null) {
            throw new Exception("Ticket not found: " + ticketId);
        }
        
        return pricingStrategy.calculatePrice(ticket);
    }

    public void initializeParkingSpots() {
        // Initialize parking spots for different vehicle types
        initializeSpotsForType(ParkingSpotType.MOTORCYCLE_SPOT, 20, 1);
        initializeSpotsForType(ParkingSpotType.CAR_SPOT, 100, 1);
        initializeSpotsForType(ParkingSpotType.TRUCK_SPOT, 10, 1);
        initializeSpotsForType(ParkingSpotType.BUS_SPOT, 5, 1);
        initializeSpotsForType(ParkingSpotType.VAN_SPOT, 15, 1);
        initializeSpotsForType(ParkingSpotType.HANDICAPPED_SPOT, 10, 1);
        initializeSpotsForType(ParkingSpotType.ELECTRIC_VEHICLE_SPOT, 20, 1);
    }

    private void initializeSpotsForType(ParkingSpotType spotType, int count, int floor) {
        for (int i = 1; i <= count; i++) {
            String spotId = spotType.name() + "_" + floor + "_" + i;
            ParkingSpot spot = new ParkingSpot(spotId, spotType, floor, 1, i);
            parkingLot.addParkingSpot(spot);
        }
    }

    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    public void setParkingLot(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public PricingStrategy getPricingStrategy() {
        return pricingStrategy;
    }

    public void setPricingStrategy(PricingStrategy pricingStrategy) {
        this.pricingStrategy = pricingStrategy;
    }

    public PaymentStrategy getDefaultPaymentStrategy() {
        return defaultPaymentStrategy;
    }

    public void setDefaultPaymentStrategy(PaymentStrategy defaultPaymentStrategy) {
        this.defaultPaymentStrategy = defaultPaymentStrategy;
    }
} 