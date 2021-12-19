package QuanLyBanDienThoai;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Scanner;


public class DanhSachDT implements Serializable {

	protected DienThoai[]dt;
	
	public DanhSachDT() {
		dt = new DienThoai[0];
	}
	
	public void setDienThoai(DienThoai[]dt) {
		this.dt=dt;
	}
	public DienThoai[]dt(){
		return dt;
	}
	public void setsoLuong(int soLuong) {
		dt = Arrays.copyOf(dt, soLuong);
	}
	public int getsoLuong() {
		return dt.length;
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

	
//	public void nhapBangConsole() {
//		Scanner sc = new Scanner(System.in);
//		try {
//			int soLuong = Lib.takeSoLuongCanTao("Nhập số lượng điện thoại: ");
//			dt = new DienThoai[soLuong];
//
//			for(int i=0; i<soLuong; i++) {
//				System.out.println("Nhập Loại Điện Thoại: ");
//				System.out.println("1. Iphone");
//				System.out.println("2. Samsung");
//				System.out.println("3. Oppo ");
//				System.out.println("4. Nokia ");
//				switch(Lib.takeInputChoice(1,4))
//				{
//				case 1 -> {dt[i] = new iphone();}
//				case 2 -> {dt[i] = new samsung();}
//				case 3 -> {dt[i] = new oppo();}
//				case 4 -> {dt[i] = new nokia();}
//				}
//				System.out.println("\n Nhập điện thoại thứ " + (i+1) + " ");
//				dt[i].nhap();
//			}
//		}catch (NumberFormatException e) {
//			System.out.println("Đã xảy ra lỗi!!!");
//			e.printStackTrace();
//		}
//
//	}
	
	
	public void xuat() {
		if (getsoLuong() <= 0) {
			Lib.printError("Không có Điện Thoại nào trong danh sách! ");
			return;
		}
		xuatTieuDe();
		for(int i=0; i < dt.length; i++) {
			dt[i].xuatThongTin();
		}
	}

	//Check mã NSX có trùng hay ko
	public String nhapMaDT()
	{
		boolean check;
		String madt;
		do {
			check = false;
			madt = Lib.takeStringInput("Nhập mã điện thoại: ");
			if(timkiemmaDienThoai(madt) != -1) {
				check = true;
				Lib.printError("Mã điện thoại này đã có");
			}
		}while (check);
		return madt;
	}

	public String nhapMaNSX(DanhSachNSX danhSachNSX)
	{
		boolean check;
		String mansx;
		do {
			check = false;
			mansx = Lib.takeStringInput("Nhập mã nhà sản xuất: ");
			if(danhSachNSX.timkiemNSX(mansx) == -1) {
				check = true;
				Lib.printError("Mã nhà sản xuất này chưa có");
				System.out.println("1. Xem danh sách nhà sản xuất");
				System.out.println("2. Dừng thêm điện thoại");
				switch (Lib.takeInputChoice(1,2))
				{
					case 1 -> {
						danhSachNSX.xuat();
					}
					case 2 -> {return "stop";}
				}
			}
		}while (check);
		return mansx;
	}
	
	public void themDT(DanhSachNSX danhSachNSX, danhsachcungcap dsncc) {
		String madt = nhapMaDT();
		String mansx = nhapMaNSX(danhSachNSX);
		if(mansx.equals("stop"))
			return;
		String mancc = Lib.nhapIDNhaCungCap(dsncc);
		dt = Arrays.copyOf(dt, getsoLuong() + 1);
		System.out.println("Nhập Loại Điện Thoại: ");
		System.out.println("1. Iphone");
		System.out.println("2. Samsung");
		System.out.println("3. Oppo ");
		System.out.println("4. Nokia ");
		switch(Lib.takeInputChoice(1, 4))
		{
		case 1 -> {dt[getsoLuong()-1] = new iphone();}
		case 2 -> {dt[getsoLuong()-1] = new samsung();}
		case 3 -> {dt[getsoLuong()-1] = new oppo();}
		case 4 -> {dt[getsoLuong()-1] = new nokia();}
	
		}
		dt[getsoLuong()-1].nhap(madt, mansx, mancc);
	}
	
	public int timkiemmaDienThoai(String maDienThoai) {
		for(int i = 0; i < dt.length; i++) {
			if(dt[i].getmaDienThoai().equals(maDienThoai)) {
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
			for(int i = index; i < dt.length-1; i++) {
				dt[i] = dt[i+1];
			}
			dt[dt.length-1] = null;
			dt = Arrays.copyOf(dt, dt.length-1);
		}
	}
	
	public DienThoai[] searctenDienThoai(String tenDienThoai) {
		DienThoai[] arrdt = new DienThoai[0];
		int i = 0;
		for(DienThoai objDT : dt) {
			if(objDT.gettenDienThoai().equalsIgnoreCase(tenDienThoai)) {
				arrdt = Arrays.copyOf(arrdt, arrdt.length + 1);
				arrdt[i++] = objDT;
			}
		}
		return arrdt;
	}
	
	public DienThoai[] searchcolor(String color) {
		DienThoai[] arrdt = new DienThoai[0];
		int i = 0;
		for(DienThoai objDT : dt) {
			if(objDT.getcolor().equalsIgnoreCase(color)) {
				arrdt = Arrays.copyOf(arrdt, arrdt.length + 1);
				arrdt[i++] = objDT;
			}
		}
		return arrdt;
	}
	
	public DienThoai[] searchheDieuHanh(String heDieuHanh) {
		DienThoai[] arrdt = new DienThoai[0];
		int i = 0;
		for(DienThoai objDT : dt) {
			if(objDT.getheDieuHanh().equalsIgnoreCase(heDieuHanh)) {
				arrdt = Arrays.copyOf(arrdt, arrdt.length + 1);
				arrdt[i++] = objDT;
			}
		}
		return arrdt;
	}
	
	public void search() {
		if(getsoLuong() == 0)
		{
			Lib.printError("Danh sách đang rỗng");
			return;
		}
		System.out.println("Tìm kiếm theo:");
		System.out.println("1. Mã Điện Thoại");
		System.out.println("2. Tên Điện Thoại");
		System.out.println("3. Màu Điện Thoại ");
		System.out.println("4. Hệ Điều Hành");

		switch (Lib.takeInputChoice(1, 4)) {
			case 1 -> {
				String maDienThoai = Lib.takeStringInput("Nhập mã Điện Thoại: ");
				int i = timkiemmaDienThoai(maDienThoai);
				if (i == -1) {
					Lib.printError("Không có mã SV đó trong danh sách");
				} else {
					xuatTieuDe();
					dt[i].xuatThongTin();
				}
			}
			case 2 -> {
				String tenDienThoai = Lib.takeStringInput("Nhập tên Điện Thoại: ");
				DienThoai[] arrDT = searctenDienThoai(tenDienThoai);
				if(arrDT.length >= 1)
				{
					xuatTieuDe();
					for (DienThoai objDT : arrDT) {
						objDT.xuatThongTin();
					}
				}else
					Lib.printError("Không tìm thấy");
			}
			case 3 -> {
				String color = Lib.takeStringInput("Nhập màu Điện Thoại: ");
				DienThoai[] arrDt = searchcolor(color);
				if(arrDt.length >= 1)
				{
					xuatTieuDe();
					for (DienThoai objDT : arrDt) {
						objDT.xuatThongTin();
					}
				}else
					Lib.printError("Không tìm thấy");
			}
			case 4 -> {
				String heDieuHanh = Lib.takeStringInput("Nhập màu Hệ Điều Hành: ");
				DienThoai[] arrdT = searchheDieuHanh(heDieuHanh);
				if(arrdT.length >= 1)
				{
					xuatTieuDe();
					for (DienThoai objDT : arrdT) {
						objDT.xuatThongTin();
					}
				}else
					Lib.printError("Không tìm thấy");
			}
		}
	}

	public DienThoai[] getDt() {
		return dt;
	}

	public void setDt(DienThoai[] dt) {
		this.dt = dt;
	}

	public void menu(DanhSachNSX danhSachNSX, danhsachcungcap dsncc)
	{
		while(true) {
			xuat();
			System.out.println("1. Tìm kiếm");
			System.out.println("2. Xóa Điện Thoại");
			System.out.println("3. Thêm Điện Thoại ");
			System.out.println("4. Sửa");
			System.out.println("0. Thoát");
			boolean out = false;
			switch (Lib.takeInputChoice(0, 4)) {
				case 1 -> search();
				case 2 -> {xoaDT();}
				case 3 -> {
					int sl = Lib.takeSoLuongCanTao("Nhập số lượng cần thêm: ");
					for (int i = 0; i < sl; i++) {
						System.out.println("Nhập điện thoại thứ " + (i+1));
						themDT(danhSachNSX, dsncc);
					}
				}
				case 4 -> {
					if(getsoLuong() == 0)
					{
						Lib.printError("Danh sách đang rỗng");
						break;
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
							getDt()[index].xuatThongTin();
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
									getDt()[index].settenDienThoai(ten);
								}
								case 2 -> {getDt()[index].setmaNSX(nhapMaNSX(danhSachNSX));}
								case 3 -> {getDt()[index].setcolor(Lib.takeStringInput("Nhập màu mới: "));}
								case 4 -> {getDt()[index].setgiathanh(Lib.takeIntegerInput("Nhập giá thành mới: "));}
								case 5 -> {getDt()[index].setmaNhaCungCap(Lib.nhapIDNhaCungCap(dsncc));}
								case 0 -> {outChaneDT = true;}
							}
							if(!outChaneDT)
								Lib.clearScreen();
						}while(!outChaneDT);
					}
				}
				case 0 -> out = true;
			}
			if(out == true)
				break;
			Lib.clearScreen();
		}
	}

}
