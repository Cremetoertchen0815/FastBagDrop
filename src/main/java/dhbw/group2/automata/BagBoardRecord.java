package dhbw.group2.automata;

import dhbw.group2.humans.identification.Passport;
import dhbw.group2.plane.boarding.BaggageTag;
import dhbw.group2.plane.ticket.Ticket;

public record BagBoardRecord(int timestamp, Passport passport, Ticket ticket, BaggageTag baggageTag,
                             BagBoardResult result) {
    public BagBoardRecord {
    }
}
