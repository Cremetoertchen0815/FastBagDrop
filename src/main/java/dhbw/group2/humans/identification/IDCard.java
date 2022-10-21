package dhbw.group2.humans.identification;

public class IDCard {
    private String uuid;
    private IDCardStatus status;
    private RFIDChip chip = new RFIDChip("1234");

    public String getEncryptedPin() {
        return chip.getEncryptedPin();
    }

    public String getUuid() {
        return uuid;
    }

    public IDCardStatus getStatus() {
        return status;
    }

    public void setStatus(IDCardStatus status) {
        this.status = status;
    }
}
