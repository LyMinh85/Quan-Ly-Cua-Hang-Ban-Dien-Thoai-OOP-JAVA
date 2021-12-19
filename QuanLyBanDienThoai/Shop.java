package QuanLyBanDienThoai;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Shop implements Serializable{
    ArrayList<Nguoi> listNguoi;

    public static Shop obj;

    public static Shop getInstance() {
        if (obj == null) {
            obj = new Shop();
        }
        return obj;
    }

    public Nguoi login() {
        Lib.printMessage("Đăng nhập");
        String id = Lib.takeStringInput("Nhập id: ");
        String pass = Lib.takeStringInput("Nhập password: ");
        return listNguoi.stream().filter(x -> x.id.equals(id) && x.password.equals(pass)).findAny().orElse(null);
    }

    public Shop() {
        listNguoi = new ArrayList<>();
        taoNguoiQuanLy();
    }

    private void taoNguoiQuanLy() {
        if (listNguoi.stream().filter(x -> x instanceof QuanLy).findAny().orElse(null) != null) {
            System.out.println("Cửa hàng chỉ có 1 người quản lý");
            return;
        }
        Nguoi quanLy = new QuanLy(
                "admin", "Lý Tuấn Minh",
                "idk", "8565", "08/08/8888",
                "Nam", "85659712", "admin");
        listNguoi.add(quanLy);
    }

    public Nguoi timKiemTheoID(String id)
    {
        return listNguoi.stream().filter(x -> x.id.equals(id)).findAny().orElse(null);
    }

    //dk = "KhachHang" | "NhanVienThuKho" | "NhanVienBanHang" | "NhanVien"
    public Nguoi timKiemTheoID(String id, String dk)
    {
        if(dk.equals("KhachHang"))
            return listNguoi.stream().filter(x -> x.id.equals(id) && x instanceof KhachHang).findFirst().orElse(null);
        else if(dk.equals("NhanVienThuKho"))
            return listNguoi.stream().filter(x -> x.id.equals(id) && x instanceof NhanVienThuKho).findFirst().orElse(null);
        else if(dk.equals("NhanVienBanHang"))
            return listNguoi.stream().filter(x -> x.id.equals(id) && x instanceof NhanVienBanHang).findFirst().orElse(null);
        else
            return listNguoi.stream().filter(x -> x.id.equals(id) && (x instanceof NhanVienBanHang || x instanceof NhanVienThuKho)).findFirst().orElse(null);
    }

    private String chonGioiTinh()
    {
        String gt = "";
        System.out.println("***Lựa chọn giới tính***");
        System.out.println("1. Nam");
        System.out.println("2. Nữ");
        System.out.println("3. Khác");
        switch (Lib.takeInputChoice(1, 3))
        {
            case 1 -> {gt = "Nam";}
            case 2 -> {gt = "Nữ";}
            case 3 -> {gt = "Khác";}
        }
        return gt;
    }

    //Tạo người với lựa chọn. Choice = "KhachHang" | "NhanVien"
    public void taoNguoi(String choice) {
        String id;
        while (true) {
            id = Lib.takeStringInput("Nhập id: ");
            if (timKiemTheoID(id) != null) {
                Lib.printError("ID này đã có vui lòng chọn ID khác");
            } else {
                break;
            }
        }

        String hoTen = Lib.takeStringInput("Nhập họ tên: ");
        String diaChi = Lib.takeStringInput("Nhập địa chỉ: ");
        String sdt = Lib.takePhoneNumberInput("Nhập số điện thoại: ");
        String ns = Lib.takeDateInput("Nhập ngày sinh: ");
        String gt = chonGioiTinh();
        String cmnd = Lib.takeStringInput("Nhập CMND: ");
        String pass = Lib.takeStringInput("Nhập password: ");

        if (choice.equals("KhachHang")) {
            Nguoi kh = new KhachHang(id, hoTen, diaChi, sdt, ns, gt, cmnd, pass);
            listNguoi.add(kh);
        } else if (choice.equals("NhanVien")) {
            int mucLuong = Lib.takeIntegerInput("Nhập mức lương: ");
            System.out.println("Chọn chức vụ của nhân viên");
            System.out.println("1. Nhân viên thủ kho");
            System.out.println("2. Nhân viên bán hàng");
            switch (Lib.takeInputChoice(1, 2))
            {
                case 1 -> {
                    NhanVienThuKho nhanVienThuKho = new NhanVienThuKho(id, hoTen, diaChi, sdt, ns, gt, cmnd, pass, mucLuong);
                    listNguoi.add(nhanVienThuKho);
                }
                case 2 -> {
                    NhanVienBanHang nhanVienBanHang = new NhanVienBanHang(id, hoTen, diaChi, sdt, ns, gt, cmnd, pass, mucLuong);
                    listNguoi.add(nhanVienBanHang);
                }
            }

        }
    }

    public void inDanhsachNhanVien() {
        int count = 0;

        xuatTieuDeNhanVien();
        for (Nguoi nguoi : listNguoi) {
            if (nguoi instanceof NhanVienBanHang || nguoi instanceof NhanVienThuKho)
            {
                nguoi.xuatThongTin();
                count++;
            }
        }
        if(count == 0)
            Lib.printError("Không có nhân viên nào");
    }

    public void inDanhsachNhanVien(ArrayList<Nguoi> list) {
        int count = 0;
        xuatTieuDeNhanVien();
        for (Nguoi nguoi : list) {
            if (nguoi instanceof NhanVienBanHang || nguoi instanceof NhanVienThuKho)
            {
                nguoi.xuatThongTin();
                count++;
            }
        }
        if(count == 0)
            Lib.printError("Không có nhân viên nào");
    }

    public void inDanhSachKhangHang() {
        int count = 0;

        xuatTieuDeKhachHang();
        for (Nguoi nguoi : listNguoi) {
            if (nguoi instanceof KhachHang)
            {
                nguoi.xuatThongTin();
                count++;
            }
        }
        if(count == 0)
            Lib.printError("Không có khách hàng nào");
    }

    public void xuatTieuDeKhachHang()
    {
        System.out.printf("+%-16s+%-16s+%-16s+%-10s+%-10s+%-10s+%-9s+%-16s+%n",
                Lib.repeatStr("-", 16), Lib.repeatStr("-", 16), Lib.repeatStr("-", 16),
                Lib.repeatStr("-", 10), Lib.repeatStr("-", 10), Lib.repeatStr("-", 10),
                Lib.repeatStr("-", 9), Lib.repeatStr("-", 16));

        System.out.printf("|%-16s|%-16s|%-16s|%-10s|%-10s|%-10s|%-9s|%-16s|%n",
                "ID khách hàng", "Họ tên", "Địa chỉ", "SDT", "Ngày sinh", "Giới tính", "CMND", "Password");

        System.out.printf("+%-16s+%-16s+%-16s+%-10s+%-10s+%-10s+%-9s+%-16s+%n",
                Lib.repeatStr("-", 16), Lib.repeatStr("-", 16), Lib.repeatStr("-", 16),
                Lib.repeatStr("-", 10), Lib.repeatStr("-", 10), Lib.repeatStr("-", 10),
                Lib.repeatStr("-", 9), Lib.repeatStr("-", 16));
    }

    public void xuatTieuDeNhanVien()
    {
        System.out.printf("+%-16s+%-16s+%-16s+%-10s+%-10s+%-10s+%-9s+%-16s+%-10s+%-16s+%n",
                Lib.repeatStr("-", 16), Lib.repeatStr("-", 16), Lib.repeatStr("-", 16),
                Lib.repeatStr("-", 10), Lib.repeatStr("-", 10), Lib.repeatStr("-", 10),
                Lib.repeatStr("-", 9), Lib.repeatStr("-", 16), Lib.repeatStr("-", 10),
                Lib.repeatStr("-", 16));

        System.out.printf("|%-16s|%-16s|%-16s|%-10s|%-10s|%-10s|%-9s|%-16s|%-10s|%-16s|%n",
                "ID nhân viên", "Họ tên", "Địa chỉ", "SDT", "Ngày sinh",
                "Giới tính", "CMND", "Password", "Mức lương", "Chức vụ");

        System.out.printf("+%-16s+%-16s+%-16s+%-10s+%-10s+%-10s+%-9s+%-16s+%-10s+%-16s+%n",
                Lib.repeatStr("-", 16), Lib.repeatStr("-", 16), Lib.repeatStr("-", 16),
                Lib.repeatStr("-", 10), Lib.repeatStr("-", 10), Lib.repeatStr("-", 10),
                Lib.repeatStr("-", 9), Lib.repeatStr("-", 16), Lib.repeatStr("-", 10),
                Lib.repeatStr("-", 16));
    }

    private void menuSua(Nguoi nguoi)
    {
        int max = 7;
        if(nguoi instanceof NhanVienBanHang || nguoi instanceof NhanVienThuKho)
            max = 8;
        boolean outChange;
        do {
            outChange = false;
            if(nguoi instanceof KhachHang)
                xuatTieuDeKhachHang();
            else
                xuatTieuDeNhanVien();
            nguoi.xuatThongTin();
            System.out.println("1. Sửa tên");
            System.out.println("2. Sửa địa chỉ");
            System.out.println("3. Sửa SDT");
            System.out.println("4. Sửa ngày sinh");
            System.out.println("5. Sửa giới tính");
            System.out.println("6. Sửa CMND");
            System.out.println("7. Sửa password");
            if(nguoi instanceof NhanVienBanHang || nguoi instanceof NhanVienThuKho)
                System.out.println("8. Sửa mức lương");
            System.out.println("0. Thoát sửa");

            switch (Lib.takeInputChoice(0, max))
            {
                case 1 -> {nguoi.setHoTen(Lib.takeStringInput("Nhập họ tên mới: "));}
                case 2 -> {nguoi.setDiaChi(Lib.takeStringInput("Nhập địa chỉ mới: "));}
                case 3 -> {nguoi.setSDT(Lib.takePhoneNumberInput("Nhập SDT mới: "));}
                case 4 -> {nguoi.setNgaySinh(Lib.takeDateInput("Nhập ngày sinh mới: "));}
                case 5 -> {nguoi.setGioiTinh(chonGioiTinh());}
                case 6 -> {nguoi.setCMND(Lib.takeStringInput("Nhập CMND mới: "));}
                case 7 -> {nguoi.setPassword(Lib.takeStringInput("Nhập password mới: "));}
                case 8 -> {((NhanVien)nguoi).setMucLuong(Lib.takeIntegerInput("Nhập mức lương mới: "));}
                case 0 -> {outChange = true;}
            }
            if(!outChange)
                Lib.clearScreen();
        }while (!outChange);
    }

    public void menuDanhSachKhachHang()
    {
        boolean out;
        do
        {
            out = false;
            inDanhSachKhangHang();
            System.out.println("1. Thêm khách hàng");
            System.out.println("2. Tìm kiếm");
            System.out.println("3. Xóa");
            System.out.println("4. Sửa");
            System.out.println("0. Thoát");
            switch (Lib.takeInputChoice(0, 4))
            {
                case 1 -> {taoNguoi("KhachHang");}
                case 2 -> {
                    String id = Lib.takeStringInput("Nhập ID khách hàng cần tìm: ");
                    Nguoi obj = timKiemTheoID(id, "KhachHang");
                    if(obj == null)
                        Lib.printError("Không tìm thấy");
                    else{
                        xuatTieuDeKhachHang();
                        obj.xuatThongTin();
                    }
                }
                case 3 -> {
                    String id = Lib.takeStringInput("Nhập ID khách hàng cần xóa: ");
                    Nguoi obj = timKiemTheoID(id, "KhachHang");
                    if(obj == null)
                        Lib.printError("Không tìm thấy");
                    else{
                        listNguoi.remove(obj);
                        Lib.printMessage("Xóa thành công");
                    }
                }
                case 4 -> {
                    String id = Lib.takeStringInput("Nhập ID khách hàng cần sửa: ");
                    Nguoi obj = timKiemTheoID(id, "KhachHang");
                    if(obj == null)
                        Lib.printError("Không tìm thấy");
                    else{
                        menuSua(obj);
                    }
                }
                case 0 -> {out = true;}
            }
            if(!out)
                Lib.clearScreen();
        }while(!out);
    }

    //dk = "NhanVienBanHang" | "NhanVienThuKho"
    private ArrayList<Nguoi> locDanhSachNhanVien(String dk)
    {
        ArrayList<Nguoi> result;
        if (dk.equals("NhanVienBanHang"))
            result = listNguoi.stream()
                    .filter(x -> x instanceof NhanVienBanHang)
                    .collect(Collectors.toCollection(ArrayList::new));
        else
            result = listNguoi.stream()
                    .filter(x -> x instanceof NhanVienThuKho)
                    .collect(Collectors.toCollection(ArrayList::new));
        if(result.toString().equals("[]"))
            return null;
        return result;
    }

    public void menuDanhSachNhanVien()
    {
        boolean out;
        do
        {
            out = false;
            inDanhsachNhanVien();
            System.out.println("1. Thêm nhân viên");
            System.out.println("2. Tìm kiếm");
            System.out.println("3. Xóa");
            System.out.println("4. Sửa");
            System.out.println("5. Lọc theo chức vụ");
            System.out.println("0. Thoát");
            switch (Lib.takeInputChoice(0, 5))
            {
                case 1 -> {taoNguoi("NhanVien");}
                case 2 -> {
                    String id = Lib.takeStringInput("Nhập ID nhân viên cần tìm: ");
                    Nguoi obj = timKiemTheoID(id, "NhanVien");
                    if(obj == null)
                        Lib.printError("Không tìm thấy");
                    else{
                        xuatTieuDeNhanVien();
                        obj.xuatThongTin();
                    }
                }
                case 3 -> {
                    String id = Lib.takeStringInput("Nhập ID nhân viên cần xóa: ");
                    Nguoi obj = timKiemTheoID(id, "NhanVien");
                    if(obj == null)
                        Lib.printError("Không tìm thấy");
                    else{
                        listNguoi.remove(obj);
                        Lib.printMessage("Xóa thành công");
                    }
                }
                case 4 -> {
                    String id = Lib.takeStringInput("Nhập ID nhân viên cần sửa: ");
                    Nguoi obj = timKiemTheoID(id, "NhanVien");
                    if(obj == null)
                        Lib.printError("Không tìm thấy");
                    else{
                        menuSua(obj);
                    }
                }
                case 5 -> {
                    System.out.println("1. Lọc theo nhân viên thủ kho");
                    System.out.println("2. Lọc theo nhân viên bán hàng");
                    switch (Lib.takeInputChoice(1, 2))
                    {
                        case 1 -> {
                            ArrayList<Nguoi> list = locDanhSachNhanVien("NhanVienThuKho");
                            if(list != null)
                                inDanhsachNhanVien(list);
                            else
                                Lib.printError("Không lọc được");
                        }
                        case 2 -> {
                            ArrayList<Nguoi> list = locDanhSachNhanVien("NhanVienBanHang");
                            if(list != null)
                                inDanhsachNhanVien(list);
                            else
                                Lib.printError("Không lọc được");
                        }
                    }
                }
                case 0 -> {out = true;}
            }
            if(!out)
                Lib.clearScreen();
        }while(!out);
    }

}
