package dhbw.group2.peripherals;

import dhbw.group2.EncryptionAlgorithm;
import dhbw.group2.IDCard;

public class IDCardReader {
    public String readPin(IDCard card, EncryptionAlgorithm encryptionAlgorithm) {
        return card.chip.getEncryptedPin();
    }
}
