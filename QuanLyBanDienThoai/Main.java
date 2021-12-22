package QuanLyBanDienThoai;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        boolean exit = false;

        //Load nguoi----------
        Shop shop = new Shop();
        shop = (Shop) Lib.load(shop, "dbNguoi");
        if(shop == null)
            shop = new Shop();

        //Load dt-------------
        DanhSachDT danhSachDT = new DanhSachDT();
        danhSachDT = (DanhSachDT) Lib.load(danhSachDT, "dbDienThoai");
        if(danhSachDT == null)
            danhSachDT = new DanhSachDT();

        //Load nsx-------------
        DanhSachNSX danhSachNSX = new DanhSachNSX();
        danhSachNSX = (DanhSachNSX) Lib.load(danhSachNSX, "dbNSX");
        if(danhSachNSX == null)
            danhSachNSX = new DanhSachNSX();

        //Load phieu nhap----------
        DSPhieuNhap dsPhieuNhap = new DSPhieuNhap();
        dsPhieuNhap = (DSPhieuNhap) Lib.load(dsPhieuNhap, "dbPhieuNhap");
        if(dsPhieuNhap == null)
            dsPhieuNhap = new DSPhieuNhap();

        //Load phieu xuat----------
        DSPhieuXuat dsPhieuXuat = new DSPhieuXuat();
        dsPhieuXuat = (DSPhieuXuat) Lib.load(dsPhieuXuat, "dbPhieuXuat");
        if(dsPhieuXuat == null)
            dsPhieuXuat = new DSPhieuXuat();
        
        //Load hoa don------
        danhsachhoadon DSHD = new danhsachhoadon();
        DSHD = (danhsachhoadon) Lib.load(DSHD, "dbHoaDon");
        if(DSHD == null)
            DSHD = new danhsachhoadon();

        //Load nha cung cap----
        danhsachcungcap dsncc = new danhsachcungcap();
        dsncc = (danhsachcungcap) Lib.load(dsncc, "dbNhaCungCap");
        if(dsncc == null)
            dsncc = new danhsachcungcap();

        System.out.printf("+%-30s%-5s%-25s+\n", Lib.repeatStr("-", 30), "Cửa hàng bán điện thoại", Lib.repeatStr("-", 25));
        while(true)
        {
            System.out.printf("+%-30s%-5s%-25s+\n", Lib.repeatStr("-", 30), "Đăng nhập", Lib.repeatStr("-", 25));
            System.out.println("1. Đăng nhập");
            System.out.println("2. Đăng ký");
            System.out.println("0. Thoát");
            switch (Lib.takeInputChoice(0,2))
            {
                case 1 -> {
                    Nguoi nguoi = shop.login();
                    boolean logout = false;
                    if(nguoi == null)
                    {
                        //Không có tài khoản đó
                        Lib.printError("Tài khoản không tồn tại");
                    }
                    else if(nguoi instanceof QuanLy)
                    {
                        //Người quản lý (admin)
                        while(true)
                        {
                            System.out.printf("+%-30s%-5s%-25s+\n", Lib.repeatStr("-", 30), "Admin", Lib.repeatStr("-", 25));
                            System.out.printf("%-20s%-15s%-25s\n", Lib.repeatStr(" ", 20), Lib.toBlueText("Xin chào " + nguoi.getHoTen()), Lib.repeatStr(" ", 25));
                            System.out.println("1. Xem danh sách nhân viên");
                            System.out.println("2. Xem danh sách khách hàng");
                            System.out.println("3. Xem danh sách điện thoại");
                            System.out.println("4. Xem danh sách nhà sản xuất");
                            System.out.println("5. Xem danh sách nhà cung cấp");
                            System.out.println("0. Đăng xuất");
                            switch (Lib.takeInputChoice(0,5))
                            {
                                case 1 -> {shop.menuDanhSachNhanVien(); Lib.save(shop, "dbNguoi");}
                                case 2 -> {shop.menuDanhSachKhachHang(); Lib.save(shop, "dbNguoi");}
                                case 3 -> {danhSachDT.menu(danhSachNSX, dsncc); Lib.save(danhSachDT, "dbDienThoai");}
                                case 4 -> {danhSachNSX.menu(); Lib.save(danhSachNSX, "dbNSX");}
                                case 5 -> {dsncc.menuNhacc(); Lib.save(dsncc, "dbNhaCungCap");}
                                case 0 -> {logout = true;}
                            }
                            if(logout)
                                break;

                            Lib.clearScreen();
                        }
                    }
                    else if(nguoi instanceof NhanVien)
                    {

                        //Nhân viên thủ kho
                        if(nguoi instanceof NhanVienThuKho)
                        {
                            while(true){
                                System.out.printf("+%-30s%-5s%-25s+\n", Lib.repeatStr("-", 30), "Nhân viên thủ kho", Lib.repeatStr("-", 25));
                                System.out.printf("%-20s%-15s%-25s\n", Lib.repeatStr(" ", 20), Lib.toBlueText("Xin chào " + nguoi.getHoTen()), Lib.repeatStr(" ", 25));
                                System.out.println("1. Xem các phiếu xuất");
                                System.out.println("2. Xem các phiếu nhập");
                                System.out.println("0. Đăng xuất");
                                switch (Lib.takeInputChoice(0, 2)){
                                    case 1 -> {dsPhieuXuat.menu(danhSachDT, nguoi, shop, dsncc);Lib.save(dsPhieuXuat, "dbPhieuXuat");}
                                    case 2 ->{dsPhieuNhap.menu(danhSachDT, nguoi, shop, dsncc);Lib.save(dsPhieuNhap, "dbPhieuNhap");}
                                    case 0 -> {logout = true;}
                                }
                                if(logout)
                                    break;
                                Lib.clearScreen();
                            }
                        }
                        else //Nhân viên bán hàng
                        {
                            while(true){
                                System.out.printf("+%-30s%-5s%-25s+\n", Lib.repeatStr("-", 30), "Nhân viên bán hàng", Lib.repeatStr("-", 25));
                                System.out.printf("%-20s%-15s%-25s\n", Lib.repeatStr(" ", 20), Lib.toBlueText("Xin chào " + nguoi.getHoTen()), Lib.repeatStr(" ", 25));
                                System.out.println("1. Xem danh sách hóa đơn");
                                System.out.println("2. Xem danh sách khách hàng");
                                System.out.println("0. Đăng xuất");
                                switch (Lib.takeInputChoice(0, 2)){
                                    case 1 -> {DSHD.menuHD(danhSachDT, nguoi, shop); Lib.save(DSHD, "dbHoaDon");}
                                    case 2 -> {shop.menuDanhSachKhachHang(); Lib.save(shop, "dbNguoi");}
                                    case 0 -> {logout = true;}
                                }
                                if(logout)
                                    break;
                                Lib.clearScreen();
                            }
                        }


                    }
                    else if(nguoi instanceof KhachHang)
                    {
                        //Khách hàng
                        do
                        {
                            System.out.printf("+%-30s%-5s%-25s+\n", Lib.repeatStr("-", 30), "Khách hàng", Lib.repeatStr("-", 25));
                            System.out.printf("%-20s%-15s%-25s\n", Lib.repeatStr(" ", 20), Lib.toBlueText("Xin chào " + nguoi.getHoTen()), Lib.repeatStr(" ", 25));
                            System.out.println("1. Xem danh sách hóa đơn");
                            System.out.println("0. Thoát");
                            switch (Lib.takeInputChoice(0, 1))
                            {
                                case 1 -> {DSHD.menuHD(danhSachDT, nguoi, shop); Lib.save(DSHD, "dbHoaDon");}
                                case 0 -> {logout = true;}
                            }
                            if(!logout)
                                Lib.clearScreen();
                        }while (!logout);
                    }

                    Lib.clearScreen();
                }
                case 2 -> {
                    System.out.printf("+%-30s%-5s%-25s+\n", Lib.repeatStr("-", 30), "Đăng ký", Lib.repeatStr("-", 25));
                    shop.taoNguoi("KhachHang");
                    Lib.printMessage("Bạn đã đăng ký xong");
                    Lib.save(shop, "dbNguoi");
                }
                case 0 -> {exit = true;}
            }

            if(exit)
                break;
        }

        Lib.printMessage("Chương trình đã kết thúc");
    }


}
