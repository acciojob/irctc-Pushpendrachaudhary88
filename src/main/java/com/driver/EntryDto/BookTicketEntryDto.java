package com.driver.EntryDto;


import com.driver.model.Station;

import java.util.List;

public class BookTicketEntryDto {

    private List<Integer> passengerIds;


    private Integer bookingPersonId;

    private Integer trainId;


    private Station fromStation;

    private Station toStation;

    private int noOfSeats;

    public BookTicketEntryDto(List<Integer> passengerIds, Integer trainId, Station fromStation, Station toStation,int noOfSeats,Integer bookingPersonId) {
        this.passengerIds = passengerIds;
        this.trainId = trainId;
        this.fromStation = fromStation;
        this.toStation = toStation;
        this.noOfSeats = noOfSeats;
        this.bookingPersonId = bookingPersonId;
    }

    public int getNoOfSeats() {
        return noOfSeats;
    }

    public void setNoOfSeats(int noOfSeats) {
        this.noOfSeats = noOfSeats;
    }

    public BookTicketEntryDto() {
    }

    public List<Integer> getPassengerIds() {
        return passengerIds;
    }

    public void setPassengerIds(List<Integer> passengerIds) {
        this.passengerIds = passengerIds;
    }

    public Integer getTrainId() {
        return trainId;
    }

    public void setTrainId(Integer trainId) {
        this.trainId = trainId;
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

    public Integer getBookingPersonId() {
        return bookingPersonId;
    }

    public void setBookingPersonId(Integer bookingPersonId) {
        this.bookingPersonId = bookingPersonId;
    }
}
