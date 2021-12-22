package QuanLyBanDienThoai;

import java.io.Serializable;

public class nokia extends DienThoai implements Serializable {
	public nokia()
	{
		heDieuHanh = "ANDROID";
	}
	public nokia(String maDienThoai, String tenDienThoai, String maNSX,String color, int giathanh,String theHe, String maNhaCungCap) {
		super(maDienThoai, tenDienThoai, maNSX, color, giathanh, theHe, maNhaCungCap);
		heDieuHanh = "ANDROID";
	}

	
	
	
	public void xuatThongTin() {
		super.xuatThongTin();
//		System.out.println(heDieuHanh);
		
	}
	
	public void nhap(String maDienThoai)
	{
		super.nhap(maDienThoai);
	}
}