package QuanLyBanDienThoai;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Locale;


public class DanhSachDT implements Serializable {

	protected DienThoai[] listDT;
	private int count = 0;

	public DanhSachDT() {
		listDT = new DienThoai[4];
		listDT[0] = new iphone(getMaDT(), "Iphone X", "NSX001", "Pink", 15200, "10", "Meow");
		listDT[1] = new samsung(getMaDT(), "SamSung Galaxy", "NSX002", "Blue", 12500, "10", "Fish");
		listDT[2] = new oppo(getMaDT(), "Oppo A95", "NSX003", "Red", 9640, "10", "Fish");
		listDT[3] = new nokia(getMaDT(), "Nokia G10", "NSX004", "Black", 7840, "10", "Meow");
	}
	
	public void setDienThoai(DienThoai[]dt) {
		this.listDT =dt;
	}
	public DienThoai[]dt(){
		return listDT;
	}
	public void setsoLuong(int soLuong) {
		listDT = Arrays.copyOf(listDT, soLuong);
	}
	public int getsoLuong() {
		return listDT.length;
	}
	private String getMaDT()
	{
		count++;
		Integer a = count;
		String str = a.toString();
		while(str.length() != 3)
			str = "0" + str;
		str = "DT" + str;
		return str;
	}

	public void xuatTieuDe()
	{
		String smaDienThoai, stenDienThoai, smaNSX, scolor, sgiathanh, smaNhaCungCap;
		smaDienThoai = "Mã Điện Thoại";
		stenDienThoai = "Tên Điện Thoại";
		smaNSX = "Mã Nhà Sản Xuất";
		scolor = "Màu sắc";
		sgiathanh = "Giá thành";
		smaNhaCungCap = "Mã Nhà Cung Cấp";
		System.out.format("+%18s+%18s+%18s+%18s+%18s+%18s+%18s+%n","------------------","------------------","------------------","------------------","------------------","------------------", "------------------");
		System.out.format("|%18s|%18s|%18s|%18s|%18s|%18s|%18s|%n",smaDienThoai,stenDienThoai,smaNSX,scolor,sgiathanh,smaNhaCungCap, "Hệ điều hành");
		System.out.format("+%18s+%18s+%18s+%18s+%18s+%18s+%18s+%n","------------------","------------------","------------------","------------------","------------------","------------------", "------------------");
	}

	public void xuat() {
		if (getsoLuong() <= 0) {
			Lib.printError("Không có Điện Thoại nào trong danh sách! ");
			return;
		}
		xuatTieuDe();
		for(int i = 0; i < listDT.length; i++) {
			listDT[i].xuatThongTin();
		}
	}

	public String nhapMaNSX(DanhSachNSX danhSachNSX)
	{
		boolean check;
		String mansx;
		danhSachNSX.xuat();
		do {
			check = false;
			mansx = Lib.takeStringInput("Nhập mã nhà sản xuất: ");
			if(danhSachNSX.timkiemNSX(mansx) == -1)
				Lib.printError("Không có mã nhà sản xuất này");
		}while (check);
		return mansx;
	}
	
	public void themDT(DanhSachNSX danhSachNSX, danhsachcungcap dsncc) {
		String madt = getMaDT();
		String mansx = nhapMaNSX(danhSachNSX);
		if(mansx.equals("stop"))
			return;
		String mancc = Lib.nhapIDNhaCungCap(dsncc);
		listDT = Arrays.copyOf(listDT, getsoLuong() + 1);

		switch(danhSachNSX.timKiemTheoID(mansx).gettenNSX())
		{
			case "Apple" -> listDT[getsoLuong()-1] = new iphone();
			case "SamSung" -> listDT[getsoLuong()-1] = new samsung();
			case "Oppo" -> listDT[getsoLuong()-1] = new oppo();
			case "Nokia" -> listDT[getsoLuong()-1] = new nokia();
		}
		listDT[getsoLuong()-1].nhap(madt, mansx, mancc);
	}
	
	public int timkiemmaDienThoai(String maDienThoai) {
		for(int i = 0; i < listDT.length; i++) {
			if(listDT[i].getmaDienThoai().equals(maDienThoai)) {
				return i;
			}
		}
		return -1;
	}
	
	public void xoaDT() {
		if(getsoLuong() == 0)
		{
			Lib.printError("Danh sách đang rỗng");
			return;
		}

		String maDienThoai = Lib.takeStringInput("Nhập mã Điện Thoại cần xóa: ");
		int index = timkiemmaDienThoai(maDienThoai);
		if(index == -1) {
			Lib.printError("Không có mã Điện Thoại này");
		}else {
			for(int i = index; i < listDT.length-1; i++) {
				listDT[i] = listDT[i+1];
			}
			listDT[listDT.length-1] = null;
			listDT = Arrays.copyOf(listDT, listDT.length-1);
		}
	}
	
