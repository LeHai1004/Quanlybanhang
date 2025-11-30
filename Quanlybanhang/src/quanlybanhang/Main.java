package quanlybanhang;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        HeThongBanHang heThong = new HeThongBanHang();
        String tenAdminChuan = "HoangHai"; 
        QuanTriVien adminChinh = new QuanTriVien(tenAdminChuan);
        Scanner scanner = new Scanner(System.in);

        // Du lieu mau (Neu he thong rong)
        if (heThong.timKhachHang("KH001") == null) heThong.themKhachHang(new KhachHang("KH001", "Nguyen Van A", "Ha Noi"));
        if (heThong.timKhachHang("KH002") == null) heThong.themKhachHang(new KhachHang("KH002", "Tran Thi B", "Da Nang"));
        if (heThong.xemKhoHang().isEmpty()) {
            heThong.adminThemSanPham(adminChinh, new Sach("SP001", "Java Book", 100, "TG A", 200, 50));
            heThong.adminThemSanPham(adminChinh, new DienThoai("SP002", "iPhone", 2000, "Apple", 5000, 20));
        }

        while (true) {
            System.out.println("\n========================================");
            System.out.println("      HE THONG QUAN LY BAN HANG");
            System.out.println("========================================");
            System.out.println("1. Khach Hang (Mua hang)");
            System.out.println("2. Quan Tri Vien (Quan ly)");
            System.out.println("0. Luu va Thoat");
            System.out.print(">> Moi ban chon: ");
            
            int c = -1; try { c = scanner.nextInt(); } catch(Exception e) {} scanner.nextLine();

            if (c == 1) {
                // --- HUONG DAN TRUOC KHI DANG NHAP ---
                System.out.println("\n--- DANH SACH KHACH HANG HIEN CO ---");
                for(KhachHang k : heThong.xemDanhSachKhachHang()) {
                    System.out.printf("- Ma: %-6s | Ten: %s\n", k.getMaKH(), k.getTenKH());
                }
                System.out.println("------------------------------------");
                
                menuKhachHang(heThong, scanner);
                
            } else if (c == 2) {
                System.out.print("Nhap ten Admin (" + tenAdminChuan + "): ");
                if (scanner.nextLine().equals(tenAdminChuan)) menuAdmin(heThong, adminChinh, scanner);
                else System.out.println("Sai ten Admin!");
                
            } else if (c == 0) { 
                heThong.luuDuLieuVaoFile(); 
                return; 
            }
        }
    }

    public static void menuKhachHang(HeThongBanHang ht, Scanner sc) {
        System.out.print(">> Nhap Ma KH cua ban de dang nhap: "); 
        String ma = sc.nextLine();
        KhachHang kh = ht.timKhachHang(ma);
        if (kh == null) { System.out.println("Loi: Khong tim thay ma khach hang nay!"); return; }

        while (true) {
            System.out.println("\n----------------------------------------");
            System.out.println(" Xin chao: " + kh.getTenKH());
            System.out.println("----------------------------------------");
            System.out.println("1. Xem tat ca san pham");
            System.out.println("2. Tim san pham theo ten");
            System.out.println("3. Them san pham vao gio hang");
            System.out.println("4. Xem gio hang cua ban");
            System.out.println("5. Dat hang (Thanh toan)");
            System.out.println("6. Huy don hang");
            System.out.println("7. Lich su mua hang");
            System.out.println("0. Dang xuat");
            System.out.print(">> Chon chuc nang: ");
            int c = -1; try { c = sc.nextInt(); } catch(Exception e) {} sc.nextLine();

            if (c == 1) for (SanPham s : ht.xemKhoHang()) s.xuat();
            else if (c == 2) {
                System.out.print("Nhap ten SP can tim: ");
                for (SanPham s : ht.timSanPhamTheoTen(sc.nextLine())) s.xuat();
            } else if (c == 3) {
                // --- HUONG DAN CHON SAN PHAM ---
                System.out.println("\n--- DANH SACH SAN PHAM TRONG KHO ---");
                for (SanPham s : ht.xemKhoHang()) {
                    System.out.printf("[%s] %-20s | Gia: %,.0f\n", s.getMaSP(), s.getTenSP(), s.getGiaBan());
                }
                System.out.println("------------------------------------");
                System.out.print(">> Nhap MA SAN PHAM muon mua: "); 
                SanPham s = ht.timSanPham(sc.nextLine());
                if (s != null) { 
                    System.out.print("Nhap so luong: "); 
                    kh.getGioHang().themSanPham(s, sc.nextInt()); 
                    sc.nextLine(); 
                } else {
                    System.out.println("Loi: Khong tim thay ma san pham nay.");
                }
            } else if (c == 4) {
                System.out.println("--- GIO HANG ---");
                if(kh.getGioHang().isEmpty()) System.out.println("(Trong)");
                for (ChiTietDonHang ct : kh.getGioHang().getDanhSachChiTiet()) 
                    System.out.println(ct.getSanPham().getTenSP() + " - SL: " + ct.getSoLuong());
            } else if (c == 5) ht.datHang(kh);
            else if (c == 6) {
                // --- HUONG DAN CHON DON HUY ---
                System.out.println("\n--- DON HANG GẦN DAY CUA BAN ---");
                List<DonHang> dsDon = ht.layDonHangCuaKhach(kh.getMaKH());
                if(dsDon.isEmpty()) System.out.println("(Ban chua co don hang nao)");
                else {
                    for (DonHang dh : dsDon) {
                        System.out.println("- Ma: " + dh.getMaDonHang() + " | Tien: " + dh.tinhTongGiaTri() + " | Trang thai: " + dh.getTrangThai());
                    }
                    System.out.print(">> Nhap MA DON HANG muon huy: ");
                    String maHuy = sc.nextLine();
                    DonHang dh = ht.timDonHang(maHuy);
                    if (dh != null && dh.getKhachHang().getMaKH().equals(kh.getMaKH())) ht.khachHangHuyDonHang(dh.getMaDonHang());
                    else System.out.println("Loi: Ma don khong hop le.");
                }
            } else if (c == 7) {
                System.out.println("--- LICH SU ---");
                for (DonHang dh : ht.layDonHangCuaKhach(kh.getMaKH())) 
                    System.out.println(dh.getMaDonHang() + " - " + dh.tinhTongGiaTri() + " - " + dh.getTrangThai());
            } else if (c == 0) return;
        }
    }

    public static void menuAdmin(HeThongBanHang ht, QuanTriVien ad, Scanner sc) {
        while (true) {
            System.out.println("\n========================================");
            System.out.println("       MENU ADMIN (" + ad.getTenAdmin() + ")");
            System.out.println("========================================");
            System.out.println("1. Them SP | 2. Sua SP | 3. Xoa SP");
            System.out.println("4. Dang ky KH | 5. DS KH | 6. Xoa Don (Vinh vien) | 7. Xem Don Huy");
            System.out.println("0. Quay lai");
            System.out.print(">> Chon: ");
            int c = -1; try { c = sc.nextInt(); } catch(Exception e) {} sc.nextLine();

            if (c == 1) {
                System.out.print("Loai (1.Sach 2.DT): "); int l = sc.nextInt(); sc.nextLine();
                SanPham sp = (l == 1) ? new Sach() : new DienThoai();
                sp.setMaSP(ht.taoMaSP()); sp.nhap(sc); ht.adminThemSanPham(ad, sp);
            } else if (c == 2) {
                // --- HUONG DAN SUA ---
                System.out.println("--- DANH SACH SP HIEN CO ---");
                for(SanPham s : ht.xemKhoHang()) System.out.print(s.getMaSP() + " ");
                System.out.print("\n>> Nhap MA SP can sua: "); 
                String m = sc.nextLine();
                SanPham s = ht.timSanPham(m);
                if (s != null) { 
                    System.out.println("-> Dang sua: " + s.getTenSP());
                    SanPham sm = (s instanceof Sach) ? new Sach() : new DienThoai();
                    sm.nhap(sc); ht.adminSuaSanPham(ad, m, sm); 
                } else System.out.println("Khong tim thay ma nay.");
            } else if (c == 3) { 
                System.out.println("--- DANH SACH SP HIEN CO ---");
                for(SanPham s : ht.xemKhoHang()) System.out.print(s.getMaSP() + " ");
                System.out.print("\n>> Nhap MA SP can xoa: "); 
                ht.adminXoaSanPham(ad, sc.nextLine()); 
            }
            else if (c == 4) {
                KhachHang k = new KhachHang(); k.setMaKH(ht.taoMaKH()); k.nhap(sc); ht.themKhachHang(k);
                System.out.println("-> Da tao moi: " + k.getMaKH());
            } else if (c == 5) {
                for (KhachHang k : ht.xemDanhSachKhachHang()) k.xuat();
            } else if (c == 6) { 
                // --- HUONG DAN XOA DON ---
                System.out.println("--- DANH SACH DON HANG ---");
                for(DonHang d : ht.xemLichSuMuaHang()) System.out.println(d.getMaDonHang() + " (" + d.getTrangThai() + ")");
                System.out.print(">> Nhap MA DON can xoa vinh vien: "); 
                ht.adminXoaDonHang(ad, sc.nextLine()); 
            }
            else if (c == 7) ht.adminXemDonHangDaHuy();
            else if (c == 0) return;
        }
    }
}