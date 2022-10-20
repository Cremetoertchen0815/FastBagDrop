package dhbw.group2;

public class IDCard {
    private String uuid;
    private IDCardStatus status;
    private RFIDChip chip;

    public String getEncryptedPin() {
        return chip.getEncryptedPin();
    }
}
