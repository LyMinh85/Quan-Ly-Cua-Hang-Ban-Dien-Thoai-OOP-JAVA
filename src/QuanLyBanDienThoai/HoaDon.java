/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QuanLyBanDienThoai;

import java.io.Serializable;

/**
 *
 * @author User
 */
public class HoaDon implements Serializable {
    private String mahd;
    private String manv;
    private String makh;
    private String timexuat;
    private int tongtien;
    private DanhSachChiTietHoaDon dsCTHD;
    private String tinhTrang;
    
    public HoaDon() {
        this.mahd = null;
        this.tongtien=0;
        this.dsCTHD = null;
    }

    public HoaDon(String mahd, String manv, String makh, String timexuat, int tongtien, DanhSachChiTietHoaDon dsCTHD, String tinhTrang) {
        this.mahd = mahd;
        this.manv = manv;
        this.makh = makh;
        this.timexuat = timexuat;
        this.tongtien = tongtien;
        this.dsCTHD = dsCTHD;
        this.tinhTrang = tinhTrang;
    }
    //┘ └ ┌ ┐ ├ ┤ ┴ ┬ ┼ │
    public void xuatThongTin()
    {
        String tinhTrang = null;
        if(getTinhTrang().equals("Đang chờ xác nhận"))
            tinhTrang = Lib.TEXT_BLUE + getTinhTrang() + Lib.TEXT_RESET;
        else if(getTinhTrang().equals("Đã xác nhận") || getTinhTrang().equals("Đã xuất kho"))
            tinhTrang = Lib.TEXT_YELLOW + getTinhTrang() + Lib.TEXT_RESET;
        else if(getTinhTrang().equals("Đã nhận hàng"))
            tinhTrang = Lib.TEXT_GREEN + getTinhTrang() + Lib.TEXT_RESET;
        System.out.printf("│%-20s│%-20s│%-20s│%-10s│%-15s│%-25s \n", getMahd() , getManv(), getMakh(), getTimexuat(), getTongtien(), tinhTrang);
    }

    public int getTongtien() {
        return tongtien;
    }

    public void setTongtien(int tongtien) {
        this.tongtien = tongtien;
    }
    
    
    public String getManv() {
        return manv;
    }

    public void setManv(String manv) {
        this.manv = manv;
    }

    public String getMakh() {
        return makh;
    }

    public void setMakh(String makh) {
        this.makh = makh;
    }

    public String getTimexuat() {
        return timexuat;
    }

    public void setTimexuat(String timexuat) {
        this.timexuat = timexuat;
    }   

    public DanhSachChiTietHoaDon getDsCTHD() {
        return dsCTHD;
    }

    public void setDsCTHD(DanhSachChiTietHoaDon dsCTHD) {
        this.dsCTHD = dsCTHD;
    }

    public String getMahd() {
        return mahd;
    }

    public void setMahd(String mahd) {
        this.mahd = mahd;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }
}
    
    
    
    
    
