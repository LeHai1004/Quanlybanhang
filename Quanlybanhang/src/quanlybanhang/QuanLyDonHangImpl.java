/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlybanhang;

/**
 *
 * @author HOANG HAI
 */
import java.util.ArrayList;
import java.util.List;

public class QuanLyDonHangImpl implements IQuanLyDonHang {

    // Aggregation (Kết tập ⚪️)
    private List<DonHang> danhSachDonHang;

    public QuanLyDonHangImpl() {
        this.danhSachDonHang = new ArrayList<>();
    }

    @Override
    public void themDonHang(DonHang dh) {
        this.danhSachDonHang.add(dh);
        System.out.println("Đã lưu đơn hàng: " + dh.getMaDonHang());
    }

    @Override
    public List<DonHang> layTatCaDonHang() {
        return this.danhSachDonHang;
    }
}
