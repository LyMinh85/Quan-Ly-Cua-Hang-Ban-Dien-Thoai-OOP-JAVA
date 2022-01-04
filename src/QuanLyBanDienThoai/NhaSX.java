package QuanLyBanDienThoai;

import java.io.Serializable;

public class NhaSX implements Serializable {
	protected String maNSX;
	protected String tenNSX;
	protected String sdtNSX;
	protected String diachiNSX;
	protected String emailNSX;
	
	public NhaSX(){
		maNSX = null;
		tenNSX = null;
		sdtNSX = null;
		diachiNSX = null;
		emailNSX = null;
	}
	
	public NhaSX(String maNSX, String tenNSX, String sdtNSX, String diachiNSX, String emailNSX) {
		setmaNSX(maNSX);
		settenNSX(tenNSX);
		setsdtNSX(sdtNSX);
		setdiachiNSX(diachiNSX);
		setemailNSX(emailNSX);
	}
	
	public String getmaNSX() {
		return maNSX;
	}
	public void setmaNSX(String maNSX) {
		this.maNSX = maNSX;
	}
	public String gettenNSX() {
		return tenNSX;
	}
	public void settenNSX(String tenNSX) {
		this.tenNSX = tenNSX;
	}
	public String getsdtNSX() {
		return sdtNSX;
	}
	public void setsdtNSX(String sdtNSX) {
		this.sdtNSX = sdtNSX;
	}
	public String getdiachiNSX() {
		return diachiNSX;
	}
	public void setdiachiNSX(String diachiNSX) {
		this.diachiNSX = diachiNSX;
	}
	public String getemailNSX() {
		return emailNSX;
	}
	public void setemailNSX(String emailNSX) {
		this.emailNSX = emailNSX;
	}
	
	public void nhapNSX(String maNSX) {
		this.maNSX = maNSX;
		this.tenNSX = Lib.takeStringInput("Nhập tên nhà sản xuất: ");
		this.sdtNSX = Lib.takeStringInput("Nhập số điện thoại nhà sản xuất: ");
		this.diachiNSX = Lib.takeStringInput("Nhập địa chỉ nhà sản xuất: ");
		this.emailNSX = Lib.takeStringInput("Nhập email nhà sản xuất: ");
	}
	
	public void xuatNSX() {
		System.out.printf("│%-20s│%-20s│%-20s│%-20s│%-20s│%n", maNSX, tenNSX, sdtNSX, diachiNSX, emailNSX);
	}


}
