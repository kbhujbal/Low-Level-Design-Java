package com.parkinglot.strategy.impl;

import com.parkinglot.model.ParkingTicket;
import com.parkinglot.strategy.PaymentStrategy;

public class CreditCardPaymentStrategy implements PaymentStrategy {
    private String cardNumber;
    private String cardHolderName;

    public CreditCardPaymentStrategy(String cardNumber, String cardHolderName) {
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
    }

    @Override
    public boolean processPayment(ParkingTicket ticket, double amount) {
        // Simulate credit card payment processing
        System.out.println("Processing credit card payment of $" + amount + 
                          " for ticket " + ticket.getTicketId() + 
                          " using card ending in " + cardNumber.substring(cardNumber.length() - 4));
        ticket.setPaymentStatus(com.parkinglot.enums.PaymentStatus.COMPLETED);
        return true;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }
} 