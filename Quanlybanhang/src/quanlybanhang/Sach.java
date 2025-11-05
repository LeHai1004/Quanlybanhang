/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlybanhang;

/**
 *
 * @author HOANG HAI
 */
public class Sach extends SanPham {
    private String tacGia;
    private int soTrang;

    public Sach(String maSP, String tenSP, double giaGoc, String tacGia, int soTrang, int soLuongTon) {
        // Cập nhật super() để truyền soLuongTon
        super(maSP, tenSP, giaGoc, soLuongTon);
        this.tacGia = tacGia;
        this.soTrang = soTrang;
    }

    @Override
    public double tinhThue() {
        return 0.0; // Sách thuế 0%
    }
}
