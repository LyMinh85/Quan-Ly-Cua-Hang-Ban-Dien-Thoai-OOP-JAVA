package QuanLyBanDienThoai;

import java.io.Serializable;

public abstract class NhanVien extends Nguoi implements Serializable {
    protected int mucLuong;

    public NhanVien(String id, String hoTen, String diaChi, String SDT, String ngaySinh, String gioiTinh, String CMND, String password, int mucLuong) {
        super(id, hoTen, diaChi, SDT, ngaySinh, gioiTinh, CMND, password);
        this.mucLuong = mucLuong;
    }

    public abstract void xuatThongTin();

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
