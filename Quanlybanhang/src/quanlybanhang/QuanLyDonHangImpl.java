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
    public void setData(List<DonHang> ds) {
        this.danhSachDonHang = (ds != null) ? ds : new ArrayList<>();
    }

    @Override
    public void xoaDonHang(String maDonHang, QuanTriVien admin) {
        if (admin == null) {
            System.out.println("LOI: Ban khong co quyen!");
            return;
        }
        DonHang dh = null;
        for (DonHang d : danhSachDonHang) {
            if (d.getMaDonHang().equals(maDonHang)) dh = d;
        }
        if (dh != null) {
            danhSachDonHang.remove(dh);
            System.out.println("Admin da xoa vinh vien don: " + maDonHang);
        } else {
            System.out.println("Loi: Khong tim thay don hang.");
        }
    }

    @Override
    public DonHang huyDonHang(String maDonHang) {
        for (DonHang dh : danhSachDonHang) {
            if (dh.getMaDonHang().equals(maDonHang)) {
                if ("Da Huy".equals(dh.getTrangThai())) {
                    return null; // Da huy roi
                }
                dh.setTrangThai("Da Huy");
                return dh;
            }
        }
        return null;
    }
}