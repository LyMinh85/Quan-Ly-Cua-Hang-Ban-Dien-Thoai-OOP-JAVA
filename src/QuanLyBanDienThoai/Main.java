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

        System.out.printf("+%-30s%-5s%-25s+\n", Lib.repeatStr("-", 30), "C???a h??ng b??n ??i???n tho???i", Lib.repeatStr("-", 25));
        while(true)
        {
            System.out.printf("+%-30s%-5s%-25s+\n", Lib.repeatStr("-", 30), "????ng nh???p", Lib.repeatStr("-", 25));
            System.out.println("1. ????ng nh???p");
            System.out.println("2. ????ng k??");
            System.out.println("0. Tho??t");
            switch (Lib.takeInputChoice(0,2))
            {
                case 1 -> {
                    Nguoi nguoi = shop.login();
                    boolean logout = false;
                    //Kh??ng c?? t??i kho???n ????
                    if(nguoi == null)
                        Lib.printError("T??i kho???n kh??ng t???n t???i");
                    //Ng?????i qu???n l?? (admin)
                    else if(nguoi instanceof QuanLy)
                    {
                        while(true)
                        {
                            System.out.printf("+%-30s%-5s%-25s+\n", Lib.repeatStr("-", 30), "Admin", Lib.repeatStr("-", 25));
                            System.out.printf("%-20s%-15s%-25s\n", Lib.repeatStr(" ", 20), Lib.toBlueText("Xin ch??o " + nguoi.getHoTen()), Lib.repeatStr(" ", 25));
                            System.out.println("1. Xem danh s??ch nh??n vi??n");
                            System.out.println("2. Xem danh s??ch nh?? s???n xu???t");
                            System.out.println("3. Xem danh s??ch nh?? cung c???p");
                            System.out.println("0. ????ng xu???t");
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
                        //Nh??n vi??n th??? kho
                        if(nguoi instanceof NhanVienThuKho)
                        {
                            while(true){
                                System.out.printf("+%-30s%-5s%-25s+\n", Lib.repeatStr("-", 30), "Nh??n vi??n th??? kho", Lib.repeatStr("-", 25));
                                System.out.printf("%-20s%-15s%-25s\n", Lib.repeatStr(" ", 20), Lib.toBlueText("Xin ch??o " + nguoi.getHoTen()), Lib.repeatStr(" ", 25));
                                System.out.println("1. Xem c??c phi???u xu???t");
                                System.out.println("2. Xem c??c phi???u nh???p");
                                System.out.println("3. Xem danh s??ch ??i???n tho???i");
                                System.out.println("0. ????ng xu???t");
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
                        //Nh??n vi??n b??n h??ng
                        else
                        {
                            while(true){
                                System.out.printf("+%-30s%-5s%-25s+\n", Lib.repeatStr("-", 30), "Nh??n vi??n b??n h??ng", Lib.repeatStr("-", 25));
                                System.out.printf("%-20s%-15s%-25s\n", Lib.repeatStr(" ", 20), Lib.toBlueText("Xin ch??o " + nguoi.getHoTen()), Lib.repeatStr(" ", 25));
                                System.out.println("1. Qu???n l?? h??a ????n");
                                System.out.println("2. Qu???n l?? kh??ch h??ng");
                                System.out.println("3. Th???ng k?? c??c h??a ????n");
                                System.out.println("4. Th???ng k?? c??c m???t h??ng ???? b??n");
                                System.out.println("0. ????ng xu???t");
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
                    //Kh??ch h??ng
                    else if(nguoi instanceof KhachHang)
                    {
                        do
                        {
                            System.out.printf("+%-30s%-5s%-25s+\n", Lib.repeatStr("-", 30), "Kh??ch h??ng", Lib.repeatStr("-", 25));
                            System.out.printf("%-20s%-15s%-25s\n", Lib.repeatStr(" ", 20), Lib.toBlueText("Xin ch??o " + nguoi.getHoTen()), Lib.repeatStr(" ", 25));
                            System.out.println("1. Xem danh s??ch h??a ????n");
                            System.out.println("0. Tho??t");
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
                //????ng k?? t??i kho???n kh??ch h??ng
                case 2 -> {
                    System.out.printf("+%-30s%-5s%-25s+\n", Lib.repeatStr("-", 30), "????ng k??", Lib.repeatStr("-", 25));
                    shop.taoNguoi("KhachHang");
                    Lib.printMessage("B???n ???? ????ng k?? xong");
                    Lib.save(shop, "dbNguoi");
                }
                //Tho??t ch????ng tr??nh
                case 0 -> {exit = true;}
            }

            if(exit)
                break;
        }

        Lib.printMessage("Ch????ng tr??nh ???? k???t th??c");
    }


}
