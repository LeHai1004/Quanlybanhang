package quanlybanhang;

import java.util.Scanner;

public class DienThoai extends SanPham {
    private String hangSX;
    private int dungLuongPin;

    public DienThoai() {
    }

    public DienThoai(String maSP, String tenSP, double giaGoc, String hangSX, int dungLuongPin, int soLuongTon) {
        super(maSP, tenSP, giaGoc, soLuongTon);
        this.hangSX = hangSX;
        this.dungLuongPin = dungLuongPin;
    }

    @Override
    public void nhapThongTinThem(Scanner scanner) {
        System.out.print("Nhap hang san xuat: ");
        setHangSX(scanner.nextLine());
        
        System.out.print("Nhap dung luong pin: ");
        setDungLuongPin(scanner.nextInt());
        scanner.nextLine();
    }

    @Override
    public void xuat() {
        super.xuat();
        System.out.println("Hang SX: " + getHangSX());
        System.out.println("Pin: " + getDungLuongPin() + " mAh");
    }

    @Override
    public double tinhThue() {
        return getGiaGoc() * 0.1;
    }

    public String getHangSX() { return hangSX; }
    public void setHangSX(String hangSX) { this.hangSX = hangSX; }

    public int getDungLuongPin() { return dungLuongPin; }
    public void setDungLuongPin(int dungLuongPin) { this.dungLuongPin = dungLuongPin; }
}