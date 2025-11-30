package quanlybanhang;

import java.util.Scanner;

public class Sach extends SanPham {
    private String tacGia;
    private int soTrang;

    public Sach() {
    }

    public Sach(String maSP, String tenSP, double giaGoc, String tacGia, int soTrang, int soLuongTon) {
        super(maSP, tenSP, giaGoc, soLuongTon);
        this.tacGia = tacGia;
        this.soTrang = soTrang;
    }

    @Override
    public void nhapThongTinThem(Scanner scanner) {
        System.out.print("Nhap tac gia: ");
        setTacGia(scanner.nextLine());
        
        System.out.print("Nhap so trang: ");
        setSoTrang(scanner.nextInt());
        scanner.nextLine();
    }

    @Override
    public void xuat() {
        super.xuat();
        System.out.println("Tac gia: " + getTacGia());
        System.out.println("So trang: " + getSoTrang());
    }

    @Override
    public double tinhThue() {
        return 0.0;
    }

    public String getTacGia() { return tacGia; }
    public void setTacGia(String tacGia) { this.tacGia = tacGia; }

    public int getSoTrang() { return soTrang; }
    public void setSoTrang(int soTrang) { this.soTrang = soTrang; }
}