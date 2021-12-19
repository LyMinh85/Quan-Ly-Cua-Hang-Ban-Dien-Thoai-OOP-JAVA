package QuanLyBanDienThoai;

import java.io.Serializable;

public class iphone extends DienThoai implements Serializable {

	public iphone() {
		heDieuHanh ="IOS";
	}

	public void heDieuHanh()
	{
		System.out.println(heDieuHanh);
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

	 