package com.driver.EntryDto;

import com.driver.model.Station;

import java.time.LocalTime;
import java.util.List;

public class AddTrainEntryDto {

    private List<Station> stationRoute; //The items in the list denote the  order in which the train will move

    private LocalTime departureTime;

    private int noOfSeats;

    public List<Station> getStationRoute() {
        return stationRoute;
    }

    public AddTrainEntryDto(List<Station> stationRoute, LocalTime departureTime, int noOfSeats) {
        this.stationRoute = stationRoute;
        this.departureTime = departureTime;
        this.noOfSeats = noOfSeats;
    }

    public AddTrainEntryDto() {
    }

    public void setStationRoute(List<Station> stationRoute) {
        this.stationRoute = stationRoute;
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
