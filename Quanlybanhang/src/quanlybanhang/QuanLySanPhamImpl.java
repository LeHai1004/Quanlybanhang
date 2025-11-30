package quanlybanhang;

import java.util.ArrayList;
import java.util.List;

public class QuanLySanPhamImpl implements IQuanLySanPham {
    private List<SanPham> danhSachSP;

    public QuanLySanPhamImpl() {
        this.danhSachSP = new ArrayList<>();
    }

    @Override
    public void them(SanPham sp) {
        danhSachSP.add(sp);
        System.out.println("Da them: " + sp.getTenSP());
    }

    @Override
    public void sua(String maSP, SanPham spMoi) {
        SanPham spTim = timKiemTheoMa(maSP);
        if (spTim != null) {
            int index = danhSachSP.indexOf(spTim);
            danhSachSP.set(index, spMoi);
            System.out.println("-> Da cap nhat san pham " + maSP);
        } else {
            System.out.println("-> Loi: Khong tim thay SP.");
        }
    }

    @Override
    public void xoa(String maSP) {
        SanPham spXoa = timKiemTheoMa(maSP);
        if (spXoa != null) {
            danhSachSP.remove(spXoa);
            System.out.println("-> Da xoa san pham " + maSP);
        } else {
            System.out.println("-> Loi: Khong tim thay SP.");
        }
    }

    @Override
    public List<SanPham> timKiemTheoTen(String ten) {
        List<SanPham> kq = new ArrayList<>();
        for (SanPham sp : danhSachSP) {
            if (sp.getTenSP().toLowerCase().contains(ten.toLowerCase())) {
                kq.add(sp);
            }
        }
        return kq;
    }

    @Override
    public List<SanPham> layTatCa() {
        return this.danhSachSP;
    }

    @Override
    public SanPham timKiemTheoMa(String maSP) {
        for (SanPham sp : danhSachSP) {
            if (sp.getMaSP().equals(maSP)) {
                return sp;
            }
        }
        return null;
    }

    @Override
    public boolean kiemTraTonKho(String maSP, int soLuongMua) {
        SanPham sp = timKiemTheoMa(maSP);
        return (sp != null) && (sp.getSoLuongTon() >= soLuongMua);
    }

    @Override
    public void capNhatTonKho(String maSP, int soLuongDaBan) {
        SanPham sp = timKiemTheoMa(maSP);
        if (sp != null) {
            sp.setSoLuongTon(sp.getSoLuongTon() - soLuongDaBan);
        }
    }

    @Override
    public void setData(List<SanPham> dsSanPham) {
        this.danhSachSP = (dsSanPham != null) ? dsSanPham : new ArrayList<>();
    }
}