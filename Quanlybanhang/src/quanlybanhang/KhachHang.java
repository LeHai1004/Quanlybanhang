/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlybanhang;



/**
 *
 * @author HOANG HAI
 */
public class KhachHang {
    private String maKH;
    private String tenKH;
    private String diaChi;

    // Composition (Thành phần ⚫️)
    private GioHang gioHang;

    public KhachHang(String maKH, String tenKH, String diaChi) {
        this.maKH = maKH;
        this.tenKH = tenKH;
        this.diaChi = diaChi;
        this.gioHang = new GioHang();
    }

    public GioHang getGioHang() {
        return this.gioHang;
    }
    
    /**
     * Hàm đặt hàng, giao tiếp với cả 2 hệ thống quản lý
     */
    public void datHang(String maDonHang, IQuanLySanPham qlSanPham, IQuanLyDonHang qlDonHang) {
        if (gioHang.isEmpty()) {
            System.out.println("Giỏ hàng rỗng, không thể đặt hàng.");
            return;
        }

        // B1: Kiểm tra kho
        for (ChiTietDonHang ct : gioHang.getDanhSachChiTiet()) {
            if (!qlSanPham.kiemTraTonKho(ct.getSanPham().getMaSP(), ct.getSoLuong())) {
                System.out.println("LỖI: Sản phẩm '" + ct.getSanPham().getTenSP() + "' không đủ tồn kho!");
                return;
            }
        }

        // B2: Tạo đơn hàng
        DonHang donHangMoi = new DonHang(maDonHang, this, gioHang.getDanhSachChiTiet());
        
        // B3: Cập nhật kho
        for (ChiTietDonHang ct : donHangMoi.getDanhSachChiTiet()) {
            qlSanPham.capNhatTonKho(ct.getSanPham().getMaSP(), ct.getSoLuong());
        }

        // B4: Lưu đơn hàng vào hệ thống
        qlDonHang.themDonHang(donHangMoi);
        
        // B5: Rỗng giỏ
        gioHang.lamTrongGio();
        System.out.println("Khách hàng '" + this.tenKH + "' đã đặt hàng thành công!");
    }

    public String getTenKH() {
        return tenKH;
    }

    
    
}
