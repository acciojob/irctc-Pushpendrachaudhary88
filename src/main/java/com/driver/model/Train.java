package com.driver.model;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "trains")
public class Train {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int trainId;

    private String route;

    //Mapping with tickets Entity : parent Entity
    @OneToMany(mappedBy = "train", cascade = CascadeType.ALL)
    private List<Ticket> bookedTickets = new ArrayList<>();

    private LocalTime departureTime; //This is the time it starts from its first station
    //And it takes 1 hour to reach the next station on its route

    private int noOfSeats;

    public Train() {
    }

    public Train(int trainId, String route, List<Ticket> bookedTickets, LocalTime departureTime, int noOfSeats) {
        this.trainId = trainId;
        this.route = route;
        this.bookedTickets = bookedTickets;
        this.departureTime = departureTime;
        this.noOfSeats = noOfSeats;
    }

    public int getTrainId() {
        return trainId;
    }

    public void setTrainId(int trainId) {
        this.trainId = trainId;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public List<Ticket> getBookedTickets() {
        return bookedTickets;
    }

    public void setBookedTickets(List<Ticket> bookedTickets) {
        this.bookedTickets = bookedTickets;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    public int getNoOfSeats() {
        return noOfSeats;
    }

    public void setNoOfSeats(int noOfSeats) {
        this.noOfSeats = noOfSeats;
    }
}
