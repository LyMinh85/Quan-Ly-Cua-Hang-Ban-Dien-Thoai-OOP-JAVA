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
public class ChiTietHoaDon implements Serializable {
    public String madienthoai;
    public int sluong;
    public int gia;

    //Xây dựng hàm không tham số
    public ChiTietHoaDon() {
        this.madienthoai = null;
        this.sluong = 0;
        this.gia = 0;
    }
    //Xây dựng hàm có tham số
    public ChiTietHoaDon(String madienthoai, int sluong, int gia) {
        this.madienthoai = madienthoai;
        this.sluong = sluong;
        this.gia = gia;
    }

    public void xuatThongTin(String maHD)
    {
        System.out.printf("│%-20s│%-20s│%-20s│%-20s│\n", maHD, getMadienthoai(),getSluong(),getGia());
    }

    public String getMadienthoai() {
        return madienthoai;
    }

    public void setMadienthoai(String madienthoai) {
        this.madienthoai = madienthoai;
    }

    public int getSluong() {
        return sluong;
    }

    public void setSluong(int sluong) {
        this.sluong = sluong;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }
  
}
    
