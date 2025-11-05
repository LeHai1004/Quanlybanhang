/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlybanhang;

import java.util.List;

/**
 *
 * @author HOANG HAI
 */
public class DonHang {
    private String maDonHang;
    
    // Association (Liên kết)
    private KhachHang khachHang;
    
    // Composition (Thành phần ⚫️)
    private List<ChiTietDonHang> danhSachChiTiet;
    
    // Association (Liên kết)
    private IKhuyenMai khuyenMai;

    public DonHang(String maDonHang, KhachHang khachHang, List<ChiTietDonHang> chiTiet) {
        this.maDonHang = maDonHang;
        this.khachHang = khachHang;
        this.danhSachChiTiet = chiTiet;
        this.khuyenMai = null;
    }

    public void apDungKhuyenMai(IKhuyenMai khuyenMai) {
        this.khuyenMai = khuyenMai;
    }

    public double tinhTongGiaTri() {
        double tong = 0;
        for (ChiTietDonHang ct : danhSachChiTiet) {
            tong += ct.tinhTongPhan();
        }

        // Đa hình
        if (khuyenMai != null) {
            tong = khuyenMai.apDung(tong);
        }
        return tong;
    }
    
    public String getMaDonHang() {
        return maDonHang;
    }
    
    public KhachHang getKhachHang() {
        return khachHang;
    }
    
    public List<ChiTietDonHang> getDanhSachChiTiet() {
        return danhSachChiTiet;
    }
}
