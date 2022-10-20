package dhbw.group2.plane.boarding;

public class Baggage {
    private String content;
    private BaggageTag tag;
    public String getContent() {
        return content;
    }

    public void attachTag(BaggageTag tag) {
        this.tag = tag;
    }

    public BaggageTag getTag() {
        return tag;
    }
}
