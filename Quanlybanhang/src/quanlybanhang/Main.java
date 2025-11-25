package quanlybanhang;

import java.util.List;
import java.util.Scanner;

public class Main {
    
    // --- MENU 1: Menu chinh (Chon vai tro) ---
    public static void main(String[] args) {
        
        HeThongBanHang heThong = new HeThongBanHang();
        QuanTriVien adminChinh = new QuanTriVien("Admin_SuperUser");
        Scanner scanner = new Scanner(System.in);

        // Kiem tra va them du lieu mau (neu kho rong)
        if (heThong.timKhachHang("KH001") == null) {
            heThong.themKhachHang(new KhachHang("KH001", "Nguyen Van A", "Ha Noi"));
        }
        if (heThong.timKhachHang("KH002") == null) {
            heThong.themKhachHang(new KhachHang("KH002", "Tran Thi B", "Da Nang"));
        }
        if (heThong.xemKhoHang().isEmpty()) {
             System.out.println("--- KHO RONG - ADMIN NHAP HANG LAN DAU ---");
            heThong.adminThemSanPham(adminChinh, new Sach("SP001", "Lap trinh Java", 100000, "Tac gia A", 300, 50));
            heThong.adminThemSanPham(adminChinh, new DienThoai("SP002", "iPhone 20", 25000000, "Apple", 5000, 20));
        }

        while (true) {
            System.out.println("\n--- HE THONG QUAN LY BAN HANG ---");
            System.out.println("Ban la:");
            System.out.println("1. Khach Hang");
            System.out.println("2. Quan Tri Vien (Admin)");
            System.out.println("0. Luu va Thoat");
            System.out.print("Moi ban chon: ");

            int choice = -1;
            try { 
                choice = scanner.nextInt(); 
            } catch (Exception e) {
                System.out.println("Loi: Lua chon khong hop le.");
            }
            scanner.nextLine(); // Xoa bo dem

            switch (choice) {
                case 1:
                    menuKhachHang(heThong, scanner);
                    break;
                case 2:
                    menuAdmin(heThong, adminChinh, scanner);
                    break;
                case 0:
                    heThong.luuDuLieuVaoFile();
                    System.out.println("Tam biet! Du lieu da duoc luu.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Lua chon khong hop le.");
            }
        }
    }
    
