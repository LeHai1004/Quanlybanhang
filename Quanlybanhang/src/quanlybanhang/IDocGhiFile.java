package quanlybanhang;

import java.util.List;

public interface IDocGhiFile {
    List<SanPham> docSanPham();
    List<KhachHang> docKhachHang();
    List<DonHang> docDonHang(List<SanPham> dsSP, List<KhachHang> dsKH);

    void luuSanPham(List<SanPham> dsSanPham);
    void luuKhachHang(List<KhachHang> dsKhachHang);
    void luuDonHang(List<DonHang> dsDonHang);
}