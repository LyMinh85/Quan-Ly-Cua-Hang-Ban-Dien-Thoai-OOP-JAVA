package QuanLyBanDienThoai;

public class Main {

    public static void main(String[] args) {
        boolean exit = false;

        //Load nguoi----------
        Shop shop = null;
        shop = (Shop) Lib.load(shop, "dbNguoi");
        if(shop == null)
        {
            shop = new Shop();
            Lib.save(shop, "dbNguoi");
        }

        //Load nha cung cap----
        DanhSachNhaCungCap dsncc = null;
        dsncc = (DanhSachNhaCungCap) Lib.load(dsncc, "dbNhaCungCap");
        if(dsncc == null)
        {
            dsncc = new DanhSachNhaCungCap();
            Lib.save(dsncc, "dbNhaCungCap");
        }

        //Load nsx-------------
        DanhSachNSX danhSachNSX = null;
        danhSachNSX = (DanhSachNSX) Lib.load(danhSachNSX, "dbNSX");
        if(danhSachNSX == null)
        {
            danhSachNSX = new DanhSachNSX();
            Lib.save(danhSachNSX, "dbNSX");
        }


        //Load dt-------------
        DanhSachDT danhSachDT = null;
        danhSachDT = (DanhSachDT) Lib.load(danhSachDT, "dbDienThoai");
        if(danhSachDT == null)
        {
            danhSachDT = new DanhSachDT();
            Lib.save(danhSachDT, "dbDienThoai");
        }

        //Load hoa don------
        DanhSachHoaDon danhSachHoaDon = null;
        danhSachHoaDon = (DanhSachHoaDon) Lib.load(danhSachHoaDon, "dbHoaDon");
        if(danhSachHoaDon == null)
        {
            danhSachHoaDon = new DanhSachHoaDon(danhSachDT);
            Lib.save(danhSachHoaDon, "dbHoaDon");
        }

        //Load phieu nhap----------
        DSPhieuNhap dsPhieuNhap = null;
        dsPhieuNhap = (DSPhieuNhap) Lib.load(dsPhieuNhap, "dbPhieuNhap");
        if(dsPhieuNhap == null)
        {
            dsPhieuNhap = new DSPhieuNhap(danhSachDT);
            Lib.save(dsPhieuNhap, "dbPhieuNhap");
        }

        //Load phieu xuat----------
        DSPhieuXuat dsPhieuXuat = null;
        dsPhieuXuat = (DSPhieuXuat) Lib.load(dsPhieuXuat, "dbPhieuXuat");
        if(dsPhieuXuat == null)
        {
            dsPhieuXuat = new DSPhieuXuat(shop.timKiemTheoID("vu"), danhSachHoaDon);
            Lib.save(dsPhieuXuat, "dbPhieuXuat");
            Lib.save(danhSachHoaDon, "dbHoaDon");
        }

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
                    //Không có tài khoản đó
                    if(nguoi == null)
                        Lib.printError("Tài khoản không tồn tại");
                    //Người quản lý (admin)
                    else if(nguoi instanceof QuanLy)
                    {
                        while(true)
                        {
                            System.out.printf("+%-30s%-5s%-25s+\n", Lib.repeatStr("-", 30), "Admin", Lib.repeatStr("-", 25));
                            System.out.printf("%-20s%-15s%-25s\n", Lib.repeatStr(" ", 20), Lib.toBlueText("Xin chào " + nguoi.getHoTen()), Lib.repeatStr(" ", 25));
                            System.out.println("1. Xem danh sách nhân viên");
                            System.out.println("2. Xem danh sách nhà sản xuất");
                            System.out.println("3. Xem danh sách nhà cung cấp");
                            System.out.println("0. Đăng xuất");
                            switch (Lib.takeInputChoice(0,3))
                            {
                                case 1 -> {
                                    shop.menuDanhSachNhanVien();
                                    Lib.save(shop, "dbNguoi");
                                }
                                case 2 -> {
                                    danhSachNSX.menu();
                                    Lib.save(danhSachNSX, "dbNSX");
                                }
                                case 3 -> {
                                    dsncc.menuNhacc();
                                    Lib.save(dsncc, "dbNhaCungCap");
                                }
                                case 0 -> logout = true;
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
                                System.out.println("3. Xem danh sách điện thoại");
                                System.out.println("0. Đăng xuất");
                                switch (Lib.takeInputChoice(0, 3)){
                                    case 1 -> {
                                        dsPhieuXuat.menu(nguoi, danhSachHoaDon);
                                        Lib.save(dsPhieuXuat, "dbPhieuXuat");
                                        Lib.save(danhSachHoaDon, "dbHoaDon");
                                    }
                                    case 2 -> {
                                        dsPhieuNhap.menu(danhSachDT, nguoi, shop, dsncc);
                                        Lib.save(dsPhieuNhap, "dbPhieuNhap");
                                        Lib.save(danhSachDT, "dbDienThoai");
                                    }
                                    case 3 -> {
                                        danhSachDT.menu(danhSachNSX, dsncc);
                                        Lib.save(danhSachDT, "dbDienThoai");
                                    }
                                    case 0 -> logout = true;
                                }
                                if(logout)
                                    break;
                                Lib.clearScreen();
                            }
                        }
                        //Nhân viên bán hàng
                        else
                        {
                            while(true){
                                System.out.printf("+%-30s%-5s%-25s+\n", Lib.repeatStr("-", 30), "Nhân viên bán hàng", Lib.repeatStr("-", 25));
                                System.out.printf("%-20s%-15s%-25s\n", Lib.repeatStr(" ", 20), Lib.toBlueText("Xin chào " + nguoi.getHoTen()), Lib.repeatStr(" ", 25));
                                System.out.println("1. Quản lý hóa đơn");
                                System.out.println("2. Quản lý khách hàng");
                                System.out.println("3. Thống kê các hóa đơn");
                                System.out.println("4. Thống kê các mặt hàng đã bán");
                                System.out.println("0. Đăng xuất");
                                switch (Lib.takeInputChoice(0, 4)){
                                    case 1 -> {
                                        danhSachHoaDon.menuHD(danhSachDT, nguoi, shop, dsPhieuXuat);
                                        Lib.save(danhSachHoaDon, "dbHoaDon");
                                        Lib.save(dsPhieuXuat, "dbPhieuXuat");
                                    }
                                    case 2 -> {
                                        shop.menuDanhSachKhachHang();
                                        Lib.save(shop, "dbNguoi");
                                    }
                                    case 3 -> danhSachHoaDon.menuThongKeHoaDon(danhSachDT, nguoi, shop);
                                    case 4 -> danhSachHoaDon.menuThongKeMatHang(danhSachDT, danhSachNSX);
                                    case 0 -> logout = true;
                                }
                                if(logout)
                                    break;
                                Lib.clearScreen();
                            }
                        }
                    }
                    //Khách hàng
                    else if(nguoi instanceof KhachHang)
                    {
                        do
                        {
                            System.out.printf("+%-30s%-5s%-25s+\n", Lib.repeatStr("-", 30), "Khách hàng", Lib.repeatStr("-", 25));
                            System.out.printf("%-20s%-15s%-25s\n", Lib.repeatStr(" ", 20), Lib.toBlueText("Xin chào " + nguoi.getHoTen()), Lib.repeatStr(" ", 25));
                            System.out.println("1. Xem danh sách hóa đơn");
                            System.out.println("0. Thoát");
                            switch (Lib.takeInputChoice(0, 1))
                            {
                                case 1 -> {
                                    danhSachHoaDon.menuHD(danhSachDT, nguoi, shop, dsPhieuXuat);
                                    Lib.save(danhSachHoaDon, "dbHoaDon");
                                    Lib.save(danhSachDT, "dbDienThoai");
                                }
                                case 0 -> logout = true;
                            }
                            if(!logout)
                                Lib.clearScreen();
                        }while (!logout);
                    }
                    Lib.clearScreen();
                }
                //Đăng ký tài khoản khách hàng
                case 2 -> {
                    System.out.printf("+%-30s%-5s%-25s+\n", Lib.repeatStr("-", 30), "Đăng ký", Lib.repeatStr("-", 25));
                    shop.taoNguoi("KhachHang");
                    Lib.printMessage("Bạn đã đăng ký xong");
                    Lib.save(shop, "dbNguoi");
                }
                //Thoát chương trình
                case 0 -> {exit = true;}
            }

            if(exit)
                break;
        }

        Lib.printMessage("Chương trình đã kết thúc");
    }


}
