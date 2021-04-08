package com.gridnine.testing;

import java.util.Iterator;
import java.util.List;

public class FlightsDepartsBeforeArrives extends AbstractFlightsList {
    List<Flight> flightsDepartsBeforeArrives = flightsList;
    @Override
    public void showFlight() {
        if (flightsDepartsBeforeArrives.size() > 0) {
            System.out.println("------ Дата прилёта раньше даты вылета ------");
            Iterator<Flight> flight2 = flightsDepartsBeforeArrives.iterator();
            while (flight2.hasNext()) {
                System.out.println(flight2.next());
            }
        }
    }

    @Override
    public boolean checkRules(Segment segment) {
        if (segment.getArrivalDate().isBefore(segment.getDepartureDate())) {
            return true;
        }
        return false;
    }
}
