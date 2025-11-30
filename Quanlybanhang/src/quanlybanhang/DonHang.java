package quanlybanhang;

import java.util.ArrayList;
import java.util.List;

public class DonHang {
    private String maDonHang;
    private KhachHang khachHang;
    private List<ChiTietDonHang> danhSachChiTiet;
    private GiamGiaPhanTram khuyenMai;
    private String trangThai; // "Da Dat" hoac "Da Huy"

    public DonHang(String maDonHang, KhachHang khachHang, List<ChiTietDonHang> chiTiet) {
        this.maDonHang = maDonHang;
        this.khachHang = khachHang;
        this.danhSachChiTiet = chiTiet;
        this.khuyenMai = null;
        this.trangThai = "Da Dat";
    }

    public void themChiTiet(ChiTietDonHang ct) {
        if (this.danhSachChiTiet == null) {
            this.danhSachChiTiet = new ArrayList<>();
        }
        this.danhSachChiTiet.add(ct);
    }

    public double tinhTongGiaTri() {
        double tong = 0;
        for (ChiTietDonHang ct : danhSachChiTiet) {
            tong += ct.tinhTongPhan();
        }
        if (khuyenMai != null) {
            tong = khuyenMai.apDung(tong);
        }
        return tong;
    }

    public String getMaDonHang() { return maDonHang; }
    public KhachHang getKhachHang() { return khachHang; }
    public List<ChiTietDonHang> getDanhSachChiTiet() { return danhSachChiTiet; }
    
    public String getTrangThai() { return trangThai; }
    public void setTrangThai(String trangThai) { this.trangThai = trangThai; }
}