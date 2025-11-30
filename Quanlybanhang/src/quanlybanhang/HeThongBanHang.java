package quanlybanhang;

import java.util.ArrayList;
import java.util.List;

public class HeThongBanHang {
    private QuanLySanPhamImpl qlSanPham;
    private QuanLyDonHangImpl qlDonHang;
    private DocGhiFileImpl docGhi;
    private List<KhachHang> danhSachKhachHang;

    public HeThongBanHang() {
        this.docGhi = new DocGhiFileImpl();
        this.qlSanPham = new QuanLySanPhamImpl();
        this.qlDonHang = new QuanLyDonHangImpl();
        this.danhSachKhachHang = new ArrayList<>();
        taiDuLieuTuFile();
    }
    
    public void taiDuLieuTuFile() {
        System.out.println("Dang tai du lieu tu file...");
        List<SanPham> dsSP = docGhi.docSanPham();
        List<KhachHang> dsKH = docGhi.docKhachHang();
        List<DonHang> dsDH = docGhi.docDonHang(dsSP, dsKH);
        qlSanPham.setData(dsSP);
        qlDonHang.setData(dsDH);
        danhSachKhachHang = dsKH;
        System.out.println("Tai xong: " + dsSP.size() + " SP, " + dsDH.size() + " DH, " + dsKH.size() + " KH.");
    }
    public void luuDuLieuVaoFile() {
        System.out.println("Dang luu du lieu...");
        docGhi.luuSanPham(qlSanPham.layTatCa());
        docGhi.luuKhachHang(danhSachKhachHang);
        docGhi.luuDonHang(qlDonHang.layTatCaDonHang());
    }

    public void themKhachHang(KhachHang kh) { danhSachKhachHang.add(kh); }
    public KhachHang timKhachHang(String ma) { 
        for(KhachHang k : danhSachKhachHang) {
            if(k.getMaKH().equals(ma)) return k; 
        }
        return null; 
    }
    
    public List<SanPham> xemKhoHang() { return qlSanPham.layTatCa(); }
    public SanPham timSanPham(String ma) { return qlSanPham.timKiemTheoMa(ma); }
    public List<SanPham> timSanPhamTheoTen(String ten) { return qlSanPham.timKiemTheoTen(ten); }
    public List<DonHang> xemLichSuMuaHang() { return qlDonHang.layTatCaDonHang(); }
    public List<KhachHang> xemDanhSachKhachHang() { return danhSachKhachHang; }
    
    public DonHang timDonHang(String ma) {
        for(DonHang dh : qlDonHang.layTatCaDonHang()) {
            if(dh.getMaDonHang().equals(ma)) return dh;
        }
        return null;
    }

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
        
        String maMoi = "DH" + String.format("%03d", qlDonHang.layTatCaDonHang().size() + 1);
        DonHang donHangMoi = new DonHang(maMoi, khachHang, gio.getDanhSachChiTiet());
        
        for (ChiTietDonHang ct : donHangMoi.getDanhSachChiTiet()) {
            qlSanPham.capNhatTonKho(ct.getSanPham().getMaSP(), ct.getSoLuong());
        }
        qlDonHang.themDonHang(donHangMoi);
        gio.lamTrongGio();
        System.out.println(khachHang.getTenKH() + " da dat hang thanh cong! Ma: " + maMoi);
    }

    public void khachHangHuyDonHang(String maDonHang) {
        DonHang dh = qlDonHang.huyDonHang(maDonHang); 
        if (dh == null) {
            System.out.println("Loi: Don hang khong tim thay hoac da huy.");
            return;
        }
        System.out.println("Dang hoan kho...");
        for (ChiTietDonHang ct : dh.getDanhSachChiTiet()) {
            qlSanPham.capNhatTonKho(ct.getSanPham().getMaSP(), -ct.getSoLuong());
        }
        System.out.println("Da huy don " + maDonHang + " va hoan kho.");
    }

    public List<DonHang> layDonHangCuaKhach(String maKH) {
        List<DonHang> kq = new ArrayList<>();
        for (DonHang dh : qlDonHang.layTatCaDonHang()) {
            if (dh.getKhachHang().getMaKH().equals(maKH)) kq.add(dh);
        }
        return kq;
    }
    
    public void inLichSuMuaHangCuaKhach(String maKH) {
        KhachHang kh = timKhachHang(maKH);
        if (kh == null) {
            System.out.println("Loi: Khong tim thay khach hang " + maKH);
            return;
        }
        System.out.println("--- LICH SU MUA HANG CUA: " + kh.getTenKH() + " ---");
        List<DonHang> donCuaKhach = layDonHangCuaKhach(maKH);
        if(donCuaKhach.isEmpty()) {
             System.out.println("Khach hang nay chua mua don nao.");
        } else {
            for (DonHang dh : donCuaKhach) {
                System.out.println("  + Ma DH: " + dh.getMaDonHang() + " | Trang thai: " + dh.getTrangThai());
                for (ChiTietDonHang ct : dh.getDanhSachChiTiet()) {
                    System.out.println("    - Mon: " + ct.getSanPham().getTenSP() + 
                                       " | SL: " + ct.getSoLuong());
                }
            }
        }
    }
    
    public void inChiTietDonHang(String maDonHang) {
        DonHang dh = timDonHang(maDonHang);
        if (dh == null) {
            System.out.println("Loi: Khong tim thay don hang co ma " + maDonHang);
            return;
        }
        KhachHang kh = dh.getKhachHang();
        List<ChiTietDonHang> danhSachChiTiet = dh.getDanhSachChiTiet();
        System.out.println("\n--- CHI TIET DON HANG: " + dh.getMaDonHang() + " | " + dh.getTrangThai() + " ---");
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

    public void adminXemDonHangDaHuy() {
        System.out.println("--- DON HANG DA HUY ---");
        boolean co = false;
        for(DonHang dh : qlDonHang.layTatCaDonHang()) {
            if ("Da Huy".equals(dh.getTrangThai())) {
                System.out.println("- " + dh.getMaDonHang() + " | Khach: " + dh.getKhachHang().getTenKH());
                co = true;
            }
        }
        if(!co) System.out.println("Khong co don nao.");
    }

    // Admin methods
    public void adminThemSanPham(QuanTriVien ad, SanPham sp) { if(ad!=null) qlSanPham.them(sp); }
    public void adminSuaSanPham(QuanTriVien ad, String ma, SanPham sp) { if(ad!=null) { sp.setMaSP(ma); qlSanPham.sua(ma, sp); } }
    public void adminXoaSanPham(QuanTriVien ad, String ma) { if(ad!=null) qlSanPham.xoa(ma); }
    public void adminXoaDonHang(QuanTriVien ad, String ma) { if(ad!=null) qlDonHang.xoaDonHang(ma, ad); }
    
    public String taoMaSP() { 
        int c=1; 
        while(true) { 
            String m="SP"+String.format("%03d",c); 
            if(timSanPham(m)==null) return m; 
            c++; 
        } 
    }
    public String taoMaKH() { 
        int c=1; 
        while(true) { 
            String m="KH"+String.format("%03d",c); 
            if(timKhachHang(m)==null) return m; 
            c++; 
        } 
    }
}