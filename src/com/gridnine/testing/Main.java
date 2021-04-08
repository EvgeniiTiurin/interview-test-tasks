/**
 * Flights and mistakes
 *
 * @author EvgeniiTiurin
 * @version 2.0
 */

package com.gridnine.testing;

import java.util.Iterator;
import java.util.List;

public class Main {
    private final static FlightsDepartingInThePast flightDepartingInThePast = new FlightsDepartingInThePast();
    private final static FlightsDepartsBeforeArrives flightsDepartsBeforeArrives = new FlightsDepartsBeforeArrives();
    private final static FlightsTwoHoursGroundTime flightsTwoHoursGroundTime = new FlightsTwoHoursGroundTime();
    private final static ApprovedFlights approvedFlights = new ApprovedFlights();

    public static void main(String[] args) {
        List<Flight> testflights = FlightBuilder.createFlights();   // создание тестового набора перелётов
        checkFlight(testflights);                                   // результат обработки набора
    }

    static void checkFlight(List<Flight> flights) {
        Iterator iterator = flights.iterator();
        while (iterator.hasNext()) {
            Flight flight = (Flight) iterator.next();
            for (Segment segment : flight.getSegments()) {
                if (flightDepartingInThePast.checkRules(segment)) {
                    flightDepartingInThePast.add(flight);
                    break;
                }

                if (flightsDepartsBeforeArrives.checkRules(segment)) {
                    flightsDepartsBeforeArrives.add(flight);
                    break;
                }

                if (flightsTwoHoursGroundTime.checkRules(flight)) {
                    flightsTwoHoursGroundTime.add(flight);
                    break;
                }
                approvedFlights.add(flight);
                break;
            }
        }
        showAllFlights();
    }

    /*
     * Ниже происходит вывод списка рейсов, после проверки на длину списка
     */

    static void showAllFlights() {
        approvedFlights.showFlight();
        flightsTwoHoursGroundTime.showFlight();
        flightDepartingInThePast.showFlight();
        flightsDepartsBeforeArrives.showFlight();
    }
}
