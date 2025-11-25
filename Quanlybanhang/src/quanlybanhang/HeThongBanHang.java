package quanlybanhang;

import java.util.ArrayList;
import java.util.List;

public class HeThongBanHang {
    private IQuanLySanPham qlSanPham;
    private IQuanLyDonHang qlDonHang;
    private List<KhachHang> danhSachKhachHang;
    private IDocGhiFile docGhi;

    public HeThongBanHang() {
        this.docGhi = new DocGhiFileImpl();
        this.qlSanPham = new QuanLySanPhamImpl();
        this.qlDonHang = new QuanLyDonHangImpl();
        this.danhSachKhachHang = new ArrayList<>();
        taiDuLieuTuFile();
    }
    
    // ... (GIU NGUYEN CAC HAM: taiDuLieu, luuDuLieu, themKhachHang, timKhachHang) ...
    public void taiDuLieuTuFile() {
        System.out.println("Dang tai du lieu tu file...");
        List<SanPham> dsSP = docGhi.docSanPham();
        List<KhachHang> dsKH = docGhi.docKhachHang();
        List<DonHang> dsDH = docGhi.docDonHang(dsSP, dsKH);
        
        this.qlSanPham.setData(dsSP);
        this.qlDonHang.setData(dsDH);
        this.danhSachKhachHang = dsKH;
        
        System.out.println("Tai xong! " + dsSP.size() + " SP, " + dsDH.size() + " DH, " + dsKH.size() + " KH.");
    }
    
    public void luuDuLieuVaoFile() {
        System.out.println("Dang luu du lieu ra file...");
        docGhi.luuSanPham(qlSanPham.layTatCa());
        docGhi.luuKhachHang(danhSachKhachHang);
        docGhi.luuDonHang(qlDonHang.layTatCaDonHang());
    }

    public void themKhachHang(KhachHang kh) {
        this.danhSachKhachHang.add(kh);
    }
    
    public KhachHang timKhachHang(String maKH) {
        for (KhachHang kh : danhSachKhachHang) {
            if (kh.getMaKH().equals(maKH)) {
                return kh;
            }
        }
        return null;
    }

    // ... (GIU NGUYEN HAM datHang) ...
    public void datHang(KhachHang khachHang) {
        GioHang gio = khachHang.getGioHang();
        if (gio.isEmpty()) {
            System.out.println("Gio hang rong.");
            return;
        }
        for (ChiTietDonHang ct : gio.getDanhSachChiTiet()) {
            if (!qlSanPham.kiemTraTonKho(ct.getSanPham().getMaSP(), ct.getSoLuong())) {
                System.out.println("LOI: '" + ct.getSanPham().getTenSP() + "' khong du ton kho!");
                return;
            }
        }

        // Tu dong tao ma don hang (Logic +1)
        int soLuongHienTai = qlDonHang.layTatCaDonHang().size();
        String maDonHangMoi = "DH" + String.format("%03d", soLuongHienTai + 1);

        DonHang donHangMoi = new DonHang(maDonHangMoi, khachHang, gio.getDanhSachChiTiet());
        
        for (ChiTietDonHang ct : donHangMoi.getDanhSachChiTiet()) {
            qlSanPham.capNhatTonKho(ct.getSanPham().getMaSP(), ct.getSoLuong());
        }
        qlDonHang.themDonHang(donHangMoi);
        gio.lamTrongGio();
        System.out.println(khachHang.getTenKH() + " da dat hang thanh cong! Ma don hang: " + maDonHangMoi);
    }
    
    // --- THEM MOI: LOGIC HUY DON HANG ---
    public void khachHangHuyDonHang(String maDonHang) {
        DonHang donHangBiHuy = qlDonHang.huyDonHang(maDonHang);
        if (donHangBiHuy == null) {
            System.out.println("Loi: Khong tim thay don hang " + maDonHang + " de huy.");
            return;
        }
        System.out.println("Dang hoan kho cho don hang da huy...");
        for (ChiTietDonHang ct : donHangBiHuy.getDanhSachChiTiet()) {
            String maSP = ct.getSanPham().getMaSP();
            int soLuongHoanTra = ct.getSoLuong();
            qlSanPham.capNhatTonKho(maSP, -soLuongHoanTra); 
        }
        System.out.println("Da huy don hang " + maDonHang + " va hoan tra san pham ve kho.");
    }

