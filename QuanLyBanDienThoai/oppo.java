package QuanLyBanDienThoai;

import java.io.Serializable;

public class oppo extends DienThoai implements Serializable {

	public oppo() {
		heDieuHanh ="ANDROID";
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