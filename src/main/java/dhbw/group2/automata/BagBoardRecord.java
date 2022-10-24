package dhbw.group2.automata;

import dhbw.group2.humans.identification.Passport;
import dhbw.group2.plane.boarding.BaggageTag;
import dhbw.group2.plane.ticket.Ticket;

import java.util.Objects;

public final class BagBoardRecord {
    private final int timestamp;
    private final Passport passport;
    private final Ticket ticket;
    private final BaggageTag baggageTag;
    private final BagBoardResult result;

    public BagBoardRecord(int timestamp, Passport passport, Ticket ticket, BaggageTag baggageTag, BagBoardResult result) {
        this.timestamp = timestamp;
        this.passport = passport;
        this.ticket = ticket;
        this.baggageTag = baggageTag;
        this.result = result;
    }

    public int timestamp() {
        return timestamp;
    }

    public Passport passport() {
        return passport;
    }

    public Ticket ticket() {
        return ticket;
    }

    public BaggageTag baggageTag() {
        return baggageTag;
    }

    public BagBoardResult result() {
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (BagBoardRecord) obj;
        return this.timestamp == that.timestamp &&
                Objects.equals(this.passport, that.passport) &&
                Objects.equals(this.ticket, that.ticket) &&
                Objects.equals(this.baggageTag, that.baggageTag) &&
                Objects.equals(this.result, that.result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timestamp, passport, ticket, baggageTag, result);
    }

    @Override
    public String toString() {
        return "BagBoardRecord[" +
                "timestamp=" + timestamp + ", " +
                "passport=" + passport + ", " +
                "ticket=" + ticket + ", " +
                "baggageTag=" + baggageTag + ", " +
                "result=" + result + ']';
    }

}
