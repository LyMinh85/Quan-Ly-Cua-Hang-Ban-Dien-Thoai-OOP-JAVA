package QuanLyBanDienThoai;

import java.io.Serializable;
import java.util.Scanner;

public abstract class DienThoai implements Serializable {
	protected String maDienThoai;
	protected String tenDienThoai;
	protected String maNSX;
	protected String color;
	protected int giathanh;
	protected String theHe;
	protected String maNhaCungCap;
	protected String heDieuHanh;
	 
	//Scanner sc = new Scanner(System.in);
	
	public DienThoai() {
		maDienThoai = null;
		tenDienThoai = null;
		maNSX = null;
		color = null;
		giathanh = 0;
		theHe = null;
		maNhaCungCap = null;
	}
	
	public DienThoai(String maDienThoai, String tenDienThoai, String maNSX,String color, int giathanh,String theHe, String maNhaCungCap) {
		setmaDienThoai(maDienThoai);
		settenDienThoai(tenDienThoai);
		setmaNSX(maNSX);
		setcolor(color);
		setgiathanh(giathanh);
		settheHe(theHe);
		setmaNhaCungCap(maNhaCungCap);
	}
	
	public String getheDieuHanh() {
		return heDieuHanh;
	}
	public void setheDieuHanh(String heDieuHanh) {
		this.heDieuHanh = heDieuHanh;
	}

	public String getmaDienThoai() {
		return maDienThoai;
	}
	public void setmaDienThoai(String maDienThoai) {
		this.maDienThoai = maDienThoai;
	}
	public String gettenDienThoai() {
		return tenDienThoai;
	}
	public void settenDienThoai(String tenDienThoai) {
		this.tenDienThoai = tenDienThoai;
	}
	public String getmaNSX() {
		return maNSX;
	}
	public void setmaNSX(String maNSX) {
		this.maNSX = maNSX;
	}
	public String getcolor() {
		return color;
	}
	public void setcolor(String color) {
		this.color = color;
	}
	public int getgiathanh() {
		return giathanh;
	}
	public void setgiathanh(int giathanh) {
		this.giathanh = giathanh;
	}
	public String gettheHe() {
		return theHe;
	}
	public void settheHe(String theHe) {
		this.theHe = theHe;
	}
	public String getmaNhaCungCap() {
		return maNhaCungCap;
	}
	public void setmaNhaCungCap(String maNhaCungCap) {
		this.maNhaCungCap = maNhaCungCap;
	}
	
	public void nhap(String maDienThoai, String maNSX, String maNhaCungCap) {
		this.maDienThoai = maDienThoai;
		this.tenDienThoai = Lib.takeStringInput("Nhập tên điện thoại: ");
//		this.maNSX = Lib.takeStringInput("Nhập mã nhà sản xuất: ");
		this.maNSX = maNSX;
		this.color = Lib.takeStringInput("Nhập màu sắc điện thoại: ");
		this.giathanh = Lib.takeIntegerInput("Nhập giá thành điện thoại: ");
		this.theHe = Lib.takeStringInput("Nhập thế hệ điện thoại: ");
//		this.maNhaCungCap = Lib.takeStringInput("Nhập mã nhà cung cấp: ");
		this.maNhaCungCap = maNhaCungCap;
	}

	public void nhap(String maDienThoai) {
		this.maDienThoai = maDienThoai;
		this.tenDienThoai = Lib.takeStringInput("Nhập tên điện thoại: ");
		this.maNSX = Lib.takeStringInput("Nhập mã nhà sản xuất: ");
		this.color = Lib.takeStringInput("Nhập màu sắc điện thoại: ");
		this.giathanh = Lib.takeIntegerInput("Nhập giá thành điện thoại: ");
		this.theHe = Lib.takeStringInput("Nhập thế hệ điện thoại: ");
		this.maNhaCungCap = Lib.takeStringInput("Nhập mã nhà cung cấp: ");
	}
	
	public void xuatThongTin() {
		System.out.format("|%18s|%18s|%18s|%18s|%18s|%18s|%18s|%n",maDienThoai,tenDienThoai,maNSX,color,giathanh,maNhaCungCap, heDieuHanh);
        System.out.format("+%18s+%18s+%18s+%18s+%18s+%18s+%18s+%n","------------------","------------------","------------------","------------------","------------------","------------------", "------------------");
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//DienThoai dt = new DienThoai();
		//dt.nhap();
		//dt.xuatThongTin();
		Scanner sc = new Scanner(System.in);
		DienThoai []arrDT = new DienThoai[4];
		for(int i = 0; i <arrDT.length;i++) {
			System.out.println("Chọn các số 1,2,3,4 tương ứng với tên loại điện thoại:");
			System.out.println("1.Iphone ");
			System.out.println("2.Samsung ");
			System.out.println("3.Oppo ");
			System.out.println("4.Nokia ");
			switch (sc.nextLine()) {
			case "1" -> {
				arrDT[i] = new iphone();
				arrDT[i].xuatThongTin();
			}
			case "2" -> {
				arrDT[i] = new samsung();
				arrDT[i].xuatThongTin();
			}
			case "3" -> {
				arrDT[i] = new oppo();
				arrDT[i].xuatThongTin();
			}
			case "4" -> {
				arrDT[i] = new nokia();
				arrDT[i].xuatThongTin();
			}

			}
		}
		
	
	}


}
