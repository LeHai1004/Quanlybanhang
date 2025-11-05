/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlybanhang;

/**
 *
 * @author HOANG HAI
 */
public abstract class SanPham {
    private String maSP;
    private String tenSP;
    private double giaGoc;
    private int soLuongTon; // Quản lý tồn kho

    public SanPham(String maSP, String tenSP, double giaGoc, int soLuongTon) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.giaGoc = giaGoc;
        this.soLuongTon = soLuongTon;
    }

    // Phương thức trừu tượng
    public abstract double tinhThue();

    public double getGiaBan() {
        return this.giaGoc + tinhThue();
    }

    // Getters/Setters
    public int getSoLuongTon() {
        return soLuongTon;
    }

    public void setSoLuongTon(int soLuongTon) {
        this.soLuongTon = soLuongTon;
    }
    
    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public double getGiaGoc() {
        return giaGoc;
    }

    public void setGiaGoc(double giaGoc) {
        this.giaGoc = giaGoc;
    }
}
