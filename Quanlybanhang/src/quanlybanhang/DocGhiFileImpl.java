package quanlybanhang;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DocGhiFileImpl implements IDocGhiFile {
    private static final String FILE_SP = "sanpham.txt";
    private static final String FILE_KH = "khachhang.txt";
    private static final String FILE_DH = "donhang.txt";
    private static final String FILE_CT = "chitietdonhang.txt";

    private void ghiFile(String tenFile, String data) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(tenFile))) {
            bw.write(data);
        } catch (IOException e) {
            System.out.println("Loi ghi file: " + e.getMessage());
        }
    }

    @Override
    public void luuSanPham(List<SanPham> ds) {
        StringBuilder sb = new StringBuilder();
        for (SanPham sp : ds) {
            if (sp instanceof Sach) {
                Sach s = (Sach) sp;
                sb.append("SACH|").append(s.getMaSP()).append("|").append(s.getTenSP()).append("|")
                  .append(s.getGiaGoc()).append("|").append(s.getSoLuongTon()).append("|")
                  .append(s.getTacGia()).append("|").append(s.getSoTrang()).append("\n");
            } else if (sp instanceof DienThoai) {
                DienThoai dt = (DienThoai) sp;
                sb.append("DIENTHOAI|").append(dt.getMaSP()).append("|").append(dt.getTenSP()).append("|")
                  .append(dt.getGiaGoc()).append("|").append(dt.getSoLuongTon()).append("|")
                  .append(dt.getHangSX()).append("|").append(dt.getDungLuongPin()).append("\n");
            }
        }
        ghiFile(FILE_SP, sb.toString());
    }

    @Override
    public void luuKhachHang(List<KhachHang> ds) {
        StringBuilder sb = new StringBuilder();
        for (KhachHang kh : ds) {
            sb.append(kh.getMaKH()).append("|").append(kh.getTenKH()).append("|")
              .append(kh.getDiaChi()).append("\n");
        }
        ghiFile(FILE_KH, sb.toString());
    }

    @Override
    public void luuDonHang(List<DonHang> ds) {
        StringBuilder sbDH = new StringBuilder();
        StringBuilder sbCT = new StringBuilder();
        for (DonHang dh : ds) {
            sbDH.append(dh.getMaDonHang()).append("|").append(dh.getKhachHang().getMaKH())
                .append("|").append(dh.getTrangThai()).append("\n");
            for (ChiTietDonHang ct : dh.getDanhSachChiTiet()) {
                sbCT.append(dh.getMaDonHang()).append("|").append(ct.getSanPham().getMaSP())
                    .append("|").append(ct.getSoLuong()).append("\n");
            }
        }
        ghiFile(FILE_DH, sbDH.toString());
        ghiFile(FILE_CT, sbCT.toString());
    }

    @Override
    public List<SanPham> docSanPham() {
        List<SanPham> ds = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_SP))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split("\\|");
                if (p[0].equals("SACH")) {
                    ds.add(new Sach(p[1], p[2], Double.parseDouble(p[3]), p[5], Integer.parseInt(p[6]), Integer.parseInt(p[4])));
                } else if (p[0].equals("DIENTHOAI")) {
                    ds.add(new DienThoai(p[1], p[2], Double.parseDouble(p[3]), p[5], Integer.parseInt(p[6]), Integer.parseInt(p[4])));
                }
            }
        } catch (Exception e) {}
        return ds;
    }

    @Override
    public List<KhachHang> docKhachHang() {
        List<KhachHang> ds = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_KH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split("\\|");
                ds.add(new KhachHang(p[0], p[1], p[2]));
            }
        } catch (Exception e) {}
        return ds;
    }

    @Override
    public List<DonHang> docDonHang(List<SanPham> dsSP, List<KhachHang> dsKH) {
        List<DonHang> dsDH = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_DH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split("\\|");
                String maKH = p[1];
                KhachHang kh = dsKH.stream().filter(k -> k.getMaKH().equals(maKH)).findFirst().orElse(null);
                if (kh != null) {
                    DonHang dh = new DonHang(p[0], kh, new ArrayList<>());
                    if (p.length > 2) dh.setTrangThai(p[2]);
                    dsDH.add(dh);
                }
            }
        } catch (Exception e) {}

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_CT))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split("\\|");
                String maDH = p[0];
                DonHang dh = dsDH.stream().filter(d -> d.getMaDonHang().equals(maDH)).findFirst().orElse(null);
                SanPham sp = dsSP.stream().filter(s -> s.getMaSP().equals(p[1])).findFirst().orElse(null);
                if (dh != null && sp != null) {
                    dh.themChiTiet(new ChiTietDonHang(sp, Integer.parseInt(p[2])));
                }
            }
        } catch (Exception e) {}
        return dsDH;
    }
}