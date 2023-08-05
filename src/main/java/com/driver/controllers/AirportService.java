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

    public String add_flight(Flight flight) {

        String ans = repository_ob.add_flight(flight);
        return ans;

    }

    //================================================//
    public String getLargestAirportName() {
        String ans = repository_ob.getLargestAirportName_helper();
        return ans;
    }

    //==================================================//
    public double getShortestDurationOfPossibleBetweenTwoCities(City fromcity, City tocity) {
        double ans = repository_ob.getShortestDurationOfPossibleBetweenTwoCities(fromcity, tocity);
        return ans;

    }
    //=======================================================================================//

    public int getNumberOfPeopleOn(Date date, String airportname) {

        int ans = repository_ob.getNumberOfPeopleOn(date, airportname);
        return ans;
    }
    //===================================================================//

    public int calculateFlightFare(Integer flightId) {

        int ans = repository_ob.calculaateFlightFare(flightId);
        return ans;
    }

    //========================================================//
    public String bookATicket(Integer flightId, Integer passengerId) {

        String ans = repository_ob.bookingTicket(flightId, passengerId);
        return ans;
    }
    //=============================================================//

    public String cancelATicket(Integer flightId, Integer passengerId) {

        String ans = repository_ob.cancelATicket(flightId, passengerId);
        return ans;
    }
    //================================================================//

    public String add_airport(Airport airport) {


        String ans = repository_ob.add_airport(airport);
        return ans;


    }
    //=================================//

    public String add_passenger(Passenger passenger) {

        String ans = repository_ob.add_passenger(passenger);
        return ans;
    }

}

    //==============================================//


