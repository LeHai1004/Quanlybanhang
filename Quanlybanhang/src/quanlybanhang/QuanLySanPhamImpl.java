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
// Đã xóa: import java.util.stream.Collectors;

public class QuanLySanPhamImpl implements IQuanLySanPham {

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
        // Dùng vòng lặp for-each kết hợp với biến tạm để xóa
        // (Vì không dùng được Stream, cũng không nên xóa khi đang lặp)
        SanPham spCanXoa = null;
        for (SanPham sp : danhSachSP) {
            if (sp.getMaSP().equals(maSP)) {
                spCanXoa = sp;
                break; // Tìm thấy thì dừng
            }
        }
        
        if (spCanXoa != null) {
            danhSachSP.remove(spCanXoa);
            System.out.println("Đã xóa SP có mã: " + maSP);
        }
    }

    // --- HÀM timKiemTheoTen ĐÃ ĐƯỢC VIẾT LẠI ---
    @Override
    public List<SanPham> timKiemTheoTen(String ten) {
        // 1. Tạo một danh sách rỗng để chứa kết quả
        List<SanPham> ketQuaTimKiem = new ArrayList<>();
        
        // 2. Chuyển từ khóa tìm kiếm về chữ thường
        String tenTimKiemLower = ten.toLowerCase();

        // 3. Dùng vòng lặp for-each cơ bản
        for (SanPham sp : danhSachSP) {
            // 4. Lấy tên sản phẩm và chuyển về chữ thường
            String tenSanPhamLower = sp.getTenSP().toLowerCase();
            
            // 5. Kiểm tra xem tên SP có chứa từ khóa tìm kiếm không
            if (tenSanPhamLower.contains(tenTimKiemLower)) {
                // 6. Nếu có, thêm vào danh sách kết quả
                ketQuaTimKiem.add(sp);
            }
        }

        // 7. Trả về danh sách kết quả
        return ketQuaTimKiem;
    }

    @Override
    public List<SanPham> layTatCa() {
        return this.danhSachSP;
    }

    // --- HÀM timKiemTheoMa ĐÃ ĐƯỢC VIẾT LẠI ---
    @Override
    public SanPham timKiemTheoMa(String maSP) {
        // Dùng vòng lặp for-each cơ bản
        for (SanPham sp : danhSachSP) {
            // Kiểm tra xem mã SP có khớp không
            if (sp.getMaSP().equals(maSP)) {
                // Nếu tìm thấy, trả về ngay lập tức
                return sp;
            }
        }
        
        // Nếu lặp hết mà không tìm thấy, trả về null
        return null;
    }

    // --- Các hàm nghiệp vụ (không đổi) ---
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