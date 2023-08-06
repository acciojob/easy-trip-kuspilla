package com.driver.controllers;


import com.driver.model.Airport;
import com.driver.model.City;
import com.driver.model.Flight;
import com.driver.model.Passenger;
import com.driver.controllers.AirportRepository;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AirportService {


    AirportRepository repository_ob = new AirportRepository();

    public String addFlight(Flight flight) {

        String ans = repository_ob.addFlight(flight);
        return ans;

    }

    //================================================//
    public String getLargestAirportName() {
        String ans = repository_ob.getLargestAirport();
        return ans;
    }

    //==================================================//
    public double getShortestDurationOfPossibleBetweenTwoCities(City fromcity, City tocity) {
        double ans = repository_ob.getShortestDurationPossibleBetweenTwoCities(fromcity, tocity);
        return ans;

    }
    //=======================================================================================//

    public int getNumberOfPeopleOn(Date date, String airportname) {

        int ans = repository_ob.getNumberOfPeopleOn(date, airportname);
        return ans;
    }
    //===================================================================//

    public int calculateFlightFare(Integer flightId) {

        int ans = repository_ob.calcluateFlightFare(flightId);
        return ans;
    }

    //========================================================//
    public String bookATicket(Integer flightId, Integer passengerId) {

        String ans = repository_ob.bookTicket(flightId, passengerId);
        return ans;
    }
    //=============================================================//

    public String cancelATicket(Integer flightId, Integer passengerId) {

        String ans = repository_ob.cancelTicket(flightId, passengerId);
        return ans;
    }
    //================================================================//

     public int countOfBookingsDoneByPassengerAllCombined(Integer passengerId){
        int ans = repository_ob.countBookingsofPassenger(passengerId);
        return ans;
     }

    public String add_airport(Airport airport) {


        repository_ob.addAirport(airport);
        return "SUCCESS";


    }
    //=================================//

     public String getAirportNameFromFlightId(Integer flightId){
        String ans = repository_ob.getAirportNameFromFlightId(flightId);
        return ans;
     }

     //========================================================//

    public int calculateRevenueOfAFlight(Integer flightId){
        int ans = repository_ob.calculateRevenueofaFlight(flightId);
        return ans;
    }
    //===========================================================//
    public String add_passenger(Passenger passenger) {

        String ans = repository_ob.addPassenger(passenger);
        return ans;
    }

}

    //==============================================//


