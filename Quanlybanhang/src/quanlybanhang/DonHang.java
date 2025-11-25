package quanlybanhang;

import java.util.List;

public class DonHang {
    private String maDonHang;
    private KhachHang khachHang;
    private List<ChiTietDonHang> danhSachChiTiet;
    private GiamGiaPhanTram khuyenMai; // Noi thang

    public DonHang(String maDonHang, KhachHang khachHang, List<ChiTietDonHang> chiTiet) {
        this.maDonHang = maDonHang;
        this.khachHang = khachHang;
        this.danhSachChiTiet = chiTiet;
        this.khuyenMai = null;
    }
    
    // Ham ho tro de DocGhiFileImpl su dung
    public void themChiTiet(ChiTietDonHang ct) {
        this.danhSachChiTiet.add(ct);
    }

    public void apDungKhuyenMai(GiamGiaPhanTram khuyenMai) {
        this.khuyenMai = khuyenMai;
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
}