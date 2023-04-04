package com.driver.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name="ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ticketId;

    //This is also parent wrt to ticketEntity
    @ManyToMany(mappedBy = "bookedTickets",cascade = CascadeType.ALL)
    private List<Passenger> passengersList;

    @ManyToOne
    @JoinColumn
    private Train train;

    private Station fromStation;

    private int totalFare;

    private Station toStation;

    public Ticket(int ticketId, List<Passenger> passengersList, Train train, Station fromStation, Station toStation,int totalFare) {
        this.ticketId = ticketId;
        this.passengersList = passengersList;
        this.train = train;
        this.fromStation = fromStation;
        this.toStation = toStation;
        this.totalFare = totalFare;
    }

    public int getTotalFare() {
        return totalFare;
    }

    public void setTotalFare(int totalFare) {
        this.totalFare = totalFare;
    }

    public Station getFromStation() {
        return fromStation;
    }

    public void setFromStation(Station fromStation) {
        this.fromStation = fromStation;
    }

    public Station getToStation() {
        return toStation;
    }

    public void setToStation(Station toStation) {
        this.toStation = toStation;
    }

    public Ticket() {
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public List<Passenger> getPassengersList() {
        return passengersList;
    }

    public void setPassengersList(List<Passenger> passengersList) {
        this.passengersList = passengersList;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

}
