package com.parkinglot.demo;

import com.parkinglot.ParkingLot;
import com.parkinglot.model.*;
import com.parkinglot.enums.*;
import com.parkinglot.service.ParkingService;
import com.parkinglot.strategy.PricingStrategy;
import com.parkinglot.strategy.PaymentStrategy;
import com.parkinglot.strategy.impl.HourlyPricingStrategy;
import com.parkinglot.strategy.impl.CashPaymentStrategy;
import com.parkinglot.strategy.impl.CreditCardPaymentStrategy;

public class ParkingLotDemo {
    
    public static void main(String[] args) {
        try {
            // Initialize parking lot
            ParkingLot parkingLot = new ParkingLot("PL001", "Downtown Parking", "123 Main St", 3);
            
            // Initialize pricing strategy
            PricingStrategy pricingStrategy = new HourlyPricingStrategy(5.0); // $5 per hour
            
            // Initialize payment strategies
            PaymentStrategy cashPayment = new CashPaymentStrategy();
            PaymentStrategy creditCardPayment = new CreditCardPaymentStrategy("1234567890123456", "John Doe");
            
            // Initialize parking service
            ParkingService parkingService = new ParkingService(parkingLot, pricingStrategy, cashPayment);
            
            // Initialize parking spots
            parkingService.initializeParkingSpots();
            
            System.out.println("=== Parking Lot System Demo ===");
            System.out.println(parkingLot);
            System.out.println();
            
            // Display available spots
            System.out.println("Available spots by vehicle type:");
            parkingService.getAvailableSpotsByType().forEach((type, count) -> 
                System.out.println(type + ": " + count + " spots"));
            System.out.println();
            
            // Create vehicles
            Vehicle car1 = new Vehicle("ABC123", VehicleType.CAR, "John Doe", "555-0101");
            Vehicle motorcycle1 = new Vehicle("XYZ789", VehicleType.MOTORCYCLE, "Jane Smith", "555-0202");
            Vehicle truck1 = new Vehicle("TRK456", VehicleType.TRUCK, "Bob Johnson", "555-0303");
            
            // Park vehicles
            System.out.println("=== Parking Vehicles ===");
            ParkingTicket ticket1 = parkingService.parkVehicle(car1);
            ParkingTicket ticket2 = parkingService.parkVehicle(motorcycle1);
            ParkingTicket ticket3 = parkingService.parkVehicle(truck1);
            
            System.out.println();
            System.out.println("Updated available spots:");
            parkingService.getAvailableSpotsByType().forEach((type, count) -> 
                System.out.println(type + ": " + count + " spots"));
            System.out.println();
            
            // Simulate some time passing
            System.out.println("Simulating 2 hours of parking...");
            Thread.sleep(1000); // Simulate time passing
            
            // Exit vehicles with different payment methods
            System.out.println("=== Exiting Vehicles ===");
            double amount1 = parkingService.exitVehicle(ticket1.getTicketId(), cashPayment);
            double amount2 = parkingService.exitVehicle(ticket2.getTicketId(), creditCardPayment);
            double amount3 = parkingService.exitVehicle(ticket3.getTicketId());
            
            System.out.println();
            System.out.println("Total revenue: $" + (amount1 + amount2 + amount3));
            System.out.println();
            
            // Display final status
            System.out.println("Final parking lot status:");
            System.out.println(parkingLot);
            System.out.println();
            System.out.println("Available spots by vehicle type:");
            parkingService.getAvailableSpotsByType().forEach((type, count) -> 
                System.out.println(type + ": " + count + " spots"));
            
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
} 