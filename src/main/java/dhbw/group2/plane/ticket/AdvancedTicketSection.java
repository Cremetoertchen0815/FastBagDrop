package dhbw.group2.plane.ticket;

public class AdvancedTicketSection extends TicketSection {
    public String gate;
    public String boardingTime;

    public AdvancedTicketSection(String name, String flight, BookingClass bookingClass, String source, String destination, String date, String id, long sequence, String gate, String boardingTime) {
        super(name, flight, bookingClass, source, destination, date, id, sequence);
        this.gate = gate;
        this.boardingTime = boardingTime;
    }

    @Override
    public AdvancedTicketSection Clone() {
        return new AdvancedTicketSection(name, flight, bookingClass, source, destination, date, id, sequence, gate, boardingTime);
    }
}
