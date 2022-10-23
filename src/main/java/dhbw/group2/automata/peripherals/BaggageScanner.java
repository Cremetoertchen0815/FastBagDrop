package dhbw.group2.automata.peripherals;

import dhbw.group2.automata.CentralConfig;
import dhbw.group2.plane.boarding.Baggage;

public class BaggageScanner {

    private final static String explosiveConst = "explosives";

    // Brute-force string search method
    private static boolean bruteForceScan(String string, String pattern) {
        int sLen = string.length();
        int pLen = pattern.length();
        for (int i = 0; i < sLen - pLen + 1; i++) {
            int j = 0;
            for (; j < pLen; j++) {
                if (string.charAt(i + j) != pattern.charAt(j))
                    break;
            }
            if (j == pLen) return true;
        }
        return false;
    }

    public boolean scanBaggageForExplosives(Baggage baggage) {
        //TODO: Implement scanning algorithms other than brute force(future task #2)
        return switch (CentralConfig.getInstance().baggageScanAlgorithm) {
            case BRUTE_FORCE -> bruteForceScan(baggage.getContent(), explosiveConst);
            default -> false;
        };
    }
}
