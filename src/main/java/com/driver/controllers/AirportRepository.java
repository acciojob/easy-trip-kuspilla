package com.driver.controllers;


import com.driver.model.City;
import com.driver.model.Flight;
import com.driver.model.Passenger;
import com.driver.model.Airport;
import io.swagger.models.auth.In;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class AirportRepository {
    HashMap<Integer, Passenger> passengerDB = new HashMap<>();
    HashMap<Integer,Integer> cancelTicketDB = new HashMap<>();
    HashMap<String, Airport> airportDB = new HashMap<>();
    HashMap<Integer, Flight> flightDB = new HashMap<>();
    HashMap<Integer, List<Integer>> flightId_vs_passengerId_DB = new HashMap<>();
    HashMap<Integer, List<Integer>> passengerId_vs_flightId_DB = new HashMap<>();

    public String add_airport(Airport airport){

        try {
            String airport_name = airport.getAirportName();
            if (airportDB.containsKey(airport_name)) {
                return "FAILURE";
            }
            else{
                airportDB.put(airport_name, airport);
                return "SUCCESS";
            }
        }
        catch (Exception e){
            return "FAILURE";
        }
    }
    //====================================================================//

    public String GetLargestAirportName(){
        List<String> names = new ArrayList<>();
        int num_of_terminals = 0;
        for( String ele :airportDB.keySet()){
            int avialabe_terminal = airportDB.get(ele).getNoOfTerminals();
            if( avialabe_terminal >= num_of_terminals){
                num_of_terminals = avialabe_terminal;
                names.add( ele);
            }

        }
        Collections.sort(names);
        return names.get(0);
    }
    //=======================================================================//
    public double getShortestDurationOfPossibleBetweenTwoCities(City fromcity, City tocity){
        double min_time = Double.MAX_VALUE;
        for(Integer ele : flightDB.keySet()){
            Flight flight = flightDB.get(ele);
            if( flight.getFromCity().equals(fromcity) && flight.getToCity().equals(tocity)){
                double time = flight.getDuration();
                min_time = Math.min(time, min_time);
            }
        }
        return min_time == Double.MAX_VALUE ? -1:min_time;
    }
    //===================================================//

    public int getNumberOfPeopleOn(Date date, String airport_name){
        if( airportDB.get(airport_name) == null) return 0;
        City curr_city = airportDB.get(airport_name).getCity();
        int count =0;

            for( int id : flightDB.keySet()){
                Flight flight = flightDB.get(id);
                if( flight.getFromCity().equals(curr_city)){
                    if( flight.getFlightDate().equals(date)){
                        count += flightId_vs_passengerId_DB.get(id).size();

                    }
                }
            }
            return count;
    }

    //======================================================//

    public int calculaateFlightFare(Integer flightId){
        int number_of_people_already_booked = flightId_vs_passengerId_DB.get(flightId).size();
        int fare = 0;
        int total = 3000 + number_of_people_already_booked * 50;
        return total;
    }

    //===========================================================//

    public String bookingTicket(Integer flightId, Integer passengerId ){

        if( passengerId_vs_flightId_DB.get(flightId) != null && flightId_vs_passengerId_DB.get(flightId).size() == flightDB.get(flightId).getMaxCapacity()){
             return "FAILURE";
        }

        // check alredy booking or not
      if( flightId_vs_passengerId_DB.get(passengerId) != null){

          for( int i : passengerId_vs_flightId_DB.get(passengerId)){
              if( i == flightId) return "FAILURE";
          }
      }
      flightId_vs_passengerId_DB.putIfAbsent(flightId ,new ArrayList<>());
      flightId_vs_passengerId_DB.get(flightId).add(passengerId);
      passengerId_vs_flightId_DB.putIfAbsent(flightId, new ArrayList<>());
      passengerId_vs_flightId_DB.get(flightId).add(passengerId);
       return "SUCCESS";
    }

    //==================================================================//

    public String cancelATicket(Integer flightId, Integer passengerId){
        if(flightDB.get(flightId) == null  ) return "FAILURE";
        boolean foundleft = false;
        if( passengerId_vs_flightId_DB.get(passengerId) == null ) return "FAILURE";

        for( Integer ele : passengerId_vs_flightId_DB.get(flightId)){
           foundleft = true;
           break;
        }

        if( foundleft){
            cancelTicketDB.put(flightId, passengerId);
            passengerId_vs_flightId_DB.get(passengerId).remove(flightId);
            flightId_vs_passengerId_DB.get(flightId).remove(passengerId);
            return "SUCCESS";
        }else{
            return "FAILURE";
        }
    }

    //=============================================================//

    public int countOfBookingsDoneByPassengerAllCombined(Integer passengerId){
        int count =0;
        for( List<Integer> passnger : flightId_vs_passengerId_DB.values()){
            if( passnger.contains(passengerId)){
                count++;
            }
        }
        return count;

    }
     //==============================================================//


    public String add_flight(Flight flight){

        try {
            Integer flightId = flight.getFlightId();
            if (airportDB.containsKey(flightId)) {
                return "FAILURE";
            }
            else{

                flightDB.put(flightId, flight);
                return "SUCCESS";
            }
        }
        catch (Exception e){
            return "FAILURE";
        }
    }

 //============================================================================//

    public String getAirportNameFromFlightId(Integer flightId){
           if( flightDB.containsKey(flightId)){
               City city = flightDB.get(flightId).getFromCity();
            for( Airport airport : airportDB.values()){
                if( airport.getCity().equals(city)){
                    return airport.getAirportName();
                }
            }
            return null;
           }else{
               return null;
           }
    }

    //=======================================================================//

    public int calculateRevenueOfAFlight(Integer flightId){
        int caluclateFair = cancelTicketDB.getOrDefault(flightId, 1)*50;
        return calculaateFlightFare(flightId) -caluclateFair;
    }

    public String add_passenger(Passenger passenger){

        try {
            Integer passengerId = passenger.getPassengerId();
            if (passengerDB.containsKey(passengerId)) {
                return "FAILURE";
            }
            else{
                passengerDB.put(passengerId, passenger);
                return "SUCCESS";
            }
        }
        catch (Exception e){
            return "FAILURE";
        }
    }
    //===============================================================//

    public String getLargestAirportName_helper(){
        int max =0;
         String Airport_name = "";
        if( airportDB.isEmpty()) return "";
        for( Airport airport: airportDB.values()){
            int terninal = airport.getNoOfTerminals();
            if( max < terninal){
                max = terninal;
                Airport_name = airport.getAirportName();
            }
            else if( terninal == airport.getNoOfTerminals()){
                if( Airport_name.compareTo( airport.getAirportName()) > 0){
                    Airport_name = airport.getAirportName();
                }
            }
        }
      return Airport_name;
    }

    //=====================================================================//


}












