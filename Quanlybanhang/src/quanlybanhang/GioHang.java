/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlybanhang;

/**
 *
 * @author HOANG HAI
 */
import java.util.ArrayList;
import java.util.List;

public class GioHang {
    
    // Composition (Thành phần ⚫️)
    private List<ChiTietDonHang> danhSachChiTiet;

    public GioHang() {
        this.danhSachChiTiet = new ArrayList<>();
    }

    public void themSanPham(SanPham sp, int soLuong) {
        ChiTietDonHang chiTietMoi = new ChiTietDonHang(sp, soLuong);
        this.danhSachChiTiet.add(chiTietMoi);
        System.out.println("Đã thêm " + soLuong + " " + sp.getTenSP() + " vào giỏ hàng.");
    }

    public double tinhTongTien() {
        double tong = 0;
        for (ChiTietDonHang ct : danhSachChiTiet) {
            tong += ct.tinhTongPhan();
        }
        return tong;
    }
    
    public List<ChiTietDonHang> getDanhSachChiTiet() {
        // Trả về bản sao
        return new ArrayList<>(this.danhSachChiTiet); 
    }
    
    public void lamTrongGio() {
        this.danhSachChiTiet.clear();
        System.out.println("Giỏ hàng đã được làm rỗng.");
    }

    public boolean isEmpty() {
        return this.danhSachChiTiet.isEmpty();
    }
}
