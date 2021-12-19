package QuanLyBanDienThoai;

import java.io.Serializable;

public class samsung extends DienThoai implements Serializable {

	public samsung() {
		heDieuHanh ="ANDROID";
	}
	
	
	
	public void xuatThongTin() {
		super.xuatThongTin();
		//System.out.println(heDieuHanh);
		
	}
	
	public void nhap(String maDienThoai)
	{
		super.nhap(maDienThoai);
	}
	
	}