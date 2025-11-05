/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlybanhang;

/**
 *
 * @author HOANG HAI
 */
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
