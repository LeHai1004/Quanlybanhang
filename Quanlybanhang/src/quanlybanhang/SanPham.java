package quanlybanhang;

import java.util.Scanner;

public abstract class SanPham {
    // --- THAY DOI: private -> protected ---
    protected String maSP;
    protected String tenSP;
    protected double giaGoc;
    protected int soLuongTon;

    public SanPham() {}

    public SanPham(String maSP, String tenSP, double giaGoc, int soLuongTon) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.giaGoc = giaGoc;
        this.soLuongTon = soLuongTon;
    }

    public void nhap(Scanner scanner) {
        // MaSP nhap o Main
        System.out.print("Nhap ten SP: ");
        setTenSP(scanner.nextLine());
        System.out.print("Nhap gia goc: ");
        setGiaGoc(scanner.nextDouble());
        System.out.print("Nhap so luong ton kho: ");
        setSoLuongTon(scanner.nextInt());
        scanner.nextLine(); 
    }

    public void xuat() {
        // Bay gio lop con co the goi truc tiep bien maSP thay vi getMaSP()
        // nhung de giu code chay on dinh, ta cu dung get nhu cu cung duoc
        System.out.println("-------------------------");
        System.out.println("Ma SP: " + this.maSP); // Dung truc tiep this.maSP
        System.out.println("Ten: " + this.tenSP);
        System.out.printf("Gia ban: %,.0f VND\n", getGiaBan());
        System.out.println("Ton kho: " + this.soLuongTon);
    }
    
    public abstract double tinhThue();
    public double getGiaBan() { return this.giaGoc + tinhThue(); }
    
    // Getters/Setters (Van giu lai vi Main van can dung chung)
    public int getSoLuongTon() { return soLuongTon; }
    public void setSoLuongTon(int soLuongTon) { this.soLuongTon = soLuongTon; }
    public String getMaSP() { return maSP; }
    public String getTenSP() { return tenSP; }
    public double getGiaGoc() { return giaGoc; }
    
    public void setMaSP(String maSP) { this.maSP = maSP; }
    public void setTenSP(String tenSP) { this.tenSP = tenSP; }
    public void setGiaGoc(double giaGoc) { this.giaGoc = giaGoc; }
}