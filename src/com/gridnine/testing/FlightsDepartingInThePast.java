package com.gridnine.testing;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;

public class FlightsDepartingInThePast extends AbstractFlightsList {
    List<Flight> flightsDepartingInThePast = flightsList;

    @Override
    public void showFlight() {
        if (flightsDepartingInThePast.size() > 0) {
            System.out.println("------ Вылет до текущего момента ------");
            Iterator<Flight> flight1 = flightsDepartingInThePast.iterator();
            while (flight1.hasNext()) {
                System.out.println(flight1.next());
            }
        }
    }

    @Override
    public boolean checkRules(Segment segment) {
        if (segment.getDepartureDate().isBefore(LocalDateTime.now())) {
            return true;
        }
        return false;
    }
}
