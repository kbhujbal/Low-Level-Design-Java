package com.parkinglot.strategy.impl;

import com.parkinglot.model.ParkingTicket;
import com.parkinglot.strategy.PaymentStrategy;

public class CashPaymentStrategy implements PaymentStrategy {
    @Override
    public boolean processPayment(ParkingTicket ticket, double amount) {
        // Simulate cash payment processing
        System.out.println("Processing cash payment of $" + amount + " for ticket " + ticket.getTicketId());
        ticket.setPaymentStatus(com.parkinglot.enums.PaymentStatus.COMPLETED);
        return true;
    }
} 