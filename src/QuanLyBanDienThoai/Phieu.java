package QuanLyBanDienThoai;

import java.io.Serializable;

public abstract class Phieu implements Serializable {
    protected String IDPhieu;
    protected String date;
    protected String IDNhanVien;
    protected DSChiTietPhieu dsChiTietPhieu;
    protected int sumMoney;

    public Phieu(String IDPhieu, String date, DSChiTietPhieu dsChiTietPhieu, int sumMoney, String IDNhanVien){
        setIDPhieu(IDPhieu);
        setDate(date);
        this.dsChiTietPhieu = dsChiTietPhieu;
        this.sumMoney = sumMoney;
        this.IDNhanVien = IDNhanVien;
    }

    public abstract void inPhieu();

    public abstract void xemChiTietPhieu();

    public String toString()
    {
        return IDPhieu + "-" + date + "-" + sumMoney + "-" + IDNhanVien;
    }

    public String getIDPhieu() {
        return IDPhieu;
    }

    public void setIDPhieu(String IDPhieu) {
        this.IDPhieu = IDPhieu;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {this.date = date;}

    public int getSumMoney() {
        return sumMoney;
    }

    public void setSumMoney(int sumMoney) {
        this.sumMoney = sumMoney;
    }

    public DSChiTietPhieu getDsChiTietPhieu() {
        return dsChiTietPhieu;
    }

    public void setDsChiTietPhieu(DSChiTietPhieu dsChiTietPhieu) {
        this.dsChiTietPhieu = dsChiTietPhieu;
    }

    public String getIDNhanVien() {
        return IDNhanVien;
    }

    public void setIDNhanVien(String IDNhanVien) {
        this.IDNhanVien = IDNhanVien;
    }
}
