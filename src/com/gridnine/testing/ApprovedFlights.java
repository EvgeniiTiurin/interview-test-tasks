package com.gridnine.testing;

import java.util.Iterator;
import java.util.List;

public class ApprovedFlights extends AbstractFlightsList {
    List<Flight> approvedFlights = flightsList;

    @Override
    public void showFlight() {
        if (approvedFlights.size() > 0) {
            System.out.println("------ Разрешённые рейсы ------");
            Iterator<Flight> flightIterator = approvedFlights.iterator();
            while (flightIterator.hasNext()) {
                System.out.println(flightIterator.next());
            }
        }
    }
}
