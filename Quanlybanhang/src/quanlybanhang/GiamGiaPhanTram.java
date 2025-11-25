package quanlybanhang;

public class GiamGiaPhanTram implements IKhuyenMai {
    private double phanTramGiam;

    public GiamGiaPhanTram(double phanTramGiam) {
        this.phanTramGiam = phanTramGiam;
    }

    @Override
    public double apDung(double giaTien) {
        return giaTien * (1.0 - this.phanTramGiam);
    }
}