package QuanLyBanDienThoai;

import java.io.Serializable;
import java.util.Arrays;

public class DanhSachNSX implements Serializable {
	protected NhaSX[] nsx;
	private int count = 0;

	public DanhSachNSX() {
		nsx = new NhaSX[4];
		nsx[0] = new NhaSX(getMaNhaSanXuat(), "Apple", "58495", "ABC", "Apple@email");
		nsx[1] = new NhaSX(getMaNhaSanXuat(), "SamSung", "234", "EFG", "SamSung@email");
		nsx[2] = new NhaSX(getMaNhaSanXuat(), "Oppo", "32423", "TIYM", "Oppo@email");
		nsx[3] = new NhaSX(getMaNhaSanXuat(), "Nokia", "7656", "TEMK", "Nokia@email");
	}

	private String getMaNhaSanXuat() {
		count++;
		Integer a = count;
		String str = a.toString();
		while (str.length() != 3)
			str = "0" + str;
		str = "NSX" + str;
		return str;
	}

	public void xuatTieuDe() {
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
			Lib.printError("Không có NSX nào trong danh sách! ");
			return;
		}
		xuatTieuDe();
		for (NhaSX nhaSX : nsx) {
			nhaSX.xuatNSX();
		}
	}

	public void themNSX() {
		nsx = Arrays.copyOf(nsx, getSoLuong() + 1);
		nsx[getSoLuong() - 1] = new NhaSX();
		nsx[getSoLuong() - 1].nhapNSX(getMaNhaSanXuat());
	}

	public int timkiemNSX(String maNSX) {
		for (int i = 0; i < nsx.length; i++) {
			if (nsx[i].getmaNSX().equals(maNSX)) {
				return i;
			}
		}
		return -1;
	}

	public void xoaNSX() {
		String maNSX = Lib.takeStringInput("Nhập mã NSX cần xóa: ");
		int index = timkiemNSX(maNSX);
		if (index == -1) {
			Lib.printError("Không có mã NSX này");
		} else {
			for (int i = index; i < nsx.length - 1; i++) {
				nsx[i] = nsx[i + 1];
			}
			nsx[nsx.length - 1] = null;
			nsx = Arrays.copyOf(nsx, nsx.length - 1);
		}
	}

	//Tìm kiếm trả về object
	public NhaSX timKiemTheoID(String id) {
		for (NhaSX nhaSX : nsx) {
			if (nhaSX.getmaNSX().equals(id))
				return nhaSX;
		}
		return null;
	}

	public void menuSuaNSX() {
		String idsua = Lib.takeStringInput("Nhập mã nhà sản xuất cần sửa: ");
		NhaSX nhaSXsua = timKiemTheoID(idsua);
		if (nhaSXsua == null)
			Lib.printError("Không tìm thấy");
		else {
			boolean outChange;
			do {
				outChange = false;
				xuatTieuDe();
				nhaSXsua.xuatNSX();
				System.out.println("1. Sửa Tên");
				System.out.println("2. Sửa SDT");
				System.out.println("3. Sửa địa chỉ");
				System.out.println("4. Sửa email");
				System.out.println("0. Thoát sửa");
				switch (Lib.takeInputChoice(0, 4)) {
					case 1 -> nhaSXsua.settenNSX(Lib.takeStringInput("Nhập tên mới: "));
					case 2 -> nhaSXsua.setsdtNSX(Lib.takeStringInput("Nhập SDT mới: "));
					case 3 -> nhaSXsua.setdiachiNSX(Lib.takeStringInput("Nhập địa chỉ mới: "));
					case 4 -> nhaSXsua.setemailNSX(Lib.takeStringInput("Nhập email mới: "));
					case 0 -> outChange = true;

				}
				if (!outChange)
					Lib.clearScreen();
			} while (!outChange);
		}
	}

	public void menu() {
		while (true) {
			xuat();
			System.out.println("1. Xóa NSX");
			System.out.println("2. Thêm NSX ");
			System.out.println("3. Tìm kiếm");
			System.out.println("4. Sửa");
			System.out.println("0. Thoát");
			boolean out = false;
			switch (Lib.takeInputChoice(0, 4)) {
				case 1 -> xoaNSX();
				case 2 -> themNSX();
				case 3 -> {
					NhaSX nhaSX = timKiemTheoID(Lib.takeStringInput("Nhập mã nhà sản xuất cần tìm: "));
					if (nhaSX == null)
						Lib.printError("Không tìm thấy");
					else {
						xuatTieuDe();
						nhaSX.xuatNSX();
					}
				}
				case 4 -> menuSuaNSX();
				case 0 -> out = true;
			}
			if (out)
				break;
			Lib.clearScreen();
		}

	}





	//Getter and Setter
	public void setNhaSX(NhaSX[] nsx) {
		this.nsx = nsx;
	}
	public NhaSX[] nsx() {
		return nsx;
	}
	public void setSLuong(int SoLuong) {
		nsx = Arrays.copyOf(nsx, SoLuong);
	}
	public int getSoLuong() {
		return nsx.length;
	}

}