    // ... (GIU NGUYEN CAC HAM BAO CAO) ...
    public List<SanPham> xemKhoHang() { return this.qlSanPham.layTatCa(); }
    public SanPham timSanPham(String maSP) { return this.qlSanPham.timKiemTheoMa(maSP); }
    public List<DonHang> xemLichSuMuaHang() { return this.qlDonHang.layTatCaDonHang(); }
    public DonHang timDonHang(String maDonHang) {
        for (DonHang dh : qlDonHang.layTatCaDonHang()) {
            if (dh.getMaDonHang().equals(maDonHang)) {
                return dh;
            }
        }
        return null;
    }
    public List<KhachHang> xemDanhSachKhachHang() { return this.danhSachKhachHang; }
    public List<SanPham> timSanPhamTheoTen(String ten) { return this.qlSanPham.timKiemTheoTen(ten); }
    
    public void inLichSuMuaHangCuaKhach(String maKH) {
        KhachHang kh = timKhachHang(maKH);
        if (kh == null) {
            System.out.println("Loi: Khong tim thay khach hang " + maKH);
            return;
        }
        System.out.println("--- LICH SU MUA HANG CUA: " + kh.getTenKH() + " ---");
        List<DonHang> tatCaDonHang = this.qlDonHang.layTatCaDonHang();
        boolean daMuaGiChua = false;
        for (DonHang dh : tatCaDonHang) {
            if (dh.getKhachHang().getMaKH().equals(maKH)) {
                daMuaGiChua = true;
                System.out.println("  + Ma DH: " + dh.getMaDonHang());
                for (ChiTietDonHang ct : dh.getDanhSachChiTiet()) {
                    System.out.println("    - Mon: " + ct.getSanPham().getTenSP() + " | SL: " + ct.getSoLuong());
                }
            }
        }
        if (!daMuaGiChua) { System.out.println("Khach hang nay chua mua don nao."); }
    }
    
    public void inChiTietDonHang(String maDonHang) {
        DonHang dh = timDonHang(maDonHang);
        if (dh == null) {
            System.out.println("Loi: Khong tim thay don hang co ma " + maDonHang);
            return;
        }
        KhachHang kh = dh.getKhachHang();
        List<ChiTietDonHang> danhSachChiTiet = dh.getDanhSachChiTiet();
        System.out.println("\n--- CHI TIET DON HANG: " + dh.getMaDonHang() + " ---");
        System.out.println("Nguoi mua: " + kh.getTenKH() + " (Ma KH: " + kh.getMaKH() + ")");
        System.out.println("--- DANH SACH SAN PHAM ---");
        if (danhSachChiTiet.isEmpty()) {
            System.out.println("Don hang nay khong co san pham nao.");
        } else {
            for (ChiTietDonHang ct : danhSachChiTiet) {
                SanPham sp = ct.getSanPham();
                System.out.printf("- Ten SP: %s (Ma: %s) | So luong: %d\n",
                    sp.getTenSP(), sp.getMaSP(), ct.getSoLuong());
            }
        }
    }

    // ... (GIU NGUYEN CAC HAM ADMIN CU) ...
    public void adminThemSanPham(QuanTriVien admin, SanPham sp) {
        if (admin == null) return;
        System.out.println("Admin '" + admin.getTenAdmin() + "' dang them san pham: " + sp.getMaSP());
        this.qlSanPham.them(sp);
    }
    
    public void adminSuaSanPham(QuanTriVien admin, String maSP, SanPham spMoi) {
        if (admin == null) return;
        spMoi.setMaSP(maSP); 
        this.qlSanPham.sua(maSP, spMoi);
    }

    public void adminXoaSanPham(QuanTriVien admin, String maSP) {
        if (admin == null) return;
        this.qlSanPham.xoa(maSP);
    }

    public void adminXoaDonHang(QuanTriVien admin, String maDonHang) {
        if (admin == null) return;
        this.qlDonHang.xoaDonHang(maDonHang, admin);
    }
    
    // --- THEM MOI: HAM TU DONG TAO MA (AUTO-INCREMENT) ---
    
    public String taoMaSanPhamMoi() {
        int count = 1;
        String maMoi;
        while (true) {
            // Tao ma theo dang SP001, SP002...
            maMoi = "SP" + String.format("%03d", count);
            // Kiem tra xem ma nay da ton tai chua
            if (timSanPham(maMoi) == null) {
                // Neu chua co thi dung ma nay
                break;
            }
            // Neu co roi thi tang so len
            count++;
        }
        return maMoi;
    }
    
    public String taoMaKhachHangMoi() {
        int count = 1;
        String maMoi;
        while (true) {
            // Tao ma theo dang KH001, KH002...
            maMoi = "KH" + String.format("%03d", count);
            // Kiem tra ton tai
            if (timKhachHang(maMoi) == null) {
                break;
            }
            count++;
        }
        return maMoi;
    }
}