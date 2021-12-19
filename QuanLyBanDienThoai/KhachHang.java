package QuanLyBanDienThoai;

import java.io.Serializable;

public class KhachHang extends Nguoi implements Serializable {

    public KhachHang(String id, String hoTen, String diaChi, String SDT, String ngaySinh, String gioiTinh, String CMND, String password) {
        super(id, hoTen, diaChi, SDT, ngaySinh, gioiTinh, CMND, password);
    }

    public void xuatThongTin()
    {
        super.xuatThongTin();
        System.out.println();
    }

    public String toString()
    {
        return super.toString();
    }



}
