package dhbw.group2.automata.peripherals;

import dhbw.group2.EncryptionAlgorithm;
import dhbw.group2.humans.identification.IDCard;

public class IDCardReader {
    public String readPin(IDCard card, EncryptionAlgorithm encryptionAlgorithm) {
        return card.chip.getEncryptedPin();
    }
}
