package QuanLyBanDienThoai;

import java.io.Serializable;
import java.util.Arrays;

//Danh sách này bắt chước theo vector trong c++
public class DSChiTietPhieu implements Serializable {
    private ChiTietPhieu []chiTietPhieus;
    private int index;
    private int capacity;

    public DSChiTietPhieu()
    {
        index = 0;
        capacity = 2;
        chiTietPhieus = new ChiTietPhieu[capacity];
    }

    public boolean empty()
    {
        return index == 0;
    }

    public boolean taoDSChiTietPhieu(DanhSachDT danhSachDT) {
        int n;
        do {
            n = Lib.takeSoLuongCanTao("Nhập số lượng điện thoại trong phiếu: ");
            if(n > danhSachDT.getsoLuong())
            {
                Lib.printError("Số lượng bạn muốn nhập lớn hơn số lượng trong danh sách điện thoại");
                Lib.printMessage("Số lượng trong danh sách hiện tại: " + danhSachDT.getsoLuong());
                continue;
            }
            break;
        }while (true);
            for (int i = 0; i < n; i++) {
                ChiTietPhieu obj = nhapChiTietPhieu(danhSachDT);
                if(obj == null)
                    return false;
                add(obj);
            }
        return true;
    }

    public String kiemTraMaDT(String nhapGi, DanhSachDT danhSachDT)
    {
        String idDT = Lib.takeStringInput(nhapGi);;
        while(danhSachDT.timkiemmaDienThoai(idDT) == -1 || search(idDT) != null)
        {
            if (danhSachDT.timkiemmaDienThoai(idDT) == -1)
            {
                Lib.printError("Mã điện thoại này chưa có trong danh sách điện thoại");
                System.out.println("1. Xem danh sách điện thoại rồi nhập lại");
                System.out.println("2. Dừng thêm");
                switch (Lib.takeInputChoice(1, 2))
                {
                    case 1 -> {danhSachDT.xuat();}
                    case 2 -> {return "stop";}
                }
                idDT = Lib.takeStringInput("Nhập lại ID điện thoại: ");
                continue;
            }
            if(search(idDT) != null)
            {
                Lib.printError("Mã điện thoại này đã có trong danh sách chi tiết");
                idDT = Lib.takeStringInput("Vui lòng nhập mã điện thoại khác: ");
                continue;
            }
        }
        return idDT;
    }

    public ChiTietPhieu nhapChiTietPhieu(DanhSachDT danhSachDT)
    {
        String idDT = kiemTraMaDT("Nhập ID điện thoại: ", danhSachDT);
        if(idDT.equals("stop"))
            return null;

        int sl = Lib.takeIntegerInput("Nhập số lượng: ");
        int donGia = danhSachDT.getListDT()[danhSachDT.timkiemmaDienThoai(idDT)].getgiathanh();
        return new ChiTietPhieu(idDT, sl, donGia);
    }

    public void add(ChiTietPhieu obj)
    {

        if(index + 1 > capacity)
            reSizeArray();

        chiTietPhieus[index] = obj;
        index++;
    }

    public void remove(String id)
    {
        int z = -1;
        for (int i = 0; i < index; i++)
        {
            if(chiTietPhieus[i].getIDDienThoai().equals(id))
            {
                z = i;
                break;
            }
        }

        if(z == -1)
        {
            Lib.printError("Không tìm thấy mã điện thoại: " + id);
            return;
        }

        index--;
        for (int j = z; j < index; j++) {
            chiTietPhieus[j] = chiTietPhieus[j+1];
        }
        Lib.printMessage("Xóa thành công");
    }

    public ChiTietPhieu search(String id)
    {
        for (int i = 0; i < index; i++) {
            if(chiTietPhieus[i].getIDDienThoai().equals(id))
                return chiTietPhieus[i];
        }
        return null;
    }

    public DSChiTietPhieu searchSoLuong(int soLuong)
    {
        DSChiTietPhieu searchArr = new DSChiTietPhieu();
        for (int i = 0; i < index; i++) {
            if(chiTietPhieus[i].getSoLuong() == soLuong)
                searchArr.add(chiTietPhieus[i]);
        }
        return searchArr;
    }

