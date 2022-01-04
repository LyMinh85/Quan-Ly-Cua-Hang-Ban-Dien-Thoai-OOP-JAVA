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
public class NhaCungCap implements Serializable {
    private String ten;
    private String diaChi;
    private String sdt;
    private String email;
    private String maNCC;

    public NhaCungCap() {
        this.ten = null;
        this.diaChi = null;
        this.sdt = null;
        this.email = null;
        this.maNCC = null;
    }

    public NhaCungCap(String tencc, String diachi, String sdt, String email, String macc) {
        this.ten = tencc;
        this.diaChi = diachi;
        this.sdt = sdt;
        this.email = email;
        this.maNCC = macc;
    }

    public void xuatThongTin()
    {
        System.out.printf("│%-20s│%-20s│%-20s│%-20s│%-20s│ \n", maNCC, ten, diaChi, sdt, email);
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMaNCC() {
        return maNCC;
    }

    public void setMaNCC(String maNCC) {
        this.maNCC = maNCC;
    }
          
    
}
