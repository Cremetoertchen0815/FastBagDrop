package dhbw.group2;

public class IDCardReader {
    public String readPin(IDCard card, EncryptionAlgorithm encryptionAlgorithm) {
        return card.chip.getEncryptedPin();
    }
}
