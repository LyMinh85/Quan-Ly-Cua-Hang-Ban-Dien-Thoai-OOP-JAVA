package QuanLyBanDienThoai;

import java.io.Serializable;

public class NhanVien extends Nguoi implements Serializable {
    private int mucLuong;

    public NhanVien(String id, String hoTen, String diaChi, String SDT, String ngaySinh, String gioiTinh, String CMND, String password, int mucLuong) {
        super(id, hoTen, diaChi, SDT, ngaySinh, gioiTinh, CMND, password);
        this.mucLuong = mucLuong;
    }

    public void xuatThongTin()
    {
        super.xuatThongTin();
        System.out.printf("%-10s|", mucLuong);
    }

    public String toString()
    {
        return super.toString() + "-" + mucLuong;
    }

    public int getMucLuong() {
        return mucLuong;
    }

    public void setMucLuong(int mucLuong) {
        this.mucLuong = mucLuong;
    }
}
