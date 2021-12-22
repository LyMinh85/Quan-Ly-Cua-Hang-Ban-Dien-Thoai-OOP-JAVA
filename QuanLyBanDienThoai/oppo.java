package QuanLyBanDienThoai;

import java.io.Serializable;

public class oppo extends DienThoai implements Serializable {
	public oppo()
	{
		heDieuHanh = "ANDROID";
	}

	public oppo(String maDienThoai, String tenDienThoai, String maNSX,String color, int giathanh,String theHe, String maNhaCungCap) {
		super(maDienThoai, tenDienThoai, maNSX, color, giathanh, theHe, maNhaCungCap);
		heDieuHanh = "ANDROID";
	}
	
	
	public void xuatThongTin() {
		super.xuatThongTin();
	//	System.out.println(heDieuHanh);
		
	}
	
	public void nhap(String maDienThoai)
	{
		super.nhap(maDienThoai);
	}
}