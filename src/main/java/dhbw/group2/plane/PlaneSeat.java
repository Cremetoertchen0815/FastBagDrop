package dhbw.group2.plane;

public record PlaneSeat(int row, char seat) {
    public PlaneSeat(int row, char seat) {
        this.row = row;
        this.seat = seat;
    }
}
