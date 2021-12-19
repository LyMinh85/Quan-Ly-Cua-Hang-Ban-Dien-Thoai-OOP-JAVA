package QuanLyBanDienThoai;

import java.io.Serializable;

public class Phieu implements Serializable {
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

    public void inPhieu(){
        System.out.printf("|%-16s|%-16s|%-16s", IDPhieu, date, sumMoney);
    }

    public void xemChiTietPhieu() {
        System.out.printf("|%-50s|\n", ("ID phiếu: " + getIDPhieu()));
        System.out.printf("|%-50s|\n", ("Date: " + getDate()));
        System.out.printf("|%-50s|\n", ("Tổng tiền: " + getSumMoney()));
        System.out.printf("|%-50s|\n", ("ID nhân viên: " + getIDNhanVien()));
    }

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

    public void setDate(String date) {
        if(Lib.checkDate(date)) {
            this.date = date;
            Lib.printMessage("Sửa thành công");
        }
        else
            Lib.printError("Sửa thất bại do ngày tháng năm không đúng định dạng");

    }

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
