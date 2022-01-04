package QuanLyBanDienThoai;

import java.io.Serializable;

public class Apple extends DienThoai implements Serializable {
	public Apple()
	{
		heDieuHanh = "IOS";
	}
	public Apple(String maDienThoai, String tenDienThoai, String maNSX, String color, int giathanh, String theHe, String maNhaCungCap, int soLuong) {
		super(maDienThoai, tenDienThoai, maNSX, color, giathanh, theHe, maNhaCungCap, soLuong);
		heDieuHanh = "IOS";
	}
	
	public void xuatThongTin() {
		super.xuatThongTin();
	}
	
	public void nhap(String maDienThoai)
	{
		super.nhap(maDienThoai);
	}
}

	 