package dhbw.group2.plane.ticket;

public class TicketSection {
    public String name;
    public String flight;
    public BookingClass bookingClass;
    public String source;
    public String destination;
    public String date;
    public String id;
    public long sequence;

    public TicketSection(String name, String flight, BookingClass bookingClass, String source, String destination, String date, String id, long sequence) {
        this.name = name;
        this.flight = flight;
        this.bookingClass = bookingClass;
        this.source = source;
        this.destination = destination;
        this.date = date;
        this.id = id;
        this.sequence = sequence;
    }

    public TicketSection Clone() {
        return new TicketSection(name, flight, bookingClass, source, destination, date, id, sequence);
    }
}
