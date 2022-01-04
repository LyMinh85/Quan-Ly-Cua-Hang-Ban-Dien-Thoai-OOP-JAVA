package QuanLyBanDienThoai;

import java.io.Serializable;

public class Oppo extends DienThoai implements Serializable {
	public Oppo()
	{
		heDieuHanh = "ANDROID";
	}

	public Oppo(String maDienThoai, String tenDienThoai, String maNSX, String color,
				int giathanh, String theHe, String maNhaCungCap, int soLuong) {
		super(maDienThoai, tenDienThoai, maNSX, color, giathanh, theHe, maNhaCungCap, soLuong);
		heDieuHanh = "ANDROID";
	}

	public void xuatThongTin() {
		super.xuatThongTin();
	}
	
	public void nhap(String maDienThoai)
	{
		super.nhap(maDienThoai);
	}
}