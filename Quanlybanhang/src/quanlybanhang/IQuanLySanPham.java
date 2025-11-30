package quanlybanhang;

import java.util.List;

public interface IQuanLySanPham {
    void them(SanPham sp);
    void sua(String maSP, SanPham spMoi);
    void xoa(String maSP);
    List<SanPham> timKiemTheoTen(String ten);
    List<SanPham> layTatCa();
    SanPham timKiemTheoMa(String maSP);
    
    boolean kiemTraTonKho(String maSP, int soLuongMua);
    void capNhatTonKho(String maSP, int soLuongDaBan);
    void setData(List<SanPham> dsSanPham);
}