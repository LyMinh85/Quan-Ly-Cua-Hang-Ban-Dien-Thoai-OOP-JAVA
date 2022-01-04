package QuanLyBanDienThoai;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Locale;


public class DanhSachDT implements Serializable, DanhSach {

	protected DienThoai[] listDT;
	private int count = 0;

	public DanhSachDT() {
		listDT = new DienThoai[5];
		listDT[0] = new Apple(getID(), "Iphone X", "NSX001", "Pink", 15200, "10", "Meow", 200);
		listDT[1] = new Samsung(getID(), "SamSung Galaxy", "NSX002", "Blue", 12500, "10", "Fish", 150);
		listDT[2] = new Oppo(getID(), "Oppo A95", "NSX003", "Red", 9640, "10", "Fish", 120);
		listDT[3] = new Nokia(getID(), "Nokia G10", "NSX004", "Black", 7840, "10", "Meow", 80);
		listDT[4] = new Apple(getID(), "Iphone Xs Max", "NSX001", "Purple", 21000, "11", "Meow", 100);
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

	public String getID()
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
		System.out.format("┌%-15s┬%-18s┬%-15s┬%-10s┬%-15s┬%-15s┬%-15s┬%-15s┐%n", Lib.repeatStr("─", 15),
				Lib.repeatStr("─", 18), Lib.repeatStr("─", 15), Lib.repeatStr("─", 10),
				Lib.repeatStr("─", 15), Lib.repeatStr("─", 15), Lib.repeatStr("─", 15),
				Lib.repeatStr("─", 15));
		System.out.format("│%-15s│%-18s│%-15s│%-10s│%-15s│%-15s│%-15s│%-15s│%n",smaDienThoai,stenDienThoai,
				smaNSX,scolor,sgiathanh,smaNhaCungCap, "Hệ điều hành", "Tồn kho");
		System.out.format("├%-15s┼%-18s┼%-15s┼%-10s┼%-15s┼%-15s┼%-15s┼%-15s┤%n", Lib.repeatStr("─", 15),
				Lib.repeatStr("─", 18), Lib.repeatStr("─", 15), Lib.repeatStr("─", 10),
				Lib.repeatStr("─", 15), Lib.repeatStr("─", 15), Lib.repeatStr("─", 15),
				Lib.repeatStr("─", 15));
	}

	public void xuatDS() {
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
		danhSachNSX.xuatDS();
		do {
			check = false;
			mansx = Lib.takeStringInput("Nhập mã nhà sản xuất: ");
			if(danhSachNSX.timkiemNSX(mansx) == -1)
				Lib.printError("Không có mã nhà sản xuất này");
		}while (check);
		return mansx;
	}
	
	public void themDT(DanhSachNSX danhSachNSX, DanhSachNhaCungCap dsncc) {
		String madt = getID();
		String mansx = nhapMaNSX(danhSachNSX);
		if(mansx.equals("stop"))
			return;
		String mancc = Lib.nhapIDNhaCungCap(dsncc);
		listDT = Arrays.copyOf(listDT, getsoLuong() + 1);

		switch(danhSachNSX.timKiemTheoID(mansx).gettenNSX())
		{
			case "Apple" -> listDT[getsoLuong()-1] = new Apple();
			case "SamSung" -> listDT[getsoLuong()-1] = new Samsung();
			case "Oppo" -> listDT[getsoLuong()-1] = new Oppo();
			case "Nokia" -> listDT[getsoLuong()-1] = new Nokia();
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
		if(index == 0)
			Lib.printError("Không tìm được từ khóa này trong danh sách");
		else
		{
			xuatTieuDe();
			for(int i = 0; i < index; i++)
				dsdt[i].xuatThongTin();
		}
	}

	public DienThoai[] getListDT() {
		return listDT;
	}

	public void setListDT(DienThoai[] listDT) {
		this.listDT = listDT;
	}

	public void menuSua(DanhSachNSX danhSachNSX, DanhSachNhaCungCap dsncc)
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

	public void menu(DanhSachNSX danhSachNSX, DanhSachNhaCungCap dsncc)
	{
		while(true) {
			xuatDS();
			System.out.println("1. Thêm điện thoại");
			System.out.println("2. Tìm kiếm trong bảng");
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
