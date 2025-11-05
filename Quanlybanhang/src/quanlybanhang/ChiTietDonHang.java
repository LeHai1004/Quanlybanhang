/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlybanhang;

/**
 *
 * @author HOANG HAI
 */
public class ChiTietDonHang {
    // Association (Liên kết)
    private SanPham sanPham;
    private int soLuong;

    public ChiTietDonHang(SanPham sanPham, int soLuong) {
        this.sanPham = sanPham;
        this.soLuong = soLuong;
    }

    public double tinhTongPhan() {
        // Sử dụng phương thức getGiaBan() đa hình
        return sanPham.getGiaBan() * soLuong;
    }

    public SanPham getSanPham() {
        return sanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }
}