    // --- MENU 2: Menu danh cho Khach Hang ---
    public static void menuKhachHang(HeThongBanHang heThong, Scanner scanner) {
        System.out.println("\n--- DANG NHAP KHACH HANG ---");
        System.out.print("Vui long nhap ma KH (vi du: KH001 hoac KH002): ");
        String maKH = scanner.nextLine();
        
        KhachHang kh = heThong.timKhachHang(maKH);
        if (kh == null) {
            System.out.println("Loi: Khong tim thay khach hang!");
            return;
        }
        
        while (true) {
            System.out.println("\n--- XIN CHAO, " + kh.getTenKH() + " ---");
            System.out.println("1. Xem tat ca san pham");
            System.out.println("2. Tim san pham theo ten");
            System.out.println("3. Them san pham vao gio hang");
            System.out.println("4. Xem gio hang");
            System.out.println("5. Dat hang (Thanh toan)");
            System.out.println("6. Huy don hang");
            System.out.println("0. Dang xuat (Quay lai menu chinh)");
            System.out.print("Moi ban chon: ");

            int choice = -1;
            try { choice = scanner.nextInt(); } catch (Exception e) {}
            scanner.nextLine();
            
            switch (choice) {
                case 1:
                    System.out.println("\n--- DANH SACH SAN PHAM ---");
                    List<SanPham> dssp = heThong.xemKhoHang();
                    if (dssp.isEmpty()) { System.out.println("Kho hang rong."); }
                    else {
                        for (SanPham sp : dssp) {
                            sp.xuat();
                        }
                    }
                    break;
                case 2:
                    System.out.println("\n--- TIM SAN PHAM THEO TEN ---");
                    System.out.print("Nhap ten san pham can tim: ");
                    String tenTim = scanner.nextLine();
                    List<SanPham> ketQua = heThong.timSanPhamTheoTen(tenTim);
                    if (ketQua.isEmpty()) {
                        System.out.println("Khong tim thay san pham nao voi ten '" + tenTim + "'");
                    } else {
                        System.out.println("Da tim thay " + ketQua.size() + " san pham:");
                         for (SanPham sp : ketQua) {
                            sp.xuat();
                        }
                    }
                    break;
                case 3:
                    System.out.print("Nhap ma san pham muon mua (Ma in o dau dong): ");
                    String maSP = scanner.nextLine();
                    SanPham spThem = heThong.timSanPham(maSP);
                    
                    if (spThem == null) {
                        System.out.println("Loi: Khong tim thay san pham!");
                    } else {
                        System.out.print("Nhap so luong: ");
                        int soLuong = scanner.nextInt();
                        scanner.nextLine();
                        kh.getGioHang().themSanPham(spThem, soLuong);
                    }
                    break;
                case 4:
                    System.out.println("\n--- GIO HANG CUA BAN ---");
                    List<ChiTietDonHang> gioHang = kh.getGioHang().getDanhSachChiTiet();
                    if (gioHang.isEmpty()) { System.out.println("Gio hang rong."); }
                    else {
                        for (ChiTietDonHang ct : gioHang) {
                            System.out.printf("Mon: %s | So luong: %d\n",
                                ct.getSanPham().getTenSP(), ct.getSoLuong());
                        }
                    }
                    break;
                case 5:
                    System.out.println("\n--- TIEN HANH DAT HANG ---");
                    heThong.datHang(kh);
                    break;
                case 6: 
                    System.out.println("\n--- HUY DON HANG ---");
                    System.out.println("Cac don hang gan day cua ban:");
                    heThong.inLichSuMuaHangCuaKhach(kh.getMaKH());
                    
                    System.out.print("\nNhap chinh xac MA DON HANG ban muon huy: ");
                    String maDonHangHuy = scanner.nextLine();
                    
                    DonHang dh = heThong.timDonHang(maDonHangHuy);
                    if (dh == null || !dh.getKhachHang().getMaKH().equals(kh.getMaKH())) {
                        System.out.println("Loi: Ma don hang khong dung hoac khong phai cua ban.");
                    } else {
                        heThong.khachHangHuyDonHang(maDonHangHuy);
                    }
                    break;
                case 0:
                    return; // Thoat ve menu chinh
                default:
                    System.out.println("Lua chon khong hop le.");
            }
        }
    }

