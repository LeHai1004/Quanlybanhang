package quanlybanhang;

import java.util.List;

public interface IQuanLyDonHang {
    void themDonHang(DonHang dh);
    List<DonHang> layTatCaDonHang();
    void xoaDonHang(String maDonHang, QuanTriVien admin);
    void setData(List<DonHang> dsDonHang);
    
    // --- THEM MOI ---
    DonHang huyDonHang(String maDonHang);
}