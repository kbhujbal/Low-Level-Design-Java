# Parking Lot System - Low Level Design

A comprehensive low-level design implementation of a parking lot management system in Java.

## System Overview

This parking lot system provides a complete solution for managing vehicle parking with support for different vehicle types, dynamic pricing strategies, multiple payment methods, and real-time spot availability tracking.

## Architecture

### Core Components

1. **Models** (`src/main/java/com/parkinglot/model/`)
   - `Vehicle`: Represents different types of vehicles
   - `ParkingSpot`: Individual parking spots with status tracking
   - `ParkingTicket`: Tracks parking sessions and billing

2. **Enums** (`src/main/java/com/parkinglot/enums/`)
   - `VehicleType`: MOTORCYCLE, CAR, TRUCK, BUS, VAN
   - `ParkingSpotType`: Different types of parking spots
   - `ParkingStatus`: AVAILABLE, OCCUPIED, RESERVED, MAINTENANCE
   - `PaymentStatus`: PENDING, COMPLETED, FAILED, REFUNDED

3. **Strategy Pattern** (`src/main/java/com/parkinglot/strategy/`)
   - `PricingStrategy`: Interface for different pricing algorithms
   - `PaymentStrategy`: Interface for different payment methods
   - Implementations:
     - `HourlyPricingStrategy`: Time-based pricing
     - `CashPaymentStrategy`: Cash payment processing
     - `CreditCardPaymentStrategy`: Credit card payment processing

4. **Services** (`src/main/java/com/parkinglot/service/`)
   - `ParkingService`: Main business logic for parking operations

5. **Exceptions** (`src/main/java/com/parkinglot/exception/`)
   - `ParkingException`: Custom exceptions for parking-related errors
   - `PaymentException`: Custom exceptions for payment-related errors

## Key Features

### 1. Multi-Vehicle Support
- Supports different vehicle types (Motorcycle, Car, Truck, Bus, Van)
- Dedicated parking spots for each vehicle type
- Special spots for handicapped and electric vehicles

### 2. Dynamic Pricing
- Strategy pattern for flexible pricing algorithms
- Hourly-based pricing implementation
- Easy to extend with new pricing strategies

### 3. Multiple Payment Methods
- Cash payment
- Credit card payment
- Extensible payment strategy pattern

### 4. Real-time Management
- Live spot availability tracking
- Active ticket management
- Concurrent access support using ConcurrentHashMap

### 5. Comprehensive Tracking
- Entry and exit time tracking
- Parking duration calculation
- Payment status tracking
- Vehicle and owner information

## Design Patterns Used

### 1. Strategy Pattern
- **Pricing Strategy**: Allows different pricing algorithms
- **Payment Strategy**: Supports multiple payment methods

### 2. Factory Pattern (Implicit)
- Vehicle creation with different types
- Parking spot creation with different types

### 3. Singleton Pattern (Can be implemented)
- For global parking lot instance management

### 4. Observer Pattern (Can be implemented)
- For real-time notifications when spots become available

## Class Diagram

```
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│   ParkingLot    │    │  ParkingService │    │     Vehicle     │
├─────────────────┤    ├─────────────────┤    ├─────────────────┤
│ - parkingLotId  │    │ - parkingLot    │    │ - licensePlate  │
│ - name          │    │ - pricingStrat  │    │ - vehicleType   │
│ - address       │    │ - paymentStrat  │    │ - ownerName     │
│ - totalFloors   │    ├─────────────────┤    │ - contactNumber │
│ - parkingSpots  │    │ + parkVehicle() │    ├─────────────────┤
│ - activeTickets │    │ + exitVehicle() │    │ + getters/setters│
├─────────────────┤    │ + getAvailable()│    └─────────────────┘
│ + parkVehicle() │    └─────────────────┘
│ + exitVehicle() │
│ + findSpot()    │
└─────────────────┘

┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│  ParkingSpot    │    │ ParkingTicket   │    │ PricingStrategy │
├─────────────────┤    ├─────────────────┤    ├─────────────────┤
│ - spotId        │    │ - ticketId      │    │ + calculatePrice│
│ - spotType      │    │ - vehicle       │    └─────────────────┘
│ - status        │    │ - parkingSpot   │
│ - parkedVehicle │    │ - entryTime     │    ┌─────────────────┐
│ - floorNumber   │    │ - exitTime      │    │ PaymentStrategy │
│ - rowNumber     │    │ - amount        │    ├─────────────────┤
│ - spotNumber    │    │ - paymentStatus │    │ + processPayment│
├─────────────────┤    ├─────────────────┤    └─────────────────┘
│ + isAvailable() │    │ + getDuration() │
│ + parkVehicle() │    │ + setExitTime() │
│ + removeVehicle()│   └─────────────────┘
└─────────────────┘
```

## Usage Example

```java
// Initialize parking lot
ParkingLot parkingLot = new ParkingLot("PL001", "Downtown Parking", "123 Main St", 3);

// Set up pricing and payment strategies
PricingStrategy pricingStrategy = new HourlyPricingStrategy(5.0);
PaymentStrategy cashPayment = new CashPaymentStrategy();

// Create parking service
ParkingService parkingService = new ParkingService(parkingLot, pricingStrategy, cashPayment);

// Initialize parking spots
parkingService.initializeParkingSpots();

// Create a vehicle
Vehicle car = new Vehicle("ABC123", VehicleType.CAR, "John Doe", "555-0101");

// Park the vehicle
ParkingTicket ticket = parkingService.parkVehicle(car);

// Exit the vehicle
double amount = parkingService.exitVehicle(ticket.getTicketId());
```

## System Requirements

- Java 8 or higher
- No external dependencies (pure Java implementation)

## Future Enhancements

1. **Database Integration**: Add persistence layer for ticket and vehicle data
2. **Reservation System**: Allow advance parking spot reservations
3. **Notification System**: Real-time notifications for spot availability
4. **Analytics**: Parking usage analytics and reporting
5. **Mobile App**: REST API for mobile application integration
6. **Multi-location Support**: Support for multiple parking lots
7. **Discount System**: Implement various discount strategies
8. **Maintenance Mode**: Support for spot maintenance scheduling

## Testing

The system includes a demo class (`ParkingLotDemo`) that demonstrates:
- Vehicle parking and exiting
- Different payment methods
- Real-time spot availability tracking
- Revenue calculation

## Error Handling

The system includes comprehensive error handling:
- Custom exceptions for parking and payment errors
- Validation for vehicle types and spot availability
- Payment processing error handling

## Thread Safety

The system uses `ConcurrentHashMap` for thread-safe operations on:
- Parking spots management
- Active tickets tracking

This ensures the system can handle concurrent parking operations safely. 