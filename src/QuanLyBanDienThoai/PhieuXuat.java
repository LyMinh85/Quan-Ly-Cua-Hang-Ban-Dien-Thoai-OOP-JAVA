package QuanLyBanDienThoai;

import java.io.Serializable;

public class PhieuXuat extends Phieu implements Serializable {
    private String IDNhanVienThuKho;
    private String IDHoaDon;
    private String tinhTrang = "Chờ xuất kho";
    public PhieuXuat(String IDPhieu, String date, String IDNhanVienBanHang, String IDNhanVienThuKho,
                     DSChiTietPhieu dsChiTietPhieu, int sumMoney, String IDHoaDon) {
        super(IDPhieu, date, dsChiTietPhieu, sumMoney, IDNhanVienBanHang);
        this.IDNhanVienThuKho = IDNhanVienThuKho;
        this.IDHoaDon = IDHoaDon;
    }

    public void inPhieu(){
        System.out.printf("│%-8s│%-8s│%-12s│%-16s", IDPhieu, IDHoaDon, date, sumMoney);
        String colorTinhTrang = tinhTrang;
        if(tinhTrang.equals("Chờ xuất kho"))
            colorTinhTrang = Lib.toYellowText(tinhTrang);
        else
            colorTinhTrang = Lib.toGreenText(tinhTrang);
        System.out.printf("│%-25s│%-25s│%-16s\n", IDNhanVien, IDNhanVienThuKho, colorTinhTrang);
    }
    //┘ └ ┌ ┐ ├ ┤ ┴ ┬ ┼ │ ─
    public void xemChiTietPhieu()
    {
        System.out.printf("┌%-50s┐\n", Lib.repeatStr("─", 50));
        System.out.printf("│%-10s%-30s%-10s│\n", Lib.repeatStr(" ", 10), "Chi tiết phiếu xuất", Lib.repeatStr(" ", 10));
        System.out.printf("│%-50s│\n", ("ID phiếu: " + getIDPhieu()));
        System.out.printf("│%-50s│\n", ("Date: " + getDate()));
        System.out.printf("│%-50s│\n", ("Tổng tiền: " + getSumMoney()));
        System.out.printf("│%-50s│\n", ("ID nhân viên bán hàng: " + getIDNhanVien()));
        System.out.printf("│%-50s│\n", ("ID nhân viên thủ kho: " + getIDNhanVienThuKho()));
        System.out.printf("│%-50s│\n", ("Tình trạng: " + getTinhTrang()));
        dsChiTietPhieu.xuatDS();
        System.out.printf("└%-16s┴%-16s┴%-16s┘\n", Lib.repeatStr("─", 16), Lib.repeatStr("─", 16), Lib.repeatStr("─", 16));
    }

    public void xuatHang(Nguoi nguoi, DanhSachHoaDon danhSachHoaDon)
    {
        if(tinhTrang.equals("Đã xuất kho"))
            return;
        IDNhanVienThuKho = nguoi.getId();
        tinhTrang = "Đã xuất kho";
        for (HoaDon hoaDon : danhSachHoaDon.getHoaDonArrayList())
            if(hoaDon.getMahd().equals(IDHoaDon))
                hoaDon.setTinhTrang("Đã xuất kho");
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

    public String getIDNhanVienThuKho() {
        return IDNhanVienThuKho;
    }

    public void setIDNhanVienThuKho(String IDNhanVienThuKho) {
        this.IDNhanVienThuKho = IDNhanVienThuKho;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }
}
