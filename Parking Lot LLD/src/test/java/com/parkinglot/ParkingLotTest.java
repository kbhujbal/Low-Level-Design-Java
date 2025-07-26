package com.parkinglot;

import com.parkinglot.model.*;
import com.parkinglot.enums.*;
import com.parkinglot.service.ParkingService;
import com.parkinglot.strategy.PricingStrategy;
import com.parkinglot.strategy.PaymentStrategy;
import com.parkinglot.strategy.impl.HourlyPricingStrategy;
import com.parkinglot.strategy.impl.CashPaymentStrategy;

public class ParkingLotTest {
    
    public static void testBasicParking() {
        try {
            System.out.println("=== Running Basic Parking Test ===");
            
            // Initialize parking lot
            ParkingLot parkingLot = new ParkingLot("TEST001", "Test Parking", "Test Address", 1);
            
            // Set up strategies
            PricingStrategy pricingStrategy = new HourlyPricingStrategy(5.0);
            PaymentStrategy paymentStrategy = new CashPaymentStrategy();
            
            // Create service
            ParkingService parkingService = new ParkingService(parkingLot, pricingStrategy, paymentStrategy);
            
            // Initialize spots
            parkingService.initializeParkingSpots();
            
            // Create vehicle
            Vehicle car = new Vehicle("TEST123", VehicleType.CAR, "Test Owner", "555-0000");
            
            // Park vehicle
            ParkingTicket ticket = parkingService.parkVehicle(car);
            System.out.println("Vehicle parked with ticket: " + ticket.getTicketId());
            
            // Check available spots
            int availableSpots = parkingService.getAvailableSpotsByType().get(VehicleType.CAR);
            System.out.println("Available car spots: " + availableSpots);
            
            // Exit vehicle
            double amount = parkingService.exitVehicle(ticket.getTicketId());
            System.out.println("Exit successful. Amount paid: $" + amount);
            
            // Check spots again
            availableSpots = parkingService.getAvailableSpotsByType().get(VehicleType.CAR);
            System.out.println("Available car spots after exit: " + availableSpots);
            
            System.out.println("=== Test Passed ===");
            
        } catch (Exception e) {
            System.err.println("Test failed: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        testBasicParking();
    }
} 