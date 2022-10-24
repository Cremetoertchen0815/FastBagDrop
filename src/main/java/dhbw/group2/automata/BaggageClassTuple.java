package dhbw.group2.automata;

import dhbw.group2.plane.boarding.Baggage;
import dhbw.group2.plane.ticket.BookingClass;

import java.util.Objects;

public final class BaggageClassTuple {
    private final Baggage baggage;
    private final BookingClass bookingClass;

    public BaggageClassTuple(Baggage baggage, BookingClass bookingClass) {

        this.baggage = baggage;
        this.bookingClass = bookingClass;
    }

    public Baggage baggage() {
        return baggage;
    }

    public BookingClass bookingClass() {
        return bookingClass;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (BaggageClassTuple) obj;
        return Objects.equals(this.baggage, that.baggage) &&
                Objects.equals(this.bookingClass, that.bookingClass);
    }

    @Override
    public int hashCode() {
        return Objects.hash(baggage, bookingClass);
    }

    @Override
    public String toString() {
        return "BaggageClassTuple[" +
                "baggage=" + baggage + ", " +
                "bookingClass=" + bookingClass + ']';
    }

}
