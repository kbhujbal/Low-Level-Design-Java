package com.parkinglot.model;

import com.parkinglot.enums.PaymentStatus;
import java.time.LocalDateTime;

public class ParkingTicket {
    private String ticketId;
    private Vehicle vehicle;
    private ParkingSpot parkingSpot;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private double amount;
    private PaymentStatus paymentStatus;

    public ParkingTicket(String ticketId, Vehicle vehicle, ParkingSpot parkingSpot) {
        this.ticketId = ticketId;
        this.vehicle = vehicle;
        this.parkingSpot = parkingSpot;
        this.entryTime = LocalDateTime.now();
        this.paymentStatus = PaymentStatus.PENDING;
    }

    public void setExitTime() {
        this.exitTime = LocalDateTime.now();
    }

    public long getParkingDurationInHours() {
        if (exitTime == null) {
            return java.time.Duration.between(entryTime, LocalDateTime.now()).toHours();
        }
        return java.time.Duration.between(entryTime, exitTime).toHours();
    }

    // Getters and Setters
    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }

    public void setParkingSpot(ParkingSpot parkingSpot) {
        this.parkingSpot = parkingSpot;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(LocalDateTime entryTime) {
        this.entryTime = entryTime;
    }

    public LocalDateTime getExitTime() {
        return exitTime;
    }

    public void setExitTime(LocalDateTime exitTime) {
        this.exitTime = exitTime;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    @Override
    public String toString() {
        return "ParkingTicket{" +
                "ticketId='" + ticketId + '\'' +
                ", vehicle=" + vehicle +
                ", parkingSpot=" + parkingSpot +
                ", entryTime=" + entryTime +
                ", exitTime=" + exitTime +
                ", amount=" + amount +
                ", paymentStatus=" + paymentStatus +
                '}';
    }
} 