package dhbw.group2.ticket;

public class AdvancedTicketSection extends TicketSection{
    public String gate;
    public String boardingTime;

    @Override
    public AdvancedTicketSection Clone() {

        var res = new AdvancedTicketSection();
        res.name = name;
        res.flight = flight;
        res.bookingClass = bookingClass;
        res.source = source;
        res.destination = destination;
        res.date = date;
        res.id = id;
        res.sequence = sequence;
        res.gate = gate;
        res.boardingTime = boardingTime;
        return res;
    }
}
