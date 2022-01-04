package QuanLyBanDienThoai;

import java.io.Serializable;

public class Samsung extends DienThoai implements Serializable {
	public Samsung()
	{
		heDieuHanh = "ANDROID";
	}

	public Samsung(String maDienThoai, String tenDienThoai, String maNSX, String color,
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