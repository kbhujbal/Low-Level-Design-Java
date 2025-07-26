package com.parkinglot.strategy;

import com.parkinglot.model.ParkingTicket;

public interface PaymentStrategy {
    boolean processPayment(ParkingTicket ticket, double amount);
} 