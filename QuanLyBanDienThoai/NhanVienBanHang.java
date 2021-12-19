package QuanLyBanDienThoai;

import java.io.Serializable;

public class NhanVienBanHang extends NhanVien implements Serializable {
    public NhanVienBanHang(String id, String hoTen, String diaChi, String SDT,
                           String ngaySinh, String gioiTinh, String CMND, String password, int mucLuong)
    {
        super(id, hoTen, diaChi, SDT, ngaySinh, gioiTinh, CMND, password, mucLuong);
    }

    public void xuatThongTin()
    {
        super.xuatThongTin();
        System.out.printf("%-16s|%n", "Bán hàng");
    }
}
