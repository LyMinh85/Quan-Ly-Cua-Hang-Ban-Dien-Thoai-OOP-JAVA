package QuanLyBanDienThoai;

import java.io.Serializable;
import java.util.ArrayList;

public class PhieuNhap extends  Phieu implements Serializable {
    private String IDNhaCungCap;

    public PhieuNhap(String IDPhieu, String date, String IDNhaCungCap, String IDNhanVien,
                     DSChiTietPhieu dsChiTietPhieu, int sumMoney) {
        super(IDPhieu, date, dsChiTietPhieu, sumMoney, IDNhanVien);
        setIDNhaCungCap(IDNhaCungCap);
    }

    //┘ └ ┌ ┐ ├ ┤ ┴ ┬ ┼ │ ─
    public void inPhieu(){
        System.out.printf("│%-16s│%-16s│%-16s", IDPhieu, date, sumMoney);
        System.out.printf("│%-16s│%-16s│\n", IDNhanVien, IDNhaCungCap);
    }

    public void xemChiTietPhieu()
    {
        System.out.printf("┌%-50s┐\n", Lib.repeatStr("─", 50));
        System.out.printf("│%-10s%-30s%-10s│\n", Lib.repeatStr(" ", 10), "Chi tiết phiếu nhập", Lib.repeatStr(" ", 10));
        System.out.printf("│%-50s│\n", ("ID phiếu: " + getIDPhieu()));
        System.out.printf("│%-50s│\n", ("Date: " + getDate()));
        System.out.printf("│%-50s│\n", ("Tổng tiền: " + getSumMoney()));
        System.out.printf("│%-50s│\n", ("ID nhân viên: " + getIDNhanVien()));
        System.out.printf("│%-50s│\n", ("ID nhà cung cấp: " + getIDNhaCungCap()));
        dsChiTietPhieu.xuatDS();
        System.out.printf("└%-16s┴%-16s┴%-16s┘\n", Lib.repeatStr("─", 16), Lib.repeatStr("─", 16), Lib.repeatStr("─", 16));

    }

    public String toString()
    {
        return super.toString() + "-" + IDNhaCungCap;
    }

    public String getIDNhaCungCap() {
        return IDNhaCungCap;
    }

    public void setIDNhaCungCap(String IDNhaCungCap) {
        this.IDNhaCungCap = IDNhaCungCap;
    }


}
