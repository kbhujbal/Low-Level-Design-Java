package com.parkinglot.strategy;

import com.parkinglot.model.ParkingTicket;

public interface PricingStrategy {
    double calculatePrice(ParkingTicket ticket);
} 