    public DSChiTietPhieu searchDonGia(int donGia)
    {
        DSChiTietPhieu searchArr = new DSChiTietPhieu();
        for (int i = 0; i < index; i++) {
            if(chiTietPhieus[i].getDonGia() == donGia)
                searchArr.add(chiTietPhieus[i]);
        }
        return searchArr;
    }

    public static void xuatTuaDe()
    {
        System.out.printf("+%-16s+%-16s+%-16s+\n", Lib.repeatStr("-", 16), Lib.repeatStr("-", 16), Lib.repeatStr("-", 16));
        System.out.printf("|%-16s|%-16s|%-16s|\n", "ID điện thoại", "Số lượng", "Đơn giá");
        System.out.printf("+%-16s+%-16s+%-16s+\n", Lib.repeatStr("-", 16), Lib.repeatStr("-", 16), Lib.repeatStr("-", 16));
    }

    public void xuatDS()
    {
        xuatTuaDe();
        for (int i = 0; i < index; i++) {
            chiTietPhieus[i].inChiTietPhieu();
        }
    }

    public int tinhTongTien()
    {
        int sum = 0;
        for (int i = 0; i < index; i++) {
            sum += chiTietPhieus[i].getDonGia() * chiTietPhieus[i].getSoLuong();
        }
        return sum;
    }

    private void swap(ChiTietPhieu a, ChiTietPhieu b)
    {
        ChiTietPhieu temp = a;
        a = b;
        b = temp;
    }

    public void sapXepTheoSoLuong()
    {
        for (int i = 0; i < index - 1; i++)
            for (int j = i + 1; j < index; j++)
                if (chiTietPhieus[i].getSoLuong() > chiTietPhieus[j].getSoLuong())
                    swap(chiTietPhieus[i], chiTietPhieus[j]);

    }

    public void sapXepTheoDonGia()
    {
        for (int i = 0; i < index - 1; i++)
            for (int j = i + 1; j < index; j++)
                if (chiTietPhieus[i].getDonGia() > chiTietPhieus[j].getDonGia())
                    swap(chiTietPhieus[i], chiTietPhieus[j]);
    }

    //Sửa lại capacity
    private void reSizeArray()
    {
        capacity = capacity * 2;
        chiTietPhieus = Arrays.copyOf(chiTietPhieus, capacity);
    }

    public void menuSuaChiTietPhieu(Phieu phieu, DanhSachDT danhSachDT)
    {
        String id = Lib.takeStringInput("Nhập mã điện thoại: ");
        ChiTietPhieu obj = phieu.getDsChiTietPhieu().search(id);
        while(obj == null)
        {
            Lib.printError("Mã điện thoại không tồn tại");
            id = Lib.takeStringInput("Vui lòng nhập lại mã điện thoại: ");
            obj = phieu.getDsChiTietPhieu().search(id);
        }
        while (obj != null) {
            DSChiTietPhieu.xuatTuaDe();
            obj.inChiTietPhieu();
            System.out.println("1. Sửa mã điện thoại");
            System.out.println("2. Sửa số lượng");
            System.out.println("0. Thoát");
            switch (Lib.takeInputChoice(0, 2)) {
                case 1 -> {
                    String iddt = kiemTraMaDT("Nhập mã điện thoại mới: ", danhSachDT);
                    if(iddt.equals("stop"))
                        Lib.printMessage("Dừng lại thành công");
                    else{
                        obj.setIDDienThoai(iddt);
                        Lib.printMessage("Sửa thành công");
                    }
                }
                case 2 -> {
                    int sl = Lib.takeIntegerInput("Nhập số lượng mới: ");
                    obj.setSoLuong(sl);
                    Lib.printMessage("Sửa thành công");
                }
//                case 3 -> {
//                    int dg = Lib.takeIntegerInput("Nhập đơn giá mới: ");
//                    obj.setDonGia(dg);
//                    Lib.printMessage("Sửa thành công");
//                }
                case 0 -> {
                    obj = null;
                }
            }
            phieu.setSumMoney(phieu.getDsChiTietPhieu().tinhTongTien());
        }
        if(obj != null)
            Lib.clearScreen();
    }

