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
import java.util.stream.Collectors;

public class QuanLySanPhamImpl implements IQuanLySanPham {

    // Aggregation (Kết tập ⚪️)
    private List<SanPham> danhSachSP;

    public QuanLySanPhamImpl() {
        this.danhSachSP = new ArrayList<>();
    }

    @Override
    public void them(SanPham sp) {
        danhSachSP.add(sp);
        System.out.println("Đã thêm: " + sp.getTenSP() + " (Tồn kho: " + sp.getSoLuongTon() + ")");
    }

    @Override
    public void sua(String maSP, SanPham spMoi) {
        SanPham spCanTim = timKiemTheoMa(maSP);
        if (spCanTim != null) {
            int index = danhSachSP.indexOf(spCanTim);
            danhSachSP.set(index, spMoi);
            System.out.println("Đã cập nhật: " + spMoi.getTenSP());
        }
    }

    @Override
    public void xoa(String maSP) {
        boolean removed = danhSachSP.removeIf(sp -> sp.getMaSP().equals(maSP));
        if (removed) {
            System.out.println("Đã xóa SP có mã: " + maSP);
        }
    }

    @Override
    public List<SanPham> timKiemTheoTen(String ten) {
        return danhSachSP.stream()
                .filter(sp -> sp.getTenSP().toLowerCase().contains(ten.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<SanPham> layTatCa() {
        return this.danhSachSP;
    }

    @Override
    public SanPham timKiemTheoMa(String maSP) {
        return danhSachSP.stream()
                .filter(sp -> sp.getMaSP().equals(maSP))
                .findFirst()
                .orElse(null);
    }

    // Triển khai nghiệp vụ
    @Override
    public boolean kiemTraTonKho(String maSP, int soLuongMua) {
        SanPham sp = timKiemTheoMa(maSP);
        if (sp != null) {
            return sp.getSoLuongTon() >= soLuongMua;
        }
        return false;
    }

    @Override
    public void capNhatTonKho(String maSP, int soLuongDaBan) {
        SanPham sp = timKiemTheoMa(maSP);
        if (sp != null) {
            int soLuongMoi = sp.getSoLuongTon() - soLuongDaBan;
            sp.setSoLuongTon(soLuongMoi);
            System.out.println("Đã cập nhật tồn kho " + sp.getTenSP() + ": còn " + soLuongMoi);
        }
    }
}
