package com.gridnine.testing;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;

public class FlightsTwoHoursGroundTime extends AbstractFlightsList {
    List<Flight> flightsTwoHoursGroundTime = flightsList;

    public boolean checkRules(Flight flight) {
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

    @Override
    public void showFlight() {
        if (flightsTwoHoursGroundTime.size() > 0) {
            System.out.println("------ Время между сегментами более двух часов ------");
            Iterator<Flight> flight3 = flightsTwoHoursGroundTime.iterator();
            while (flight3.hasNext()) {
                System.out.println(flight3.next());
            }
        }
    }
}