	public void search()
	{
		String tuKhoa = Lib.takeStringInput("Nhập từ khóa cần tìm: ");
		DienThoai []dsdt = new DienThoai[listDT.length];

		int gia;
		try {gia = Integer.parseInt(tuKhoa);}
		catch (NumberFormatException e){gia = Integer.MIN_VALUE;}

		int index = 0;
		for(DienThoai dienThoai : listDT)
		{
			if(dienThoai.getmaDienThoai().toLowerCase(Locale.ROOT).contains(tuKhoa.toLowerCase(Locale.ROOT)) ||
					dienThoai.gettenDienThoai().toLowerCase(Locale.ROOT).contains(tuKhoa.toLowerCase(Locale.ROOT)) ||
					dienThoai.getmaNhaCungCap().toLowerCase(Locale.ROOT).contains(tuKhoa.toLowerCase(Locale.ROOT)) ||
					dienThoai.getmaNSX().toLowerCase(Locale.ROOT).contains(tuKhoa.toLowerCase(Locale.ROOT)) ||
					dienThoai.getcolor().toLowerCase(Locale.ROOT).contains(tuKhoa.toLowerCase(Locale.ROOT)) ||
					dienThoai.getheDieuHanh().toLowerCase(Locale.ROOT).contains(tuKhoa.toLowerCase(Locale.ROOT)) ||
					dienThoai.getheDieuHanh().toLowerCase(Locale.ROOT).contains(tuKhoa.toLowerCase(Locale.ROOT)) ||
					dienThoai.getgiathanh() == gia
			)
			{
				dsdt[index++] = dienThoai;
			}
		}

		System.out.println(Lib.toBlueText("Kết quả tìm kiếm theo từ khóa: ")  + Lib.toGreenText(tuKhoa));
		xuatTieuDe();
		for(int i = 0; i < index; i++)
			dsdt[i].xuatThongTin();
		if(index == 0)
			Lib.printError("Không tìm được từ khóa này trong danh sách");
	}

	public DienThoai[] getListDT() {
		return listDT;
	}

	public void setListDT(DienThoai[] listDT) {
		this.listDT = listDT;
	}

	public void menuSua(DanhSachNSX danhSachNSX, danhsachcungcap dsncc)
	{
		if(getsoLuong() == 0)
		{
			Lib.printError("Danh sách đang rỗng");
			return;
		}
		String id = Lib.takeStringInput("Nhập mã điện thoại cần sửa: ");
		int index = timkiemmaDienThoai(id);
		if(index == -1)
			Lib.printError("Không tìm thấy");
		else
		{
			boolean outChaneDT;
			do {
				xuatTieuDe();
				getListDT()[index].xuatThongTin();
				outChaneDT = false;
				System.out.println("1. Sửa tên điện thoại");
				System.out.println("2. Sửa mã NSX");
				System.out.println("3. Sửa màu");
				System.out.println("4. Sửa giá thành");
				System.out.println("5. Sửa mã nhà cung cấp");
				System.out.println("0. Thoát sửa");
				switch (Lib.takeInputChoice(0,5))
				{
					case 1 -> {
						String ten = Lib.takeStringInput("Nhập tên DT mới: ");
						getListDT()[index].settenDienThoai(ten);
					}
					case 2 -> {
						getListDT()[index].setmaNSX(nhapMaNSX(danhSachNSX));}
					case 3 -> {
						getListDT()[index].setcolor(Lib.takeStringInput("Nhập màu mới: "));}
					case 4 -> {
						getListDT()[index].setgiathanh(Lib.takeIntegerInput("Nhập giá thành mới: "));}
					case 5 -> {
						getListDT()[index].setmaNhaCungCap(Lib.nhapIDNhaCungCap(dsncc));}
					case 0 -> {outChaneDT = true;}
				}
				if(!outChaneDT)
					Lib.clearScreen();
			}while(!outChaneDT);
		}
	}

	public void menu(DanhSachNSX danhSachNSX, danhsachcungcap dsncc)
	{
		while(true) {
			xuat();
			System.out.println("1. Thêm Điện Thoại ");
			System.out.println("2. Tìm kiếm");
			System.out.println("3. Xóa Điện Thoại");
			System.out.println("4. Sửa");
			System.out.println("0. Thoát");
			boolean out = false;
			switch (Lib.takeInputChoice(0, 4)) {
				case 1 -> themDT(danhSachNSX, dsncc);
				case 2 -> search();
				case 3 -> xoaDT();
				case 4 -> menuSua(danhSachNSX, dsncc);
				case 0 -> out = true;
			}
			if(out)
				break;
			Lib.clearScreen();
		}
	}

}
