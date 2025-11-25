package quanlybanhang;

import java.util.Scanner;

public class Sach extends SanPham {
    private String tacGia;
    private int soTrang;

    public Sach() {}

    public Sach(String maSP, String tenSP, double giaGoc, String tacGia, int soTrang, int soLuongTon) {
        super(maSP, tenSP, giaGoc, soLuongTon);
        this.tacGia = tacGia;
        this.soTrang = soTrang;
    }

    @Override
    public void nhap(Scanner scanner) {
        super.nhap(scanner); // Goi ham nhap() cua lop cha (SanPham) truoc
        
        // Nhap thong tin rieng cua Sach
        System.out.print("Nhap tac gia: ");
        setTacGia(scanner.nextLine());
        System.out.print("Nhap so trang: ");
        setSoTrang(scanner.nextInt());
        scanner.nextLine();
    }
    
    @Override
    public void xuat() {
        super.xuat(); // Goi ham xuat() cua lop cha (SanPham) truoc
        
        // In tiep thong tin rieng cua Sach
        System.out.println("Tac gia: " + getTacGia());
        System.out.println("So trang: " + getSoTrang());
    }
    
    @Override
    public double tinhThue() { return 0.0; }
    public void setTacGia(String tacGia) { this.tacGia = tacGia; }
    public void setSoTrang(int soTrang) { this.soTrang = soTrang; }
    public String getTacGia() { return tacGia; }
    public int getSoTrang() { return soTrang; }
}