package dhbw.group2.plane.boarding;

public class Baggage {
    private String content;
    private BaggageTag tag;
    private float weight;
    public String getContent() {
        return content;
    }

    public Baggage(String content) {
        this.content = content;
    }
    public void attachTag(BaggageTag tag) {
        this.tag = tag;
    }

    public BaggageTag getTag() {
        return tag;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }
}
