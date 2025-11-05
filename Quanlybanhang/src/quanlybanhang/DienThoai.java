/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlybanhang;

/**
 *
 * @author HOANG HAI
 */
public class DienThoai extends SanPham {
    private String hangSX;
    private int dungLuongPin;

    public DienThoai(String maSP, String tenSP, double giaGoc, String hangSX, int dungLuongPin, int soLuongTon) {
        // Cập nhật super() để truyền soLuongTon
        super(maSP, tenSP, giaGoc, soLuongTon);
        this.hangSX = hangSX;
        this.dungLuongPin = dungLuongPin;
    }

    @Override
    public double tinhThue() {
        return getGiaGoc() * 0.1; // Điện thoại thuế 10%
    }
}
