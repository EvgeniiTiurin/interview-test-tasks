package com.gridnine.testing;

public interface FlightList {

    void showFlight();

    boolean checkRules(Segment segment);

    void add(Flight flight);
}
