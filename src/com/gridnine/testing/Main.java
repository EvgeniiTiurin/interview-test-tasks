/**
 * Array based storage for Resumes
 *
 * @author EvgeniiTiurin
 * @version 0.1
 */

package com.gridnine.testing;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Main {
    static List<Flight> approvedFlights;
    static List<Flight> flightsDepartingInThePast;
    static List<Flight> flightsDepartsBeforeArrives;
    static List<Flight> flightsTwoHoursGroundTime;

    public static void main(String[] args) {
        approvedFlights = new ArrayList<>();
        flightsDepartingInThePast = new ArrayList<>();
        flightsDepartsBeforeArrives = new ArrayList<>();
        flightsTwoHoursGroundTime = new ArrayList<>();

        List<Flight> testflights = FlightBuilder.createFlights();   // создание тестового набора перелётов
        checkFlight(testflights);                                   // выдает результат обработки набора
    }

    static void checkFlight(List<Flight> flights) {
        Iterator iterator = flights.iterator();
        while (iterator.hasNext()) {
            Flight flight = (Flight) iterator.next();
            for (Segment segment : flight.getSegments()) {
                if (checkFlightDepartingInThePast(segment)) {
                    flightsDepartingInThePast.add(flight);

                    break;
                }
                if (checkFlightDepartsBeforeArrives(segment)) {
                    flightsDepartsBeforeArrives.add(flight);
                    break;
                }
                if (checkFlightTwoHoursGroundTime(flight)) {
                    flightsTwoHoursGroundTime.add(flight);
                    break;
                }
                approvedFlights.add(flight);
                break;
            }
        }
        showAllFlights();
    }

    static boolean checkFlightDepartingInThePast(Segment segment) {
        if (segment.getDepartureDate().isBefore(LocalDateTime.now())) {
            return true;
        }
        return false;
    }

    static boolean checkFlightDepartsBeforeArrives(Segment segment) {
        if (segment.getArrivalDate().isBefore(segment.getDepartureDate())) {
            return true;
        }
        return false;
    }

    static boolean checkFlightTwoHoursGroundTime(Flight flight) {
        Segment prevSegment = new Segment(LocalDateTime.now(), flight.getSegments().get(0).getArrivalDate());
        Iterator iterator = flight.getSegments().iterator();
        while (iterator.hasNext()) {
            Segment segment = (Segment) iterator.next();
            if (Duration.between(prevSegment.getArrivalDate(), segment.getDepartureDate()).toHours() > 2) {
                return true;
            }
            prevSegment = segment;
        }
        return false;
    }

    static void showAllFlights() {
        if (approvedFlights.size() > 0) {
            System.out.println("------ Разрешённые рейсы ------");
            Iterator<Flight> flightIterator = approvedFlights.iterator();
            while (flightIterator.hasNext()) {
                System.out.println(flightIterator.next());
            }
        }

        if (flightsDepartingInThePast.size() > 0) {
            System.out.println("------ Вылет до текущего момента ------");
            Iterator<Flight> flight1 = flightsDepartingInThePast.iterator();
            while (flight1.hasNext()) {
                System.out.println(flight1.next());
            }
        }

        if (flightsDepartsBeforeArrives.size() > 0) {
            System.out.println("------ Дата прилёта раньше даты вылета ------");
            Iterator<Flight> flight2 = flightsDepartsBeforeArrives.iterator();
            while (flight2.hasNext()) {
                System.out.println(flight2.next());
            }
        }

        if (flightsTwoHoursGroundTime.size() > 0) {
            System.out.println("------ Время между сегментами более двух часов ------");
            Iterator<Flight> flight3 = flightsTwoHoursGroundTime.iterator();
            while (flight3.hasNext()) {
                System.out.println(flight3.next());
            }
        }
    }
}
