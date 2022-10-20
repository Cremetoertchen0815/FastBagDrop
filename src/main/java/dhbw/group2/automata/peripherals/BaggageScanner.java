package dhbw.group2.automata.peripherals;

public class BaggageScanner {


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
