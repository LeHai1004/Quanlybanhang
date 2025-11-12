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
import java.util.Scanner;
// Đã xóa "Set" và "Collectors"

public class Main {
    public static void main(String[] args) {
        // --- 1. KHỞI TẠO DỮ LIỆU BAN ĐẦU ---
        IQuanLySanPham qlSanPham = new QuanLySanPhamImpl();
        IQuanLyDonHang qlDonHang = new QuanLyDonHangImpl(); 
        
        SanPham sach = new Sach("S001", "Lập trình Java", 100000, "Tác giả A", 300, 50);
        SanPham dt = new DienThoai("DT001", "iPhone 20", 25000000, "Apple", 5000, 20);
        qlSanPham.them(sach);
        qlSanPham.them(dt);

        KhachHang khachHangA = new KhachHang("KH001", "Nguyễn Văn A", "Hà Nội");
        KhachHang khachHangB = new KhachHang("KH002", "Trần Thị B", "Đà Nẵng");
        
        Scanner scanner = new Scanner(System.in);

        // --- 2. BẮT ĐẦU VÒNG LẶP MENU ---
        while (true) {
            System.out.println("\n--- HỆ THỐNG QUẢN LÝ BÁN HÀNG ---");
            System.out.println("1. Xem danh sách sản phẩm trong kho");
            System.out.println("2. Thêm sản phẩm vào giỏ hàng (cho Khách A)");
            System.out.println("3. Đặt hàng (cho Khách A)");
            System.out.println("4. Xuất báo cáo lịch sử mua hàng"); // Tên đã sửa
            System.out.println("5. Thêm sản phẩm vào giỏ hàng (cho Khách B)");
            System.out.println("6. Đặt hàng (cho Khách B)");
            System.out.println("0. Thoát chương trình");
            System.out.print("Mời bạn chọn chức năng: ");

            int choice = -1;
            try {
                choice = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Lỗi: Vui lòng nhập số!");
                scanner.nextLine();
                continue;
            }
            scanner.nextLine();
            
            switch (choice) {
                case 1:
                    // --- Chức năng 1: Xem sản phẩm ---
                    System.out.println("--- DANH SÁCH SẢN PHẨM ---");
                    List<SanPham> dssp = qlSanPham.layTatCa();
                    if (dssp.isEmpty()) {
                        System.out.println("Kho hàng rỗng.");
                    } else {
                        for (SanPham sp : dssp) {
                            System.out.printf("Mã: %s | Tên: %s | Giá bán: %,.0f VND | Tồn kho: %d\n",
                                    sp.getMaSP(), sp.getTenSP(), sp.getGiaBan(), sp.getSoLuongTon());
                        }
                    }
                    break;
                
                case 2:
                    // --- Chức năng 2: Thêm vào giỏ (Khách A) ---
                    System.out.print("Nhập mã sản phẩm (S001 hoặc DT001): ");
                    String maSP_A = scanner.nextLine();
                    SanPham spThem_A = qlSanPham.timKiemTheoMa(maSP_A);
                    
                    if (spThem_A == null) {
                        System.out.println("Lỗi: Không tìm thấy sản phẩm!");
                        break;
                    }
                    
                    System.out.print("Nhập số lượng muốn mua: ");
                    int soLuong_A = scanner.nextInt();
                    scanner.nextLine();
                    
                    khachHangA.getGioHang().themSanPham(spThem_A, soLuong_A);
                    System.out.printf("Giỏ hàng của A hiện có tổng tiền: %,.0f VND\n", khachHangA.getGioHang().tinhTongTien());
                    break;

                case 3:
                    // --- Chức năng 3: Đặt hàng (Khách A) ---
                    System.out.println("--- TIẾN HÀNH ĐẶT HÀNG CHO KHÁCH A ---");
                    String maDonHangA = "DH_A_" + System.currentTimeMillis();
                    khachHangA.datHang(maDonHangA, qlSanPham, qlDonHang);
                    break;

                case 4:
                    // --- Chức năng 4: Xuất báo cáo (ĐÃ SỬA LẠI) ---
                    System.out.println("--- BÁO CÁO: LỊCH SỬ MUA HÀNG ---");
                    List<DonHang> tatCaDonHang = qlDonHang.layTatCaDonHang();
                    
                    if (tatCaDonHang.isEmpty()) {
                        System.out.println("Chưa có đơn hàng nào được ghi nhận.");
                    } else {
                        // Chỉ dùng vòng lặp for đơn giản, không dùng Set
                        // Tên khách hàng có thể bị lặp lại nếu họ mua nhiều lần
                        System.out.println("Tổng số đơn hàng: " + tatCaDonHang.size());
                        for (DonHang dh : tatCaDonHang) {
                            System.out.println("- Đơn hàng: " + dh.getMaDonHang() + 
                                               " | Khách hàng: " + dh.getKhachHang().getTenKH());
                        }
                    }
                    break;
                    
                case 5:
                    // --- Chức năng 5: Thêm vào giỏ (Khách B) ---
                    System.out.print("Nhập mã sản phẩm (S001 hoặc DT001): ");
                    String maSP_B = scanner.nextLine();
                    SanPham spThem_B = qlSanPham.timKiemTheoMa(maSP_B);
                    
                    if (spThem_B == null) {
                        System.out.println("Lỗi: Không tìm thấy sản phẩm!");
                        break;
                    }
                    
                    System.out.print("Nhập số lượng muốn mua: ");
                    int soLuong_B = scanner.nextInt();
                    scanner.nextLine();
                    
                    khachHangB.getGioHang().themSanPham(spThem_B, soLuong_B);
                    System.out.printf("Giỏ hàng của B hiện có tổng tiền: %,.0f VND\n", khachHangB.getGioHang().tinhTongTien());
                    break;

                case 6:
                    // --- Chức năng 6: Đặt hàng (Khách B) ---
                    System.out.println("--- TIẾN HÀNH ĐẶT HÀNG CHO KHÁCH B ---");
                    String maDonHangB = "DH_B_" + System.currentTimeMillis();
                    khachHangB.datHang(maDonHangB, qlSanPham, qlDonHang);
                    break;

                case 0:
                    // --- Chức năng 0: Thoát ---
                    System.out.println("Tạm biệt! Đang đóng chương trình...");
                    scanner.close();
                    return;
                
                default:
                    System.out.println("Lỗi: Lựa chọn không hợp lệ. Vui lòng chọn từ 0 đến 6.");
            }
        }
    }
}