    // --- MENU 3: Menu danh cho Admin ---
    public static void menuAdmin(HeThongBanHang heThong, QuanTriVien admin, Scanner scanner) {
        while (true) {
            System.out.println("\n--- MENU ADMIN ---");
            System.out.println("--- Quan Ly San Pham ---");
            System.out.println("1. Them san pham moi vao kho (Auto ID)");
            System.out.println("2. Sua thong tin san pham");
            System.out.println("3. Xoa san pham khoi kho");
            System.out.println("--- Quan Ly Khach Hang ---");
            System.out.println("4. Dang ky khach hang moi (Auto ID)");
            System.out.println("--- Quan Ly Don Hang ---");
            System.out.println("5. Xoa don hang (Admin xoa vinh vien)");
            System.out.println("--- Bao Cao ---");
            System.out.println("6. Xem lich su tat ca don hang");
            System.out.println("7. Xem lich su mua hang cua 1 khach");
            System.out.println("8. In chi tiet mot don hang");
            System.out.println("9. Xem danh sach khach hang");
            System.out.println("0. Dang xuat (Quay lai menu chinh)");
            System.out.print("Moi ban chon: ");

            int choice = -1;
            try { choice = scanner.nextInt(); } catch (Exception e) {}
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("\n--- (Admin) THEM SAN PHAM MOI ---");
                    try {
                        // Tu dong tao ma SP
                        String maTuDong = heThong.taoMaSanPhamMoi();
                        System.out.println("He thong tu dong tao ma: " + maTuDong);
                        
                        System.out.print("Chon loai (1: Sach, 2: Dien thoai): ");
                        int loai = scanner.nextInt();
                        scanner.nextLine();
                        
                        SanPham spMoi;
                        if (loai == 1) {
                            spMoi = new Sach();
                        } else if (loai == 2) {
                            spMoi = new DienThoai();
                        } else {
                            System.out.println("Loai khong hop le.");
                            break;
                        }
                        
                        spMoi.setMaSP(maTuDong); // Gan ma tu dong
                        spMoi.nhap(scanner); 
                        heThong.adminThemSanPham(admin, spMoi);
                        
                    } catch (Exception e) {
                        System.out.println("Loi! Nhap lieu khong dung dinh dang.");
                        scanner.nextLine();
                    }
                    break;
                case 2:
                    System.out.println("\n--- (Admin) SUA SAN PHAM ---");
                    System.out.print("Nhap MA SAN PHAM can sua: ");
                    String maSPCanSua = scanner.nextLine();
                    SanPham spCu = heThong.timSanPham(maSPCanSua);
                    
                    if (spCu == null) {
                        System.out.println("Loi: Khong tim thay san pham nay!");
                    } else {
                        System.out.println("Da tim thay: ");
                        spCu.xuat();
                        System.out.println("Vui long nhap thong tin MOI:");
                        try {
                            SanPham spMoi;
                            // Kiem tra kieu de tao doi tuong moi tuong ung
                            if (spCu instanceof Sach) {
                                spMoi = new Sach();
                            } else if (spCu instanceof DienThoai) {
                                spMoi = new DienThoai();
                            } else {
                                System.out.println("Loi: Loai san pham khong xac dinh");
                                break;
                            }
                            
                            spMoi.nhap(scanner); 
                            heThong.adminSuaSanPham(admin, maSPCanSua, spMoi);
                            
                        } catch (Exception e) {
                            System.out.println("Loi! Nhap lieu khong dung dinh dang.");
                            scanner.nextLine();
                        }
                    }
                    break;
                case 3:
                    System.out.println("\n--- (Admin) XOA SAN PHAM ---");
                    System.out.print("Nhap MA SAN PHAM can xoa: ");
                    String maSPCanXoa = scanner.nextLine();
                    heThong.adminXoaSanPham(admin, maSPCanXoa);
                    break;
                case 4:
                    System.out.println("\n--- (Admin) DANG KY KHACH HANG MOI ---");
                    try {
                        // Tu dong tao ma KH
                        String maKHTuDong = heThong.taoMaKhachHangMoi();
                        System.out.println("He thong tu dong tao ma: " + maKHTuDong);
                        
                        KhachHang khMoi = new KhachHang();
                        khMoi.setMaKH(maKHTuDong); // Gan ma tu dong
                        khMoi.nhap(scanner); // Nhap ten va dia chi
                        
                        heThong.themKhachHang(khMoi);
                        System.out.println("Them khach hang moi thanh cong!");
                    } catch (Exception e) {
                        System.out.println("Loi! Nhap lieu khong dung dinh dang.");
                        scanner.nextLine();
                    }
                    break;
                case 5:
                    System.out.println("\n--- (Admin) XOA DON HANG (VINH VIEN) ---");
                    System.out.print("Nhap ma don hang can xoa: ");
                    String maDHXoa = scanner.nextLine();
                    heThong.adminXoaDonHang(admin, maDHXoa);
                    break;
                case 6:
                    System.out.println("\n--- BAO CAO: LICH SU MUA HANG ---");
                    List<DonHang> tatCaDonHang = heThong.xemLichSuMuaHang();
                    if (tatCaDonHang.isEmpty()) {
                        System.out.println("Chua co don hang nao.");
                    } else {
                        for (DonHang dh : tatCaDonHang) {
                            System.out.println("- DH: " + dh.getMaDonHang() + 
                                               " | Khach: " + dh.getKhachHang().getTenKH());
                        }
                    }
                    break;
                case 7:
                    System.out.println("\n--- BAO CAO: XEM LICH SU CUA KHACH HANG ---");
                    System.out.print("Nhap ma khach hang (vi du: KH001): ");
                    String maKH = scanner.nextLine();
                    heThong.inLichSuMuaHangCuaKhach(maKH);
                    break;
                case 8:
                    System.out.println("\n--- BAO CAO: IN CHI TIET DON HANG ---");
                    System.out.print("Nhap ma don hang ban muon xem (vi du: DH001): ");
                    String maDHXem = scanner.nextLine();
                    heThong.inChiTietDonHang(maDHXem);
                    break;
                case 9:
                    System.out.println("\n--- BAO CAO: DANH SACH KHACH HANG ---");
                    List<KhachHang> dsKH = heThong.xemDanhSachKhachHang();
                    if (dsKH.isEmpty()) {
                        System.out.println("Chua co khach hang nao duoc dang ky.");
                    } else {
                        for (KhachHang kh : dsKH) {
                            kh.xuat(); 
                        }
                    }
                    break;
                case 0:
                    return; // Thoat ve menu chinh
                default:
                    System.out.println("Lua chon khong hop le.");
            }
        }
    }
}