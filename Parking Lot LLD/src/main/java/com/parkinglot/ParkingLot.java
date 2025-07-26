package com.parkinglot;

import com.parkinglot.enums.*;
import com.parkinglot.model.*;
import com.parkinglot.strategy.PaymentStrategy;
import com.parkinglot.strategy.PricingStrategy;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class ParkingLot {
    private String parkingLotId;
    private String name;
    private String address;
    private int totalFloors;
    private Map<String, ParkingSpot> parkingSpots;
    private Map<String, ParkingTicket> activeTickets;
    private PricingStrategy pricingStrategy;
    private PaymentStrategy paymentStrategy;
    private int ticketCounter;

    public ParkingLot(String parkingLotId, String name, String address, int totalFloors) {
        this.parkingLotId = parkingLotId;
        this.name = name;
        this.address = address;
        this.totalFloors = totalFloors;
        this.parkingSpots = new ConcurrentHashMap<>();
        this.activeTickets = new ConcurrentHashMap<>();
        this.ticketCounter = 1;
    }

    public ParkingTicket parkVehicle(Vehicle vehicle) throws Exception {
        // Find available parking spot
        ParkingSpot availableSpot = findAvailableSpot(vehicle.getVehicleType());
        
        if (availableSpot == null) {
            throw new Exception("No available parking spot for vehicle type: " + vehicle.getVehicleType());
        }

        // Park the vehicle
        availableSpot.parkVehicle(vehicle);

        // Create parking ticket
        String ticketId = generateTicketId();
        ParkingTicket ticket = new ParkingTicket(ticketId, vehicle, availableSpot);
        activeTickets.put(ticketId, ticket);

        System.out.println("Vehicle parked successfully. Ticket ID: " + ticketId);
        return ticket;
    }

    public double exitVehicle(String ticketId, PaymentStrategy paymentStrategy) throws Exception {
        ParkingTicket ticket = activeTickets.get(ticketId);
        if (ticket == null) {
            throw new Exception("Invalid ticket ID: " + ticketId);
        }

        // Set exit time
        ticket.setExitTime();

        // Calculate parking fee
        double amount = pricingStrategy.calculatePrice(ticket);
        ticket.setAmount(amount);

        // Process payment
        boolean paymentSuccess = paymentStrategy.processPayment(ticket, amount);
        if (!paymentSuccess) {
            throw new Exception("Payment failed for ticket: " + ticketId);
        }

        // Remove vehicle from spot
        ParkingSpot spot = ticket.getParkingSpot();
        spot.removeVehicle();

        // Remove ticket from active tickets
        activeTickets.remove(ticketId);

        System.out.println("Vehicle exited successfully. Amount paid: $" + amount);
        return amount;
    }

    private ParkingSpot findAvailableSpot(VehicleType vehicleType) {
        ParkingSpotType requiredSpotType = getSpotTypeForVehicle(vehicleType);
        
        return parkingSpots.values().stream()
                .filter(spot -> spot.getSpotType() == requiredSpotType && spot.isAvailable())
                .findFirst()
                .orElse(null);
    }

    private ParkingSpotType getSpotTypeForVehicle(VehicleType vehicleType) {
        switch (vehicleType) {
            case MOTORCYCLE:
                return ParkingSpotType.MOTORCYCLE_SPOT;
            case CAR:
                return ParkingSpotType.CAR_SPOT;
            case TRUCK:
                return ParkingSpotType.TRUCK_SPOT;
            case BUS:
                return ParkingSpotType.BUS_SPOT;
            case VAN:
                return ParkingSpotType.VAN_SPOT;
            default:
                return ParkingSpotType.CAR_SPOT;
        }
    }

    private String generateTicketId() {
        return "TKT" + String.format("%06d", ticketCounter++);
    }

    public void addParkingSpot(ParkingSpot spot) {
        parkingSpots.put(spot.getSpotId(), spot);
    }

    public int getAvailableSpotsCount(VehicleType vehicleType) {
        ParkingSpotType requiredSpotType = getSpotTypeForVehicle(vehicleType);
        return (int) parkingSpots.values().stream()
                .filter(spot -> spot.getSpotType() == requiredSpotType && spot.isAvailable())
                .count();
    }

    public int getTotalSpotsCount() {
        return parkingSpots.size();
    }

    public int getOccupiedSpotsCount() {
        return (int) parkingSpots.values().stream()
                .filter(spot -> !spot.isAvailable())
                .count();
    }

    // Getters and Setters
    public String getParkingLotId() {
        return parkingLotId;
    }

    public void setParkingLotId(String parkingLotId) {
        this.parkingLotId = parkingLotId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getTotalFloors() {
        return totalFloors;
    }

    public void setTotalFloors(int totalFloors) {
        this.totalFloors = totalFloors;
    }

    public Map<String, ParkingSpot> getParkingSpots() {
        return parkingSpots;
    }

    public void setParkingSpots(Map<String, ParkingSpot> parkingSpots) {
        this.parkingSpots = parkingSpots;
    }

    public Map<String, ParkingTicket> getActiveTickets() {
        return activeTickets;
    }

    public void setActiveTickets(Map<String, ParkingTicket> activeTickets) {
        this.activeTickets = activeTickets;
    }

    public PricingStrategy getPricingStrategy() {
        return pricingStrategy;
    }

    public void setPricingStrategy(PricingStrategy pricingStrategy) {
        this.pricingStrategy = pricingStrategy;
    }

    public PaymentStrategy getPaymentStrategy() {
        return paymentStrategy;
    }

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    @Override
    public String toString() {
        return "ParkingLot{" +
                "parkingLotId='" + parkingLotId + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", totalFloors=" + totalFloors +
                ", totalSpots=" + getTotalSpotsCount() +
                ", occupiedSpots=" + getOccupiedSpotsCount() +
                '}';
    }
} 