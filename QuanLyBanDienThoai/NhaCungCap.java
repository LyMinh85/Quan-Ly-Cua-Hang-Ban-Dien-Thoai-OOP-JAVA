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
    private String tencc;
    private String diachi;
    private String sdt;
    private String email;
    private String macc;

    public NhaCungCap() {
        this.tencc = null;
        this.diachi = null;
        this.sdt = null;
        this.email = null;
        this.macc = null;
    }

    public NhaCungCap(String tencc, String diachi, String sdt, String email, String macc) {
        this.tencc = tencc;
        this.diachi = diachi;
        this.sdt = sdt;
        this.email = email;
        this.macc = macc;
    }

    public void xuatThongTin()
    {
        System.out.printf("|%-20s|%-20s|%-20s|%-20s|%-20s| \n", macc, tencc, diachi, sdt, email);
    }

    public String getTencc() {
        return tencc;
    }

    public void setTencc(String tencc) {
        this.tencc = tencc;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
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

    public String getMacc() {
        return macc;
    }

    public void setMacc(String macc) {
        this.macc = macc;
    }
          
    
}
