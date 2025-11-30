package quanlybanhang;

import java.util.ArrayList;
import java.util.List;

public class GioHang {
    private List<ChiTietDonHang> danhSachChiTiet;

    public GioHang() {
        this.danhSachChiTiet = new ArrayList<>();
    }

    public void themSanPham(SanPham sp, int soLuong) {
        ChiTietDonHang chiTietMoi = new ChiTietDonHang(sp, soLuong);
        this.danhSachChiTiet.add(chiTietMoi);
        System.out.println("Da them " + soLuong + " " + sp.getTenSP() + " vao gio.");
    }

    public List<ChiTietDonHang> getDanhSachChiTiet() {
        return new ArrayList<>(this.danhSachChiTiet);
    }

    public void lamTrongGio() {
        this.danhSachChiTiet.clear();
    }

    public boolean isEmpty() {
        return this.danhSachChiTiet.isEmpty();
    }
}