package quanlybanhang;

import java.util.Scanner;

public class DienThoai extends SanPham {
    private String hangSX;
    private int dungLuongPin;

    public DienThoai() {}

    public DienThoai(String maSP, String tenSP, double giaGoc, String hangSX, int dungLuongPin, int soLuongTon) {
        super(maSP, tenSP, giaGoc, soLuongTon);
        this.hangSX = hangSX;
        this.dungLuongPin = dungLuongPin;
    }

    @Override
    public void nhap(Scanner scanner) {
        super.nhap(scanner); // Goi ham nhap() cua lop cha (SanPham) truoc
        
        // Nhap thong tin rieng cua Dien Thoai
        System.out.print("Nhap hang san xuat (vi du: Apple): ");
        setHangSX(scanner.nextLine());
        System.out.print("Nhap dung luong pin (vi du: 5000): ");
        setDungLuongPin(scanner.nextInt());
        scanner.nextLine();
    }
    
    @Override
    public void xuat() {
        super.xuat(); // Goi ham xuat() cua lop cha (SanPham)
        
        // In tiep thong tin rieng cua Dien thoai
        System.out.println("Hang SX: " + getHangSX());
        System.out.println("Dung luong Pin: " + getDungLuongPin() + " mAh");
    }
    
    @Override
    public double tinhThue() { return getGiaGoc() * 0.1; }
    public void setHangSX(String hangSX) { this.hangSX = hangSX; }
    public void setDungLuongPin(int dungLuongPin) { this.dungLuongPin = dungLuongPin; }
    public String getHangSX() { return hangSX; }
    public int getDungLuongPin() { return dungLuongPin; }
}