package dhbw.group2.automata.peripherals;

import dhbw.group2.plane.boarding.Baggage;

public class BaggageScanner {

    private final static String explosiveConst = "explosives";

    public boolean scanBaggageForExplosives(Baggage baggage) {
        return stringSearch(baggage.getContent(), explosiveConst);
    }

    // Brute-force string search method
    private static boolean stringSearch(String string, String pattern) {
        int sLen = string.length();
        int pLen = pattern.length();
        for(int i = 0; i < sLen - pLen + 1; i++) {
            int j = 0;
            for (; j < pLen; j++) {
                if (string.charAt(i + j) != pattern.charAt(j))
                    break;
            }
            if (j == pLen) return true;
        }
        return false;
    }
}
