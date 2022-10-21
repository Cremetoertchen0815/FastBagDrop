package dhbw.group2.automata;

import dhbw.group2.plane.boarding.BaggageTag;
import dhbw.group2.plane.ticket.Ticket;

public record BagBoardRecord(int timestamp, Ticket ticket, BaggageTag baggageTag, BagBoardResult result) {
    public BagBoardRecord {
    }
}