    public void menuTimKiemChiTietPhieu(Phieu phieu)
    {
        System.out.println("1. Tìm kiếm theo mã điện thoại");
        System.out.println("2. Tìm kiếm theo số lượng");
        System.out.println("3. Tìm kiếm theo đơn giá");
        switch (Lib.takeInputChoice(1,3))
        {
            case 1 -> {
                String iddt = Lib.takeStringInput("Nhập mã điện thoại cần tìm: ");
                ChiTietPhieu obj = phieu.getDsChiTietPhieu().search(iddt);
                if(obj == null)
                    Lib.printError("Không tìm thấy");
                else
                {
                    DSChiTietPhieu.xuatTuaDe();
                    obj.inChiTietPhieu();
                }
            }
            case 2 -> {
                int sl = Lib.takeIntegerInput("Nhập số lượng cần tìm: ");
                DSChiTietPhieu searchArr = phieu.getDsChiTietPhieu().searchSoLuong(sl);
                if(searchArr.empty())
                    Lib.printError("Không tìm thấy");
                else
                    searchArr.xuatDS();
            }
            case 3 -> {
                int dg = Lib.takeIntegerInput("Nhập đơn giá cần tìm: ");
                DSChiTietPhieu searchArr = phieu.getDsChiTietPhieu().searchDonGia(dg);
                if(searchArr.empty())
                    Lib.printError("Không tìm thấy");
                else
                    searchArr.xuatDS();
            }
        }
    }

    public void menuChiTietPhieu(Phieu phieu, DanhSachDT danhSachDT, Shop shop, danhsachcungcap dsncc) {
        int max = 5;
        if(phieu instanceof PhieuNhap)
            max = 6;
        boolean out = false;
        do {
            phieu.xemChiTietPhieu();
            System.out.println("1. Tìm kiếm trong danh sách chi tiết phiếu");
            System.out.println("2. Thêm điện thoại vào danh sách");
            System.out.println("3. Xóa điện thoại trong dach sách");
            System.out.println("4. Sửa trong danh sách chi tiết phiếu");
            System.out.println("5. Sửa ngày tháng năm");
//            System.out.println("6. Sửa mã nhân viên");
            if(phieu instanceof PhieuNhap)
                System.out.println("6. Sửa mã nhà cung cấp");
            System.out.println("0. Thoát");
            switch (Lib.takeInputChoice(0, max)) {
                case 1 -> {
                    if(phieu.getDsChiTietPhieu().empty())
                        Lib.printError("Danh sách đang rỗng không có gì để tìm");
                    else
                        menuTimKiemChiTietPhieu(phieu);
                }
                case 2 -> {
                    ChiTietPhieu obj = phieu.getDsChiTietPhieu().nhapChiTietPhieu(danhSachDT);
                    if (obj == null)
                        Lib.printError("Dừng thành công");
                    else
                    {
                        phieu.getDsChiTietPhieu().add(obj);
                        Lib.printMessage("Thêm thành công");
                    }
                }
                case 3 -> {
                    String iddt = Lib.takeStringInput("Nhập mã điện thoại cần xóa: ");
                    phieu.getDsChiTietPhieu().remove(iddt);
                }
                case 4 -> {
                    if(phieu.getDsChiTietPhieu().empty())
                        Lib.printError("Danh sách đang rỗng không có gì để sửa");
                    else
                        menuSuaChiTietPhieu(phieu, danhSachDT);
                }
                case 5 -> {
                    String date = Lib.takeDateInput("Nhâọ ngày tháng năm: ");
                    phieu.setDate(date);
                }
//                case 6 -> {
//                    String id = Lib.nhapIDNhanVien(shop, "NhanVienThuKho");
//                    phieu.setIDNhanVien(id);
//                    Lib.printMessage("Sửa thành công");
//                }
                case 6 -> {
                    String idncc = Lib.nhapIDNhaCungCap(dsncc);
                    if(idncc.equals("stop"))
                        Lib.printError("Sửa thất bại");
                    else
                    {
                        ((PhieuNhap)phieu).setIDNhaCungCap(idncc);
                        Lib.printMessage("Sửa thành công");
                    }
                }
                case 0 -> {
                    out = true;
                }
            }
            if(!out)
                Lib.clearScreen();
        } while (!out);

    }

    public ChiTietPhieu[] getChiTietPhieus() {
        return chiTietPhieus;
    }

    public void setChiTietPhieus(ChiTietPhieu[] chiTietPhieus) {
        this.chiTietPhieus = chiTietPhieus;
    }

    public int getSize(){
        return index;
    }

    public int getCapacity(){
        return capacity;
    }
}
