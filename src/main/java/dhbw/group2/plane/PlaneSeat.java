package dhbw.group2.plane;

import java.util.Objects;

public final class PlaneSeat {
    private final int row;
    private final char seat;

    public PlaneSeat(int row, char seat) {
        this.row = row;
        this.seat = seat;
    }

    public int row() {
        return row;
    }

    public char seat() {
        return seat;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (PlaneSeat) obj;
        return this.row == that.row &&
                this.seat == that.seat;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, seat);
    }

    @Override
    public String toString() {
        return "PlaneSeat[" +
                "row=" + row + ", " +
                "seat=" + seat + ']';
    }

}
