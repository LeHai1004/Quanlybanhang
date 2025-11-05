/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package quanlybanhang;

/**
 *
 * @author HOANG HAI
 */
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        
        // --- 1. KHỞI TẠO CÁC HỆ THỐNG QUẢN LÝ ---
        IQuanLySanPham qlSanPham = new QuanLySanPhamImpl();
        IQuanLyDonHang qlDonHang = new QuanLyDonHangImpl(); 
        
        System.out.println("--- 2. NHẬP HÀNG VÀO KHO ---");
        // Thêm Sách (50 cuốn) và Điện thoại (20 cái)
        SanPham sach = new Sach("S001", "Lập trình Java", 100000, "Tác giả A", 300, 50);
        SanPham dt = new DienThoai("DT001", "iPhone 20", 25000000, "Apple", 5000, 20);
        qlSanPham.them(sach);
        qlSanPham.them(dt);

        // --- 3. TẠO KHÁCH HÀNG ---
        KhachHang khachHangA = new KhachHang("KH001", "Nguyễn Văn A", "Hà Nội");
        KhachHang khachHangB = new KhachHang("KH002", "Trần Thị B", "Đà Nẵng");
        
        // --- 4. KHÁCH A MUA HÀNG ---
        System.out.println("\n--- 4. KHÁCH A MUA HÀNG ---");
        khachHangA.getGioHang().themSanPham(sach, 2);
        khachHangA.getGioHang().themSanPham(dt, 1);
        
        // Truyền cả 2 trình quản lý vào
        khachHangA.datHang("DH001", qlSanPham, qlDonHang);

        // --- 5. KHÁCH B MUA HÀNG ---
        System.out.println("\n--- 5. KHÁCH B MUA HÀNG ---");
        khachHangB.getGioHang().themSanPham(sach, 1);
        khachHangB.datHang("DH002", qlSanPham, qlDonHang);
        
        // --- 6. KHÁCH A MUA HÀNG LẦN 2 ---
        System.out.println("\n--- 6. KHÁCH A MUA HÀNG (LẦN 2) ---");
        khachHangA.getGioHang().themSanPham(sach, 5);
        khachHangA.datHang("DH003", qlSanPham, qlDonHang);

        
        // --- 7. BÁO CÁO: XUẤT RA KHÁCH HÀNG ĐÃ MUA HÀNG ---
        System.out.println("\n--- 7. BÁO CÁO: DANH SÁCH KHÁCH HÀNG ĐÃ MUA ---");
        
        List<DonHang> tatCaDonHang = qlDonHang.layTatCaDonHang();
        
        // Dùng Stream API và Set để lọc ra danh sách khách hàng duy nhất
        Set<KhachHang> khachDaMua = tatCaDonHang.stream()
                .map(DonHang::getKhachHang) // Lấy KhachHang từ DonHang
                .collect(Collectors.toSet()); // Thu thập vào Set (lọc trùng)

        System.out.println("Tổng số khách hàng đã mua: " + khachDaMua.size());
        for (KhachHang kh : khachDaMua) {
            System.out.println("- Tên: " + kh.getTenKH());
        }
    }
}
