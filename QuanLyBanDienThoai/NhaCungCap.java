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
    private int sdt;
    private String email;
    private String macc;

    public NhaCungCap() {
        this.tencc = null;
        this.diachi = null;
        this.sdt = 0;
        this.email = null;
        this.macc = null;
    }

    public NhaCungCap(String tencc, String diachi, int sdt, String email, String macc) {
        this.tencc = tencc;
        this.diachi = diachi;
        this.sdt = sdt;
        this.email = email;
        this.macc = macc;
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

    public int getSdt() {
        return sdt;
    }

    public void setSdt(int sdt) {
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
