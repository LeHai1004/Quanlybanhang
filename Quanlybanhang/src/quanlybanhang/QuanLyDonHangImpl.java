package quanlybanhang;

import java.util.ArrayList;
import java.util.List;

public class QuanLyDonHangImpl implements IQuanLyDonHang {

    private List<DonHang> danhSachDonHang;

    public QuanLyDonHangImpl() {
        this.danhSachDonHang = new ArrayList<>();
    }

    @Override
    public void themDonHang(DonHang dh) {
        this.danhSachDonHang.add(dh);
        System.out.println("Da luu don hang: " + dh.getMaDonHang());
    }

    @Override
    public List<DonHang> layTatCaDonHang() {
        return this.danhSachDonHang;
    }

    @Override
    public void xoaDonHang(String maDonHang, QuanTriVien admin) {
        if (admin == null) {
            System.out.println("LOI: Ban khong co quyen!");
            return;
        }
        DonHang dhXoa = null;
        for (DonHang dh : danhSachDonHang) {
            if (dh.getMaDonHang().equals(maDonHang)) {
                dhXoa = dh;
                break;
            }
        }
        if (dhXoa != null) {
            danhSachDonHang.remove(dhXoa);
            System.out.println("Admin '" + admin.getTenAdmin() + "' da xoa: " + maDonHang);
        } else {
            System.out.println("Loi: Khong tim thay don hang " + maDonHang);
        }
    }
    
    @Override
    public void setData(List<DonHang> dsDonHang) {
        this.danhSachDonHang = (dsDonHang != null) ? dsDonHang : new ArrayList<>();
    }
    
    // --- THEM MOI ---
    @Override
    public DonHang huyDonHang(String maDonHang) {
        DonHang dhHuy = null;
        // Tim don hang
        for (DonHang dh : danhSachDonHang) {
            if (dh.getMaDonHang().equals(maDonHang)) {
                dhHuy = dh;
                break;
            }
        }
        
        // Neu tim thay, xoa khoi danh sach va tra ve
        if (dhHuy != null) {
            danhSachDonHang.remove(dhHuy);
            return dhHuy;
        }
        
        return null; // Khong tim thay
    }
}