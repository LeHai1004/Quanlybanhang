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
        // MaKH se duoc nhap o Main de kiem tra tinh duy nhat
        System.out.print("Nhap ten Khach hang: ");
        setTenKH(scanner.nextLine());
        System.out.print("Nhap dia chi: ");
        setDiaChi(scanner.nextLine());
    }
    
    public void xuat() {
        System.out.println("-------------------------");
        System.out.println("Ma KH: " + getMaKH());
        System.out.println("Ten: " + getTenKH());
        System.out.println("Dia chi: " + getDiaChi());
    }

    public GioHang getGioHang() { return this.gioHang; }
    public String getTenKH() { return tenKH; }
    public String getMaKH() { return maKH; }
    public void setMaKH(String maKH) { this.maKH = maKH; }
    public void setTenKH(String tenKH) { this.tenKH = tenKH; }
    public void setDiaChi(String diaChi) { this.diaChi = diaChi; }

    public String getDiaChi() {
        return diaChi;
    }
}