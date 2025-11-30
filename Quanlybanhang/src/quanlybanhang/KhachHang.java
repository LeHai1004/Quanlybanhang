package quanlybanhang;

import java.util.Scanner;

public class KhachHang {
    private String maKH;
    private String tenKH;
    private String diaChi;
    private GioHang gioHang;

    public KhachHang() {
        this.gioHang = new GioHang();
    }

    public KhachHang(String maKH, String tenKH, String diaChi) {
        this.maKH = maKH;
        this.tenKH = tenKH;
        this.diaChi = diaChi;
        this.gioHang = new GioHang();
    }

    public void nhap(Scanner scanner) {
        System.out.print("Nhap ten Khach hang: ");
        this.tenKH = scanner.nextLine();
        
        System.out.print("Nhap dia chi: ");
        this.diaChi = scanner.nextLine();
    }

    public void xuat() {
        System.out.println("-------------------------");
        System.out.println("Ma KH: " + maKH);
        System.out.println("Ten: " + tenKH);
        System.out.println("Dia chi: " + diaChi);
    }

    public GioHang getGioHang() { return gioHang; }
    
    public String getTenKH() { return tenKH; }
    public void setTenKH(String tenKH) { this.tenKH = tenKH; }

    public String getMaKH() { return maKH; }
    public void setMaKH(String maKH) { this.maKH = maKH; }

    public String getDiaChi() { return diaChi; }
    public void setDiaChi(String diaChi) { this.diaChi = diaChi; }
}