package QuanLyBanDienThoai;

import java.io.Serializable;

public class ChiTietPhieu implements Serializable {
    private String IDDienThoai;
    private int soLuong;
    private int donGia;

    public ChiTietPhieu(String IDDienThoai, int soLuong, int donGia) {
        setIDDienThoai(IDDienThoai);
        setSoLuong(soLuong);
        setDonGia(donGia);
    }
    //┘ └ ┌ ┐ ├ ┤ ┴ ┬ ┼ │ ─
    public void inChiTietPhieu(){
        System.out.printf("│%-16s│%-16s│%-16s│\n", IDDienThoai, soLuong, donGia);
    }

    public String toString()
    {
        return IDDienThoai + "-" + soLuong + "-" + donGia;
    }

    public String getIDDienThoai() {
        return IDDienThoai;
    }

    public void setIDDienThoai(String IDDienThoai) {
        this.IDDienThoai = IDDienThoai;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getDonGia() {
        return donGia;
    }

    public void setDonGia(int donGia) {
        this.donGia = donGia;
    }
}
