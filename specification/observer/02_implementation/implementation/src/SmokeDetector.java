import java.util.ArrayList;

public class SmokeDetector {
    private final ArrayList<ISmokeDetectorListener> listenerList;

    public SmokeDetector() {
        listenerList = new ArrayList<>();
    }

    public void falseAlarm() {
        for (ISmokeDetectorListener listener : listenerList) {
            listener.fireIgnited("SmokeDetector #1");
        }
    }

    public void addListener(ISmokeDetectorListener listener) {
        listenerList.add(listener);
    }

    public void removeListener(ISmokeDetectorListener listener) {
        listenerList.remove(listener);
    }
}