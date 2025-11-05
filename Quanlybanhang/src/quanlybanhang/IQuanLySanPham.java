/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package quanlybanhang;

import java.util.List;

/**
 *
 * @author HOANG HAI
 */
public interface IQuanLySanPham {
    // CRUD
    void them(SanPham sp);
    void sua(String maSP, SanPham spMoi);
    void xoa(String maSP);
    List<SanPham> timKiemTheoTen(String ten);
    List<SanPham> layTatCa();
    SanPham timKiemTheoMa(String maSP);

    // Chức năng nghiệp vụ
    boolean kiemTraTonKho(String maSP, int soLuongMua);
    void capNhatTonKho(String maSP, int soLuongDaBan);
}
