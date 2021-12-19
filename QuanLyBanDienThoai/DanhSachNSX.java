package QuanLyBanDienThoai;

import java.io.Serializable;
import java.util.Arrays;

public class DanhSachNSX implements Serializable {
	protected NhaSX[]nsx;
	
	public DanhSachNSX() {
		nsx = new NhaSX[0];
	}
	
	public void setNhaSX(NhaSX[]nsx) {
		this.nsx=nsx;
	}
	public NhaSX[]nsx(){
		return nsx;
	}
	public void setSLuong(int SoLuong) {
		nsx = Arrays.copyOf(nsx,SoLuong);
	}
	public int getSoLuong() {return nsx.length;}

	public void xuatTieuDe()
	{
		System.out.printf("+%-20s+%-20s+%-20s+%-20s+%-20s+%n",
				Lib.repeatStr("-", 20), Lib.repeatStr("-", 20), Lib.repeatStr("-", 20),
				Lib.repeatStr("-", 20), Lib.repeatStr("-", 20));
		System.out.printf("|%-20s|%-20s|%-20s|%-20s|%-20s|%n", "Mã nhà sản xuất", "Tên nhà sản xuất", "SDT", "Địa chỉ", "Email");
		System.out.printf("+%-20s+%-20s+%-20s+%-20s+%-20s+%n",
				Lib.repeatStr("-", 20), Lib.repeatStr("-", 20), Lib.repeatStr("-", 20),
				Lib.repeatStr("-", 20), Lib.repeatStr("-", 20));
	}
	
	public void xuat() {
		if (getSoLuong() <= 0) {
			System.out.println("Không có NSX nào trong danh sách! ");
			return;
		}
		xuatTieuDe();
		for (NhaSX nhaSX : nsx) {
			nhaSX.xuatNSX();
		}
	}
	
	public void themNSX() {
		//Check mã NSX có trùng hay ko
		boolean check;
		String mansx;
		do {
			check = false;
			mansx = Lib.takeStringInput("Nhập mã nhà sản xuất: ");
			for (NhaSX nhaSX : nsx)
			{
				if(nhaSX.getmaNSX().equals(mansx))
				{
					check = true;
					Lib.printError("Mã nhà sản xuất này đã có");
				}
			}
		}while (check);
		//Sau khi ko trùng mới tạo mới
		nsx = Arrays.copyOf(nsx, getSoLuong() + 1);
		nsx[getSoLuong()-1] = new NhaSX();
		nsx[getSoLuong()-1].nhapNSX(mansx);
	}

	public int timkiemNSX(String maNSX) {
		for(int i = 0; i < nsx.length; i++) {
			if(nsx[i].getmaNSX().equals(maNSX)) {
				return i;
			}
		}
		return -1;
	}

	public void xoaNSX() {
		String maNSX = Lib.takeStringInput("Nhập mã NSX cần xóa: ");
		int index = timkiemNSX(maNSX);
		if(index == -1) {
			Lib.printError("Không có mã NSX này");
		}else {
			for(int i = index; i < nsx.length-1; i++) {
				nsx[i] = nsx[i+1];
			}
			nsx[nsx.length-1] = null;
			nsx = Arrays.copyOf(nsx, nsx.length-1);
		}
	}

	//Tìm kiếm trả về object
	public NhaSX timKiemID(String id)
	{
		for (NhaSX nhaSX : nsx) {
			if(nhaSX.getmaNSX().equals(id))
				return nhaSX;
		}
		return null;
	}

