package quanlybanhang;

import java.util.Scanner;

public abstract class SanPham {
    protected String maSP;
    protected String tenSP;
    protected double giaGoc;
    protected int soLuongTon;

    public SanPham() {
    }

    public SanPham(String maSP, String tenSP, double giaGoc, int soLuongTon) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.giaGoc = giaGoc;
        this.soLuongTon = soLuongTon;
    }

    public void nhap(Scanner scanner) {
        System.out.print("Nhap ten SP: ");
        setTenSP(scanner.nextLine());
        
        System.out.print("Nhap gia goc: ");
        setGiaGoc(scanner.nextDouble());
        
        System.out.print("Nhap so luong ton kho: ");
        setSoLuongTon(scanner.nextInt());
        scanner.nextLine(); // Xoa bo dem
        
        nhapThongTinThem(scanner);
    }

    public abstract void nhapThongTinThem(Scanner scanner);

    public void xuat() {
        System.out.println("-------------------------");
        System.out.println("Ma SP: " + getMaSP());
        System.out.println("Ten: " + getTenSP());
        System.out.printf("Gia ban: %,.0f VND\n", getGiaBan());
        System.out.println("Ton kho: " + getSoLuongTon());
    }

    public abstract double tinhThue();

    public double getGiaBan() {
        return this.giaGoc + tinhThue();
    }

    // Getters and Setters
    public String getMaSP() { return maSP; }
    public void setMaSP(String maSP) { this.maSP = maSP; }

    public String getTenSP() { return tenSP; }
    public void setTenSP(String tenSP) { this.tenSP = tenSP; }

    public double getGiaGoc() { return giaGoc; }
    public void setGiaGoc(double giaGoc) { this.giaGoc = giaGoc; }

    public int getSoLuongTon() { return soLuongTon; }
    public void setSoLuongTon(int soLuongTon) { this.soLuongTon = soLuongTon; }
}