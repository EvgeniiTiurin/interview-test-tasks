/**
 * Array based storage for Resumes
 * @author EvgeniiTiurin
 * @version 0.1
 */

package com.gridnine.testing;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;

public class Main {
    static List<Flight> approvedFlights;
    static List<Flight> excludedFlights;

    public static void main(String[] args) {
        approvedFlights = null;
        excludedFlights = null;

        List<Flight> testflights = FlightBuilder.createFlights();   // создание тестового набора перелётов
        checkFlight(testflights);                                   // выдает результат обработки набора
    }

    static void checkFlight(List<Flight> flights) {
        Iterator iterator = flights.iterator();
        while (iterator.hasNext()) {
            Flight flight = (Flight) iterator.next();
            for (Segment segment : flight.getSegments()) {
                if (segment.getArrivalDate().isBefore(LocalDateTime.now()) || segment.getDepartureDate().isBefore(LocalDateTime.now())) {
                    excludedFlights.add(flight);
                } else {
                    approvedFlights.add(flight);
                }
            }
        }
        showAllFlights();
    }

    static void checkNextSegment() {
    }

    static void showAllFlights() {
        System.out.println("------ Разрешённые рейсы ------");
        Iterator<Flight> flightIterator = approvedFlights.iterator();
        while (flightIterator.hasNext()) {
            System.out.println(flightIterator.next());
        }
        System.out.println("------ Отменённые рейсы ------");
        Iterator<Flight> flight = excludedFlights.iterator();
        while (flight.hasNext()) {
            System.out.println(flight.next());
        }
    }
}
