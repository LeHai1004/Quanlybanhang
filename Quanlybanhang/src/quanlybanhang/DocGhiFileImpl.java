package quanlybanhang;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DocGhiFileImpl implements IDocGhiFile {

    private static final String FILE_SAN_PHAM = "sanpham.txt";
    private static final String FILE_KHACH_HANG = "khachhang.txt";
    private static final String FILE_DON_HANG = "donhang.txt";
    private static final String FILE_CHI_TIET_DON_HANG = "chitietdonhang.txt";

    private void ghiFile(String tenFile, String data) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(tenFile))) {
            bw.write(data);
        } catch (IOException e) {
            System.out.println("Loi ghi file " + tenFile + ": " + e.getMessage());
        }
    }

    @Override
    public void luuSanPham(List<SanPham> dsSanPham) {
        StringBuilder sb = new StringBuilder();
        for (SanPham sp : dsSanPham) {
            if (sp instanceof Sach) {
                Sach s = (Sach) sp;
                sb.append("SACH|").append(s.getMaSP()).append("|")
                  .append(s.getTenSP()).append("|").append(s.getGiaGoc()).append("|")
                  .append(s.getSoLuongTon()).append("|").append(s.getTacGia()).append("|")
                  .append(s.getSoTrang()).append("\n");
            } else if (sp instanceof DienThoai) {
                DienThoai dt = (DienThoai) sp;
                sb.append("DIENTHOAI|").append(dt.getMaSP()).append("|")
                  .append(dt.getTenSP()).append("|").append(dt.getGiaGoc()).append("|")
                  .append(dt.getSoLuongTon()).append("|").append(dt.getHangSX()).append("|")
                  .append(dt.getDungLuongPin()).append("\n");
            }
        }
        ghiFile(FILE_SAN_PHAM, sb.toString());
    }

    @Override
    public void luuKhachHang(List<KhachHang> dsKhachHang) {
        StringBuilder sb = new StringBuilder();
        for (KhachHang kh : dsKhachHang) {
            sb.append(kh.getMaKH()).append("|").append(kh.getTenKH()).append("|")
              .append(kh.getDiaChi()).append("\n");
        }
        ghiFile(FILE_KHACH_HANG, sb.toString());
    }

    @Override
    public void luuDonHang(List<DonHang> dsDonHang) {
        StringBuilder sbDonHang = new StringBuilder();
        StringBuilder sbChiTiet = new StringBuilder();

        for (DonHang dh : dsDonHang) {
            sbDonHang.append(dh.getMaDonHang()).append("|")
                     .append(dh.getKhachHang().getMaKH()).append("\n");

            for (ChiTietDonHang ct : dh.getDanhSachChiTiet()) {
                sbChiTiet.append(dh.getMaDonHang()).append("|")
                         .append(ct.getSanPham().getMaSP()).append("|")
                         .append(ct.getSoLuong()).append("\n");
            }
        }
        ghiFile(FILE_DON_HANG, sbDonHang.toString());
        ghiFile(FILE_CHI_TIET_DON_HANG, sbChiTiet.toString());
    }

    @Override
    public List<SanPham> docSanPham() {
        List<SanPham> dsSP = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_SAN_PHAM))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                String type = parts[0];
                if (type.equals("SACH")) {
                    dsSP.add(new Sach(parts[1], parts[2], Double.parseDouble(parts[3]),
                            parts[5], Integer.parseInt(parts[6]), Integer.parseInt(parts[4])));
                } else if (type.equals("DIENTHOAI")) {
                    dsSP.add(new DienThoai(parts[1], parts[2], Double.parseDouble(parts[3]),
                            parts[5], Integer.parseInt(parts[6]), Integer.parseInt(parts[4])));
                }
            }
        } catch (Exception e) { /* Bo qua */ }
        return dsSP;
    }

    @Override
    public List<KhachHang> docKhachHang() {
        List<KhachHang> dsKH = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_KHACH_HANG))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                dsKH.add(new KhachHang(parts[0], parts[1], parts[2]));
            }
        } catch (Exception e) { /* Bo qua */ }
        return dsKH;
    }

    // Ham phuc tap nhat: Doc va "noi" du lieu
    @Override
    public List<DonHang> docDonHang(List<SanPham> dsSP, List<KhachHang> dsKH) {
        List<DonHang> dsDH = new ArrayList<>();
        // Buoc 1: Doc file donhang.txt de lay thong tin co ban
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_DON_HANG))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|"); // maDonHang|maKH
                String maDH = parts[0];
                String maKH = parts[1];
                
                // Tim doi tuong KhachHang tuong ung
                KhachHang khachHang = null;
                for(KhachHang kh : dsKH) {
                    if (kh.getMaKH().equals(maKH)) {
                        khachHang = kh;
                        break;
                    }
                }
                
                if (khachHang != null) {
                    dsDH.add(new DonHang(maDH, khachHang, new ArrayList<>()));
                }
            }
        } catch (Exception e) { /* Bo qua */ }

        // Buoc 2: Doc file chitietdonhang.txt de them chi tiet vao don hang
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_CHI_TIET_DON_HANG))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|"); // maDonHang|maSP|soLuong
                String maDH = parts[0];
                String maSP = parts[1];
                int soLuong = Integer.parseInt(parts[2]);

                // Tim SanPham
                SanPham sanPham = null;
                for (SanPham sp : dsSP) {
                    if (sp.getMaSP().equals(maSP)) {
                        sanPham = sp;
                        break;
                    }
                }
                
                // Tim DonHang
                DonHang donHang = null;
                for (DonHang dh : dsDH) {
                    if (dh.getMaDonHang().equals(maDH)) {
                        donHang = dh;
                        break;
                    }
                }

                if (sanPham != null && donHang != null) {
                    donHang.themChiTiet(new ChiTietDonHang(sanPham, soLuong));
                }
            }
        } catch (Exception e) { /* Bo qua */ }
        
        return dsDH;
    }
}