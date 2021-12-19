package QuanLyBanDienThoai;

import java.io.Serializable;

public class PhieuXuat extends Phieu implements Serializable {

    public PhieuXuat(String IDPhieu, String date, String IDNhanVien,
                     DSChiTietPhieu dsChiTietPhieu, int sumMoney) {
        super(IDPhieu, date, dsChiTietPhieu, sumMoney, IDNhanVien);
    }

    public void inPhieu(){
        super.inPhieu();
        System.out.printf("|%-16s|%-16s|\n", IDNhanVien, "Phiếu xuất");
    }

    public void xemChiTietPhieu()
    {
        System.out.printf("+%-50s+\n", Lib.repeatStr("-", 50));
        System.out.printf("|%-10s%-30s%-10s|\n", Lib.repeatStr(" ", 10), "Chi tiết phiếu xuất", Lib.repeatStr(" ", 10));
        super.xemChiTietPhieu();
        dsChiTietPhieu.xuatDS();
    }

    public String toString()
    {
        return super.toString();
    }

    public String getIDNhanVien() {
        return IDNhanVien;
    }

    public void setIDNhanVien(String IDNhanVien) {
        this.IDNhanVien = IDNhanVien;
    }

}
