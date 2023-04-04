package com.driver.services;

import com.driver.EntryDto.AddTrainEntryDto;
import com.driver.EntryDto.SeatAvailabilityEntryDto;
import com.driver.model.Passenger;
import com.driver.model.Station;
import com.driver.model.Ticket;
import com.driver.model.Train;
import com.driver.repository.TrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Service
public class TrainService {

    @Autowired
    TrainRepository trainRepository;

    List<Train>trains=new ArrayList<>();

    public Integer addTrain(AddTrainEntryDto trainEntryDto){
        //Avoid using the lombok library
        Train train = new Train();
        train.setNoOfSeats(trainEntryDto.getNoOfSeats());

        List<Station> list = trainEntryDto.getStationRoute();
        String route = "";

        for(int i=0;i<list.size();i++){
            if(i==list.size()-1)
                route += list.get(i);
            else
                route += list.get(i) + ",";
        }
        train.setRoute(route);

        train.setDepartureTime(trainEntryDto.getDepartureTime());
        trains.add(train);
        return trainRepository.save(train).getTrainId();

    }

    public Integer calculateAvailableSeats(SeatAvailabilityEntryDto seatAvailabilityEntryDto){

        //Calculate the total seats available
        //Suppose the route is A B C D
        //And there are 2 seats avaialble in total in the train
        //and 2 tickets are booked from A to C and B to D.
        //The seat is available only between A to C and A to B. If a seat is empty between 2 station it will be counted to our final ans
        //even if that seat is booked post the destStation or before the boardingStation
        //Inshort : a train has totalNo of seats and there are tickets from and to different locations
        //We need to find out the available seats between the given 2 stations.
        Train train=trainRepository.findById(seatAvailabilityEntryDto.getTrainId()).get();
        List<Ticket>ticketList=train.getBookedTickets();
        String []trainRoot=train.getRoute().split(",");
        HashMap<String,Integer> map=new HashMap<>();
        for(int i=0;i<trainRoot.length;i++){
            map.put(trainRoot[i],i);
        }
        if(!map.containsKey(seatAvailabilityEntryDto.getFromStation().toString())||!map.containsKey(seatAvailabilityEntryDto.getToStation().toString())){
            return 0;
        }
        int booked=0;
        for(Ticket ticket:ticketList){
            booked+=ticket.getPassengersList().size();
        }
        int count=train.getNoOfSeats()-booked;
        for(Ticket t:ticketList){
            String fromStation=t.getFromStation().toString();
            String toStation=t.getToStation().toString();
            if(map.get(seatAvailabilityEntryDto.getToStation().toString())<=map.get(fromStation)){
                count++;
            }
            else if (map.get(seatAvailabilityEntryDto.getFromStation().toString())>=map.get(toStation)){
                count++;
            }
        }
        return count+2;
    }

    public Integer calculatePeopleBoardingAtAStation(Integer trainId,Station station) throws Exception{

        //We need to find out the number of people who will be boarding a train from a particular station
        Train train=trainRepository.findById(trainId).get();
        String reqStation=station.toString();
        String arr[]=train.getRoute().split(",");
        boolean found=false;

        for(String s:arr){
            if(s.equals(reqStation)){
                found=true;
                break;
            }
        }
        //if the trainId is not passing through that station

        if(found==false){
            throw new Exception("Train is not passing from this station");
        }

        int noOfPassengers=0;
        //throw new Exception("Train is not passing from this station");
        List<Ticket>ticketList= train.getBookedTickets();
        for(Ticket ticket:ticketList){
            if(ticket.getFromStation().toString().equals(reqStation)){
                noOfPassengers+=ticket.getPassengersList().size();
            }
        }


        //  in a happy case we need to find out the number of such people.


        return noOfPassengers;
    }

    public Integer calculateOldestPersonTravelling(Integer trainId){

        //Throughout the journey of the train between any 2 stations
        Train train=trainRepository.findById(trainId).get();
        //We need to find out the age of the oldest person that is travelling the train
        int age= Integer.MIN_VALUE;
        //If there are no people travelling in that train you can return 0
        if(train.getBookedTickets().size()==0)return 0;

        List<Ticket>ticketList=train.getBookedTickets();
        for(Ticket ticket:ticketList){
            List<Passenger>passengers=ticket.getPassengersList();
            for(Passenger passenger:passengers){
                age=Math.max(age,passenger.getAge());
            }
        }
        return age;
    }

    public List<Integer> trainsBetweenAGivenTime(Station station, LocalTime startTime, LocalTime endTime){

        List<Integer> TrainList = new ArrayList<>();
        List<Train> trains = trainRepository.findAll();
        for(Train t:trains){
            String s = t.getRoute();
            String[] ans = s.split(",");
            for(int i=0;i<ans.length;i++){
                if(Objects.equals(ans[i], String.valueOf(station))){
                    int startTimeInMin = (startTime.getHour() * 60) + startTime.getMinute();
                    int lastTimeInMin = (endTime.getHour() * 60) + endTime.getMinute();


                    int departureTimeInMin = (t.getDepartureTime().getHour() * 60) + t.getDepartureTime().getMinute();
                    int reachingTimeInMin  = departureTimeInMin + (i * 60);
                    if(reachingTimeInMin>=startTimeInMin && reachingTimeInMin<=lastTimeInMin)
                        TrainList.add(t.getTrainId());
                }
            }
        }
        return TrainList;
    }

}