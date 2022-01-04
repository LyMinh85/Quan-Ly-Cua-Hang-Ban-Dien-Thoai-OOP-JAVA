package QuanLyBanDienThoai;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Locale;

public class DanhSachNSX implements Serializable, DanhSach {
	protected NhaSX[] nsxArray;
	private int count = 0;

	public DanhSachNSX() {
		nsxArray = new NhaSX[4];
		nsxArray[0] = new NhaSX(getID(), "Apple", "58495", "ABC", "Apple@email");
		nsxArray[1] = new NhaSX(getID(), "SamSung", "234", "EFG", "SamSung@email");
		nsxArray[2] = new NhaSX(getID(), "Oppo", "32423", "TIYM", "Oppo@email");
		nsxArray[3] = new NhaSX(getID(), "Nokia", "7656", "TEMK", "Nokia@email");
	}

	public String getID() {
		count++;
		Integer a = count;
		String str = a.toString();
		while (str.length() != 3)
			str = "0" + str;
		str = "NSX" + str;
		return str;
	}

	public void xuatTieuDe() {
		System.out.printf("┌%-20s┬%-20s┬%-20s┬%-20s┬%-20s┐%n",
				Lib.repeatStr("─", 20), Lib.repeatStr("─", 20), Lib.repeatStr("─", 20),
				Lib.repeatStr("─", 20), Lib.repeatStr("─", 20));
		System.out.printf("│%-20s│%-20s│%-20s│%-20s│%-20s│%n", "Mã nhà sản xuất", "Tên nhà sản xuất", "SDT", "Địa chỉ", "Email");
		System.out.printf("├%-20s┼%-20s┼%-20s┼%-20s┼%-20s┤%n",
				Lib.repeatStr("─", 20), Lib.repeatStr("─", 20), Lib.repeatStr("─", 20),
				Lib.repeatStr("─", 20), Lib.repeatStr("─", 20));
	}

	public void xuatDS() {
		if (getSoLuong() <= 0) {
			Lib.printError("Không có NSX nào trong danh sách! ");
			return;
		}
		xuatTieuDe();
		for (NhaSX nhaSX : nsxArray) {
			nhaSX.xuatNSX();
		}
	}

	public void themNSX() {
		nsxArray = Arrays.copyOf(nsxArray, getSoLuong() + 1);
		nsxArray[getSoLuong() - 1] = new NhaSX();
		nsxArray[getSoLuong() - 1].nhapNSX(getID());
	}

	public int timkiemNSX(String maNSX) {
		for (int i = 0; i < nsxArray.length; i++) {
			if (nsxArray[i].getmaNSX().equals(maNSX)) {
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
			for (int i = index; i < nsxArray.length - 1; i++) {
				nsxArray[i] = nsxArray[i + 1];
			}
			nsxArray[nsxArray.length - 1] = null;
			nsxArray = Arrays.copyOf(nsxArray, nsxArray.length - 1);
		}
	}

	//Tìm kiếm trả về object
	public NhaSX timKiemTheoID(String id) {
		for (NhaSX nhaSX : nsxArray) {
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
			xuatDS();
			System.out.println("1. Xóa NSX");
			System.out.println("2. Thêm NSX ");
			System.out.println("3. Tìm kiếm");
			System.out.println("4. Sửa");
			System.out.println("0. Thoát");
			boolean out = false;
			switch (Lib.takeInputChoice(0, 4)) {
				case 1 -> xoaNSX();
				case 2 -> themNSX();
				case 3 -> timKiemTrongBang();
				case 4 -> menuSuaNSX();
				case 0 -> out = true;
			}
			if (out)
				break;
			Lib.clearScreen();
		}

	}

	public void timKiemTrongBang(){
		String tuKhoa = Lib.takeStringInput("Nhập từ khóa: ");
		NhaSX []filter = new NhaSX[getSoLuong()];
		int length = 0;
		for (int i = 0; i < getSoLuong(); i++) {
			if(
					nsxArray[i].gettenNSX().toLowerCase(Locale.ROOT).contains(tuKhoa.toLowerCase(Locale.ROOT)) ||
					nsxArray[i].getdiachiNSX().toLowerCase(Locale.ROOT).contains(tuKhoa.toLowerCase(Locale.ROOT)) ||
					nsxArray[i].getsdtNSX().toLowerCase(Locale.ROOT).contains(tuKhoa.toLowerCase(Locale.ROOT)) ||
					nsxArray[i].getemailNSX().toLowerCase(Locale.ROOT).contains(tuKhoa.toLowerCase(Locale.ROOT))
			)
			{
				filter[length++] = nsxArray[i];
			}
		}

		System.out.println(Lib.toBlueText("Tìm kiếm trong bảng theo từ khóa: ")  + Lib.toGreenText(tuKhoa));
		if(length == 0)
		{
			Lib.printError("Không tìm thấy");
			return;
		}
		xuatTieuDe();
		for (int i = 0; i < length; i++) {
			filter[i].xuatNSX();
		}
	}




	//Getter and Setter
	public void setSLuong(int SoLuong) {
		nsxArray = Arrays.copyOf(nsxArray, SoLuong);
	}
	public int getSoLuong() {
		return nsxArray.length;
	}
	public NhaSX[] getNsxArray() {
		return nsxArray;
	}

	public void setNsxArray(NhaSX[] nsxArray) {
		this.nsxArray = nsxArray;
	}
}


