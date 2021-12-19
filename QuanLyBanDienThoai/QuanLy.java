package QuanLyBanDienThoai;

import java.io.Serializable;

public class QuanLy extends Nguoi implements Serializable {

    public QuanLy(String id, String hoTen, String diaChi, String SDT, String ngaySinh, String gioiTinh, String CMND, String password) {
        super(id, hoTen, diaChi, SDT, ngaySinh, gioiTinh, CMND, password);
    }

    public String toString()
    {
        return super.toString();
    }

}
