package com.parkinglot.strategy.impl;

import com.parkinglot.model.ParkingTicket;
import com.parkinglot.strategy.PricingStrategy;

public class HourlyPricingStrategy implements PricingStrategy {
    private double hourlyRate;

    public HourlyPricingStrategy(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    @Override
    public double calculatePrice(ParkingTicket ticket) {
        long hours = ticket.getParkingDurationInHours();
        return hours * hourlyRate;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }
} 