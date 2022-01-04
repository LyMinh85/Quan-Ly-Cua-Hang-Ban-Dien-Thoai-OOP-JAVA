package QuanLyBanDienThoai;

import java.io.Serializable;

public class QuanLy extends Nguoi implements Serializable {

    public QuanLy(String id, String hoTen, String diaChi, String SDT, String ngaySinh, String gioiTinh, String CMND, String password) {
        super(id, hoTen, diaChi, SDT, ngaySinh, gioiTinh, CMND, password);
    }

    @Override
    public void xuatThongTin() {
        System.out.printf("|%-16s|%-16s|%-16s|%-10s|%-10s|%-10s|%-9s|%-16s|", id, hoTen, diaChi, SDT, ngaySinh, gioiTinh, CMND, password);
        System.out.println();
    }


    public String toString()
    {
        return super.toString();
    }

}
