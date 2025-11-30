package quanlybanhang;

import java.util.List;

public interface IQuanLyDonHang {
    void themDonHang(DonHang dh);
    List<DonHang> layTatCaDonHang();
    void xoaDonHang(String maDonHang, QuanTriVien admin);
    DonHang huyDonHang(String maDonHang);
    void setData(List<DonHang> dsDonHang);
}