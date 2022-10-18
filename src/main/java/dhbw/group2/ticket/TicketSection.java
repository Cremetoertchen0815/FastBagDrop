package dhbw.group2.ticket;

public class TicketSection {
    public String name;
    public String flight;
    public BookingClass bookingClass;
    public String source;
    public String destination;
    public String date;
    public String id;
    public long sequence;

    public TicketSection Clone() {
        var res = new TicketSection();
        res.name = name;
        res.flight = flight;
        res.bookingClass = bookingClass;
        res.source = source;
        res.destination = destination;
        res.date = date;
        res.id = id;
        res.sequence = sequence;
        return res;
    }
}
