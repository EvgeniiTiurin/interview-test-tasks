package com.gridnine.testing;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractFlightsList implements FlightList {
    public List<Flight> flightsList = new ArrayList<>();

    public void showFlight() {
    }

    public boolean checkRules(Segment segment) {
        return false;
    }

    public void add(Flight flight) {
        flightsList.add(flight);
    }
}