	public void menu()
	{
		while(true) {
			xuat();
			System.out.println("1. Xóa NSX");
			System.out.println("2. Thêm NSX ");
			System.out.println("3. Tìm kiếm");
			System.out.println("4. Sửa");
			System.out.println("0. Thoát");
			boolean out = false;
			switch(Lib.takeInputChoice(0, 4)) {
				case 1:
					xoaNSX();
					break;
				case 2:
					int sl = Lib.takeSoLuongCanTao("Nhập số lượng cần thêm: ");
					for (int i = 0; i < sl; i++) {
						System.out.println("Nhập mã nhà sản xuất thứ " + (i+1));
						themNSX();
					}
					break;
				case 3:
					String id = Lib.takeStringInput("Nhập mã nhà sản xuất cần tìm: ");
					NhaSX nhaSX = timKiemID(id);
					if (nhaSX == null)
						Lib.printError("Không tìm thấy");
					else {
						xuatTieuDe();
						nhaSX.xuatNSX();
					}
					break;
				case 4:
					String idsua = Lib.takeStringInput("Nhập mã nhà sản xuất cần sửa: ");
					NhaSX nhaSXsua = timKiemID(idsua);
					if (nhaSXsua == null)
						Lib.printError("Không tìm thấy");
					else {
						boolean outChange;
						do{
							outChange = false;
							xuatTieuDe();
							nhaSXsua.xuatNSX();
							System.out.println("1. Sửa Tên");
							System.out.println("2. Sửa SDT");
							System.out.println("3. Sửa địa chỉ");
							System.out.println("4. Sửa email");
							System.out.println("0. Thoát sửa");
							switch (Lib.takeInputChoice(0,4))
							{
								case 1 -> {
									String ten = Lib.takeStringInput("Nhập tên mới: ");
									nhaSXsua.settenNSX(ten);
								}
								case 2 -> {
									String sdt = Lib.takeStringInput("Nhập SDT mới: ");
									nhaSXsua.setsdtNSX(sdt);
								}
								case 3 -> {
									String dc = Lib.takeStringInput("Nhập địa chỉ mới: ");
									nhaSXsua.setdiachiNSX(dc);
								}
								case 4 -> {
									String email = Lib.takeStringInput("Nhập email mới: ");
									nhaSXsua.setemailNSX(email);
								}
								case 0 -> {
									outChange = true;
								}
							}
							if(!outChange)
								Lib.clearScreen();
						}while(!outChange);
					}
					break;
				case 0:
					out = true;
					break;
			}
			if(out)
				break;
			Lib.clearScreen();
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DanhSachNSX ds = new DanhSachNSX();
		//ds.nhapBangConsole();
		ds = (DanhSachNSX) Lib.load(ds, "dbNSX");
		if(ds == null)
			ds = new DanhSachNSX();
		while(true) {
			ds.xuat();
			System.out.println("1. Xóa NSX");
			System.out.println("2. Thêm NSX ");
			System.out.println("3. Tìm kiếm");
			System.out.println("4. Sửa");
			System.out.println("0. Thoát");
			boolean out = false;
			switch(Lib.takeInputChoice(0, 4)) {
				case 1:
					ds.xoaNSX();
					break;
				case 2:
					int sl = Lib.takeSoLuongCanTao("Nhập số lượng cần thêm: ");
					for (int i = 0; i < sl; i++) {
						System.out.println("Nhập mã nhà sản xuất thứ " + (i+1));
						ds.themNSX();
					}
					break;
				case 3:
					String id = Lib.takeStringInput("Nhập mã nhà sản xuất cần tìm: ");
					NhaSX nhaSX = ds.timKiemID(id);
					if (nhaSX == null)
						Lib.printError("Không tìm thấy");
					 else {
						 ds.xuatTieuDe();
						 nhaSX.xuatNSX();
					}
					break;
				case 4:
					String idsua = Lib.takeStringInput("Nhập mã nhà sản xuất cần sửa: ");
					NhaSX nhaSXsua = ds.timKiemID(idsua);
					if (nhaSXsua == null)
						Lib.printError("Không tìm thấy");
					else {
						boolean outChange;
						do{
							outChange = false;
							ds.xuatTieuDe();
							nhaSXsua.xuatNSX();
							System.out.println("1. Sửa Tên");
							System.out.println("2. Sửa SDT");
							System.out.println("3. Sửa địa chỉ");
							System.out.println("4. Sửa email");
							System.out.println("0. Thoát sửa");
							switch (Lib.takeInputChoice(0,4))
							{
								case 1 -> {
									String ten = Lib.takeStringInput("Nhập tên mới: ");
									nhaSXsua.settenNSX(ten);
								}
								case 2 -> {
									String sdt = Lib.takeStringInput("Nhập SDT mới: ");
									nhaSXsua.setsdtNSX(sdt);
								}
								case 3 -> {
									String dc = Lib.takeStringInput("Nhập địa chỉ mới: ");
									nhaSXsua.setdiachiNSX(dc);
								}
								case 4 -> {
									String email = Lib.takeStringInput("Nhập email mới: ");
									nhaSXsua.setemailNSX(email);
								}
								case 0 -> {
									outChange = true;
								}
							}
							if(!outChange)
								Lib.clearScreen();
						}while(!outChange);
					}
					break;
				case 0:
					out = true;
					break;
			}
			if(out)
				break;
			Lib.clearScreen();
		}

		Lib.save(ds, "dbNSX");

		System.out.println("Chương trình đã thoát");
		}

	}


