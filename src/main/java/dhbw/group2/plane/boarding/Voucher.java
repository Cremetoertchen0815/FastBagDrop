package dhbw.group2.plane.boarding;

import java.util.Objects;

public final class Voucher {
    private final String voucherType;

    public Voucher(String voucherType) {
        this.voucherType = voucherType;
    }

    public String voucherType() {
        return voucherType;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Voucher) obj;
        return Objects.equals(this.voucherType, that.voucherType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(voucherType);
    }

    @Override
    public String toString() {
        return "Voucher[" +
                "voucherType=" + voucherType + ']';
    }

}
