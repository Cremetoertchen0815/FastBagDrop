package dhbw.group2.plane.boarding;

public class BaggageTag {

    private static int counter = 0;
    private final int id = counter++;

    public int getId() {
        return id;
    }
}
