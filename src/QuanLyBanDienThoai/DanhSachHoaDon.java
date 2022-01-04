/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
*/
package QuanLyBanDienThoai;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Locale;

/**
 *
 * @author User
 */
public class DanhSachHoaDon implements Serializable {
    private ArrayList<HoaDon> hoaDonArrayList = new ArrayList<>();
    private int count = 0;

    public DanhSachHoaDon(DanhSachDT danhSachDT)
    {
        DanhSachChiTietHoaDon a = new DanhSachChiTietHoaDon();
        DienThoai dt1 = danhSachDT.getListDT()[0];
        DienThoai dt2 = danhSachDT.getListDT()[1];
        DienThoai dt3 = danhSachDT.getListDT()[2];
        a.getCthd().add(new ChiTietHoaDon(dt1.getmaDienThoai(), 10, dt1.getgiathanh()));
        a.getCthd().add(new ChiTietHoaDon(dt2.getmaDienThoai(), 3, dt2.getgiathanh()));
        a.getCthd().add(new ChiTietHoaDon(dt3.getmaDienThoai(), 6, dt3.getgiathanh()));
        dt1.mua(10);
        dt2.mua(3);
        dt3.mua(6);
        hoaDonArrayList.add(new HoaDon(getMaHoaDon(), "nam", "minh", "12/12/2020", a.tinhTongTien(),  a, "Đã xác nhận"));

        DanhSachChiTietHoaDon b = new DanhSachChiTietHoaDon();
        b.getCthd().add(new ChiTietHoaDon(dt2.getmaDienThoai(), 6, dt2.getgiathanh()));
        b.getCthd().add(new ChiTietHoaDon(dt3.getmaDienThoai(), 4, dt3.getgiathanh()));
        dt2.mua(6);
        dt3.mua(4);
        hoaDonArrayList.add(new HoaDon(getMaHoaDon(), "nam", "minh", "1/2/2021", b.tinhTongTien(),  b, "Đã xác nhận"));

        DanhSachChiTietHoaDon c = new DanhSachChiTietHoaDon();
        c.getCthd().add(new ChiTietHoaDon(dt1.getmaDienThoai(), 22, dt1.getgiathanh()));
        c.getCthd().add(new ChiTietHoaDon(dt3.getmaDienThoai(), 40, dt3.getgiathanh()));
        dt1.mua(22);
        dt3.mua(40);
        hoaDonArrayList.add(new HoaDon(getMaHoaDon(), "nam", "sinh", "11/2/2021", c.tinhTongTien(),  c, "Đã xác nhận"));

        DanhSachChiTietHoaDon d = new DanhSachChiTietHoaDon();
        d.getCthd().add(new ChiTietHoaDon(dt1.getmaDienThoai(), 55, dt1.getgiathanh()));
        d.getCthd().add(new ChiTietHoaDon(dt3.getmaDienThoai(), 11, dt3.getgiathanh()));
        dt1.mua(55);
        dt3.mua(11);
        hoaDonArrayList.add(new HoaDon(getMaHoaDon(), "nam", "sinh", "26/5/2021", d.tinhTongTien(),  d, "Đã xác nhận"));

        DanhSachChiTietHoaDon e = new DanhSachChiTietHoaDon();
        e.getCthd().add(new ChiTietHoaDon(dt1.getmaDienThoai(), 33, dt1.getgiathanh()));
        e.getCthd().add(new ChiTietHoaDon(dt2.getmaDienThoai(), 61, dt2.getgiathanh()));
        dt1.mua(33);
        dt2.mua(61);
        hoaDonArrayList.add(new HoaDon(getMaHoaDon(), "", "minh", "30/7/2021", e.tinhTongTien(),  e, "Đang chờ xác nhận"));

    }

    private HoaDon timKiemHoaDonTheoID(String id, Nguoi nguoi)
    {
        if(nguoi instanceof NhanVienBanHang)
            return hoaDonArrayList.stream().filter(x -> x.getMahd().equals(id)).findAny().orElse(null);
        else
            return hoaDonArrayList.stream().filter(x -> x.getMahd().equals(id) && x.getMakh().equals(nguoi.getId())).findAny().orElse(null);
    }

    private String getMaHoaDon()
    {
        count++;
        Integer a = count;
        String str = a.toString();
        while(str.length() != 3)
            str = "0" + str;
        str = "HD" + str;
        return str;
    }

    public void themHDCuaKhachHang(DanhSachDT danhSachDT, Nguoi nguoi, Shop shop){
        System.out.println(Lib.toBlueText("Mua điện thoại"));
        String mahd = getMaHoaDon();
        String manv = "";
        String makh = nguoi.getId();

        DanhSachChiTietHoaDon ds = new DanhSachChiTietHoaDon();
        ds.NhapCTHD(danhSachDT);

        String timexuat = Lib.getDateNow();
        hoaDonArrayList.add(new HoaDon(mahd, manv,makh,timexuat, ds.tinhTongTien(), ds, "Đang chờ xác nhận"));
    }

    public void suaHD(DanhSachDT danhSachDT, Nguoi nguoi, Shop shop)
    {
        String id = Lib.takeStringInput("Mã hóa đơn cần sửa: ");
        HoaDon hoaDon = timKiemHoaDonTheoID(id, nguoi);
        if(hoaDon == null)
        {
            Lib.printError("Không có hóa đơn đó");
            return;
        }
        boolean out = false;
        do {
            xuatChiTietHoaDon(hoaDon);
            System.out.println(Lib.toBlueText("Sủa thông tin hóa đơn"));
            System.out.println("1. Sửa số lượng trong chi tiết hoá đơn");
            System.out.println("2. Xoá điện thoại trong chi tiết hóa đơn");
            System.out.println("0. Thoát");
            switch (Lib.takeInputChoice(0, 2))
            {
                case 1 -> {
                    String iddt = Lib.takeStringInput("Nhập mã điện thoại cần sửa: ");
                    ChiTietHoaDon chiTietHoaDon = hoaDon.getDsCTHD().timKiemTheoIDHoaDon(iddt);
                    if(chiTietHoaDon == null)
                        Lib.printError("Không có mã điện thoại này trong chi tiết hóa đơn");
                    else
                    {
                        int num = Lib.takeIntegerInput("Nhập số lượng mới: ");
                        chiTietHoaDon.setSluong(num);
                        hoaDon.setTongtien(hoaDon.getDsCTHD().tinhTongTien());
                    }
                }
                case 2 -> {
                    String iddt = Lib.takeStringInput("Nhập mã điện thoại cần xóa: ");
                    ChiTietHoaDon chiTietHoaDon = hoaDon.getDsCTHD().timKiemTheoIDHoaDon(iddt);
                    if(chiTietHoaDon == null)
                        Lib.printError("Không có mã điện thoại này trong chi tiết hóa đơn");
                    else
                        hoaDon.getDsCTHD().delete(chiTietHoaDon);
                }
                case 0 -> out = true;
            }
            if(!out)
                Lib.clearScreen();
        }while(!out);
    }

    public void timHD(Nguoi nguoi)
    {
        System.out.println("Tìm hóa đơn");
        String tuKhoa = Lib.takeStringInput("Nhập từ khóa cần tìm: ");
        ArrayList<HoaDon> filter = new ArrayList<>();
        for (HoaDon hoaDon: hoaDonArrayList)
        {
            int gia;
            try {gia = Integer.parseInt(tuKhoa);}
            catch (NumberFormatException ignored) {gia = Integer.MIN_VALUE;}
            if(hoaDon.getMakh().equals(nguoi.getId()) || nguoi instanceof NhanVienBanHang)
            {
                if(
                        hoaDon.getMahd().toLowerCase(Locale.ROOT).contains(tuKhoa.toLowerCase(Locale.ROOT)) ||
                                hoaDon.getMakh().toLowerCase(Locale.ROOT).contains(tuKhoa.toLowerCase(Locale.ROOT)) ||
                                hoaDon.getTinhTrang().toLowerCase(Locale.ROOT).contains(tuKhoa.toLowerCase(Locale.ROOT)) ||
                                hoaDon.getTongtien() == gia ||
                                hoaDon.getTimexuat().toLowerCase(Locale.ROOT).contains(tuKhoa.toLowerCase(Locale.ROOT))
                )
                {
                    filter.add(hoaDon);
                }
            }

        }

        System.out.println(Lib.toBlueText("Kết quả tìm kiếm theo từ khóa: ")  + Lib.toGreenText(tuKhoa));
        if(filter.isEmpty())
            Lib.printError("Không có hóa đơn nào");
        else
        {
            xuatTDHD();
            for (HoaDon hoaDon : filter)
                    hoaDon.xuatThongTin();
        }

    }


    public void xoaHD(Nguoi nguoi){
        System.out.println(Lib.toBlueText("Xóa hóa đơn"));
        String idHoaDon = Lib.takeStringInput("Mã hóa đơn cần xóa: ");
        HoaDon hoaDon = timKiemHoaDonTheoID(idHoaDon, nguoi);
       
        if(hoaDon != null){
            hoaDonArrayList.remove(hoaDon);
            Lib.printMessage("Hóa đơn đã xóa khỏi danh sách");
        }else{
            Lib.printError("Hóa đơn không có trong danh sách");
        }
   }
    //┘ └ ┌ ┐ ├ ┤ ┴ ┬ ┼ │ ─

    private void xuatTDHD()
    {
       System.out.printf("┌%-20s┬%-20s┬%-20s┬%-10s┬%-15s┬%-25s┐ \n", Lib.repeatStr("─", 20), Lib.repeatStr("─", 20), Lib.repeatStr("─", 20), Lib.repeatStr("─", 10), Lib.repeatStr("─", 15), Lib.repeatStr("─", 25));
       System.out.printf("|%-20s│%-20s│%-20s│%-10s│%-15s│%-25s| \n", "Mã hóa đơn" , "Mã nhân viên","Mã khách hàng","Thời gian","Tổng tiền", "Tình trạng");
       System.out.printf("├%-20s┼%-20s┼%-20s┼%-10s┼%-15s┼%-25s┤ \n", Lib.repeatStr("─", 20) , Lib.repeatStr("─", 20), Lib.repeatStr("─", 20), Lib.repeatStr("─", 10), Lib.repeatStr("─", 15), Lib.repeatStr("─", 25));
    }

    private void xuatChiTietHoaDon(HoaDon hoaDon)
    {
       xuatTDHD();
       hoaDon.xuatThongTin();
       System.out.printf("├%-20s┴%-20s┴%-20s┴%-10s┴%-15s┴%-25s┤ \n", Lib.repeatStr("─", 20), Lib.repeatStr("─", 20), Lib.repeatStr("─", 20), Lib.repeatStr("─", 10), Lib.repeatStr("─", 15), Lib.repeatStr("─", 25));
       hoaDon.getDsCTHD().XuatCTHD();
       System.out.printf("└%-20s┴%-20s┴%-20s┴%-10s─%-15s┴%-25s┘ \n", Lib.repeatStr("─", 20), Lib.repeatStr("─", 20), Lib.repeatStr("─", 20), Lib.repeatStr("─", 10), Lib.repeatStr("─", 15), Lib.repeatStr("─", 25));
   }

    public void xuatHD(Nguoi nguoi){
        int count = 0;
        System.out.println(Lib.toBlueText("Danh sách hóa đơn"));
        xuatTDHD();
        for (HoaDon hoaDon : hoaDonArrayList)
        {
            if(hoaDon.getMakh().equals(nguoi.getId()) || nguoi instanceof NhanVienBanHang)
            {
                hoaDon.xuatThongTin();
                count++;
            }
        }

        if(count == 0)
            Lib.printError("Không có hóa đơn nào");
    }


    private void xemChiTietHoaDon(Nguoi nguoi)
    {
        String id = Lib.takeStringInput("Nhập mã hóa đơn cần xem: ");
        HoaDon hoaDon = timKiemHoaDonTheoID(id, nguoi);
        if (hoaDon == null)
            Lib.printError("Không có hóa đơn này");
        else
            xuatChiTietHoaDon(hoaDon);
    }

    private void xacNhanHoaDon(Nguoi nguoi, DSPhieuXuat dsPhieuXuat)
    {
        String id = Lib.takeStringInput("Nhập mã hóa đơn cần xác nhận: ");
        HoaDon hoaDon = hoaDonArrayList.stream().filter(x -> x.getMahd().equals(id)).findAny().orElse(null);
        if (hoaDon == null)
            Lib.printError("Không tìm thấy");
        else {
            if(hoaDon.getTinhTrang().equals("Đang chờ xác nhận"))
            {
                hoaDon.setManv(nguoi.getId());
                hoaDon.setTinhTrang("Đã xác nhận");
                dsPhieuXuat.add(hoaDon);
            }
            else
                Lib.printError("Không thực hiện được");
        }
    }

    private void xacNhanDaNhanDuocHang(Nguoi nguoi)
    {
        String id = Lib.takeStringInput("Nhập mã hóa đơn cần xác nhận: ");
        HoaDon hoaDon = timKiemHoaDonTheoID(id, nguoi);
        if(hoaDon != null)
        {
            if(hoaDon.getTinhTrang().equals("Đã xuất kho"))
                hoaDon.setTinhTrang("Đã nhận hàng");
        }
        else
            Lib.printError("Không thực hiện được");
    }

    private void thongKeCacHoaTheoKhachHang()
    {
        String makh = Lib.takeStringInput("Nhập mã khách hàng: ");
        int tongDoanhThu = 0;
        xuatTDHD();
        for(HoaDon hoaDon : hoaDonArrayList)
        {
            if(hoaDon.getMakh().contains(makh) && hoaDon.getTinhTrang().equals("Đã nhận hàng"))
            {
                hoaDon.xuatThongTin();
                tongDoanhThu += hoaDon.getTongtien();
            }
        }
        System.out.println("Tổng doanh thu: " + Lib.toGreenText(String.valueOf(tongDoanhThu)));
    }

    private void thongKeCacHoaTheoNhanVien()
    {
        String manv = Lib.takeStringInput("Nhập mã nhân viên: ");
        int tongDoanhThu = 0;
        xuatTDHD();
        for(HoaDon hoaDon : hoaDonArrayList)
        {
            if(hoaDon.getManv().contains(manv) && hoaDon.getTinhTrang().equals("Đã nhận hàng"))
            {
                hoaDon.xuatThongTin();
                tongDoanhThu += hoaDon.getTongtien();
            }
        }
        System.out.println("Tổng doanh thu: " + Lib.toGreenText(String.valueOf(tongDoanhThu)));
    }

    private void thongKeTheoKhoangThoiGian()
    {
        String from = Lib.takeDateInput("Từ ngày: ");
        String to = Lib.takeDateInput("Đến ngày: ");
        int tongDoanhThu = 0;
        xuatTDHD();
        for(HoaDon hoaDon : hoaDonArrayList)
        {
            if(hoaDon.getTinhTrang().equals("Đã nhận hàng") && Lib.kiemTraKhoangThoiGian(from, hoaDon.getTimexuat(), to))
            {
                hoaDon.xuatThongTin();
                tongDoanhThu += hoaDon.getTongtien();
            }
        }
        System.out.println("Tổng doanh thu: " + Lib.toGreenText(String.valueOf(tongDoanhThu)));
    }

    private void thongKeTheoKhoangTongTienHoaDon()
    {
        int from = Lib.takeIntegerInput("Hóa đơn tổng tiền từ: ");
        int to = Lib.takeIntegerInput("Hóa đơn tổng tiền đến: ");
        int tongDoanhThu = 0;
        xuatTDHD();
        for(HoaDon hoaDon : hoaDonArrayList)
        {
            if(hoaDon.getTinhTrang().equals("Đã nhận hàng") && hoaDon.getTongtien() >= from && hoaDon.getTongtien() <= to)
            {
                hoaDon.xuatThongTin();
                tongDoanhThu += hoaDon.getTongtien();
            }
        }
        System.out.println("Tổng doanh thu: " + Lib.toGreenText(String.valueOf(tongDoanhThu)));
    }

    public void menuThongKeHoaDon(DanhSachDT danhSachDT, Nguoi nguoi, Shop shop)
    {
        boolean out = false;
        int tongDoanhThu = 0;
        do {
            xuatTDHD();
            for(HoaDon hoaDon : hoaDonArrayList)
            {
                if(hoaDon.getTinhTrang().equals("Đã nhận hàng"))
                {
                    hoaDon.xuatThongTin();
                    tongDoanhThu += hoaDon.getTongtien();
                }
            }

            System.out.println("Tổng doanh thu: " + Lib.toGreenText(String.valueOf(tongDoanhThu)));
            System.out.println(Lib.toBlueText("THỐNG KÊ HÓA ĐƠN"));
            System.out.println("1. Thống kê theo khách hàng");
            System.out.println("2. Thống kê theo nhân viên");
            System.out.println("3. Thống kê theo khoảng thời gian");
            System.out.println("4. Thống kê theo khoảng tổng tiền của hóa đơn");
            System.out.println("0. Thoát");
            switch (Lib.takeInputChoice(0, 4))
            {
                case 1 -> {thongKeCacHoaTheoKhachHang();}
                case 2 -> {thongKeCacHoaTheoNhanVien();}
                case 3 -> {thongKeTheoKhoangThoiGian();}
                case 4 -> {thongKeTheoKhoangTongTienHoaDon();}
                case 0 -> out = true;
            }
            if(!out)
                Lib.clearScreen();
        }while (!out);
    }

    public void thongKeTheoHangDienThoai(DanhSachDT danhSachDT, DanhSachNSX danhSachNSX)
    {

        System.out.println("Chọn hãng cần thống kê");
        System.out.println("1. Apple");
        System.out.println("2. SamSung");
        System.out.println("3. Oppo");
        System.out.println("4. Nokia");
        String tenNSX = "";
        switch (Lib.takeInputChoice(1, 4))
        {
            case 1 -> tenNSX = "Apple";
            case 2 -> tenNSX = "SamSung";
            case 3 -> tenNSX = "Oppo";
            case 4 -> tenNSX = "Nokia";
        }

        NhaSX nhaSX = new NhaSX();
        for (NhaSX obj : danhSachNSX.getNsxArray())
        {
            if(obj.gettenNSX().equals(tenNSX));
            {
                nhaSX = obj;
                break;
            }
        }

        System.out.println(Lib.toBlueText("DANH SÁCH MẶT HÀNG ĐÃ BÁN"));
        System.out.println("Thống kê theo " + Lib.toGreenText(tenNSX));
        System.out.printf("┌%-20s┬%-20s┬%-20s┬%-20s┐\n", Lib.repeatStr("─", 20), Lib.repeatStr("─", 20), Lib.repeatStr("─", 20), Lib.repeatStr("─", 20));
        System.out.printf("│%-20s│%-20s│%-20s│%-20s│\n", "Mã điện thoại", "Tên điện thoại", "Số lượng đã bán", "Tổng tiền");
        System.out.printf("├%-20s┼%-20s┼%-20s┼%-20s┤\n", Lib.repeatStr("─", 20), Lib.repeatStr("─", 20), Lib.repeatStr("─", 20), Lib.repeatStr("─", 20));

        int tongDoanhThu = 0;
        int []soLuong = new int[danhSachDT.getsoLuong()];
        int []tongTien = new int[danhSachDT.getsoLuong()];

        for(HoaDon hoaDon : hoaDonArrayList)
        {
            if(hoaDon.getTinhTrang().equals("Đã nhận hàng"))
            {
                for(ChiTietHoaDon chiTietHoaDon : hoaDon.getDsCTHD().getCthd())
                {
                    int index = danhSachDT.timkiemmaDienThoai(chiTietHoaDon.getMadienthoai());
                    if(danhSachDT.getListDT()[index].getmaNSX().equals(nhaSX.getmaNSX()))
                    {
                        if(index != -1)
                        {
                            soLuong[index] += chiTietHoaDon.getSluong();
                            tongTien[index] += chiTietHoaDon.getSluong() * chiTietHoaDon.getGia();
                            tongDoanhThu += chiTietHoaDon.getSluong() * chiTietHoaDon.getGia();
                        }
                    }
                }
            }
        }

        for (int i = 0; i < danhSachDT.getsoLuong(); i++) {
            DienThoai dienThoai = danhSachDT.getListDT()[i];
            if(dienThoai.getmaNSX().equals(nhaSX.getmaNSX()))
                System.out.printf("│%-20s│%-20s│%-20s│%-20s│\n", dienThoai.getmaDienThoai(), dienThoai.gettenDienThoai(), soLuong[i], tongTien[i]);
        }

        System.out.println("Tổng doanh thu: " + Lib.toGreenText(String.valueOf(tongDoanhThu)));


    }

    public void menuThongKeMatHang(DanhSachDT danhSachDT, DanhSachNSX danhSachNSX)
    {
        boolean out = false;
        int tongDoanhThu = 0;
        do {
            System.out.println(Lib.toBlueText("DANH SÁCH MẶT HÀNG ĐÃ BÁN"));
            System.out.printf("┌%-20s┬%-20s┬%-20s┬%-20s┐\n", Lib.repeatStr("─", 20), Lib.repeatStr("─", 20), Lib.repeatStr("─", 20), Lib.repeatStr("─", 20));
            System.out.printf("│%-20s│%-20s│%-20s│%-20s│\n", "Mã điện thoại", "Tên điện thoại", "Số lượng đã bán", "Tổng tiền");
            System.out.printf("├%-20s┼%-20s┼%-20s┼%-20s┤\n", Lib.repeatStr("─", 20), Lib.repeatStr("─", 20), Lib.repeatStr("─", 20), Lib.repeatStr("─", 20));

            int []soLuong = new int[danhSachDT.getsoLuong()];
            int []tongTien = new int[danhSachDT.getsoLuong()];

            for(HoaDon hoaDon : hoaDonArrayList)
            {
                if(hoaDon.getTinhTrang().equals("Đã nhận hàng"))
                {
                    for(ChiTietHoaDon chiTietHoaDon : hoaDon.getDsCTHD().getCthd())
                    {
                        int index = danhSachDT.timkiemmaDienThoai(chiTietHoaDon.getMadienthoai());
                        if(index != -1)
                        {
                            soLuong[index] += chiTietHoaDon.getSluong();
                            tongTien[index] += chiTietHoaDon.getSluong() * chiTietHoaDon.getGia();
                            tongDoanhThu += chiTietHoaDon.getSluong() * chiTietHoaDon.getGia();
                        }
                    }
                }
            }

            for (int i = 0; i < danhSachDT.getsoLuong(); i++) {
                DienThoai dienThoai = danhSachDT.getListDT()[i];
                System.out.printf("│%-20s│%-20s│%-20s│%-20s│\n", dienThoai.getmaDienThoai(), dienThoai.gettenDienThoai(), soLuong[i], tongTien[i]);
            }

            System.out.println("Tổng doanh thu: " + Lib.toGreenText(String.valueOf(tongDoanhThu)));
            System.out.println(Lib.toBlueText("THỐNG KÊ MẶT HÀNG"));
            System.out.println("1. Thống kê theo hãng điện thoại");
            System.out.println("0. Thoát");
            switch (Lib.takeInputChoice(0, 1))
            {
                case 1 -> thongKeTheoHangDienThoai(danhSachDT, danhSachNSX);
                case 0 -> out = true;
            }
            if(!out)
                Lib.clearScreen();
        }while (!out);
    }

    public void menuHD(DanhSachDT danhSachDT, Nguoi nguoi, Shop shop, DSPhieuXuat dsPhieuXuat){
        boolean out = false;
        //Menu của nhân viên bán hàng
        if(nguoi instanceof NhanVienBanHang)
        {
            do{
                xuatHD(nguoi);
                System.out.println(Lib.toBlueText("MENU HÓA ĐƠN"));
                System.out.println("1. Xem chi tiết hóa đơn");
                System.out.println("2. Sửa thông tin hóa đơn");
                System.out.println("3. Tìm kiếm trong bảng");
                System.out.println("4. Xóa hóa đơn");
                System.out.println("5. Xác nhận hóa đơn");
                System.out.println("0. Thoát menu");
                switch (Lib.takeInputChoice(0, 5)) {
                    case 1 -> xemChiTietHoaDon(nguoi);
                    case 2 -> suaHD(danhSachDT, nguoi, shop);
                    case 3 -> timHD(nguoi);
                    case 4 -> xoaHD(nguoi);
                    case 5 -> xacNhanHoaDon(nguoi, dsPhieuXuat);
                    case 0 -> out = true;
                }
                if(!out)
                    Lib.clearScreen();
            }while(!out);
        }
        else //Menu của khách hàng
        {
            do{
                xuatHD(nguoi);
                System.out.println(Lib.toBlueText("MENU HÓA ĐƠN"));
                System.out.println("1. Xem chi tiết hóa đơn");
                System.out.println("2. Mua điện thoại");
                System.out.println("3. Tìm kiếm trong bảng");
                System.out.println("4. Xác nhận hóa đơn đã nhận được hàng");
                System.out.println("0. Thoát menu");
                switch (Lib.takeInputChoice(0, 4)) {
                    case 1 -> xemChiTietHoaDon(nguoi);
                    case 2 -> themHDCuaKhachHang(danhSachDT, nguoi, shop);
                    case 3 -> timHD(nguoi);
                    case 4 -> xacNhanDaNhanDuocHang(nguoi);
                    case 0 -> out = true;
                }
                if(!out)
                    Lib.clearScreen();
            }while(!out);
        }

    }

    public ArrayList<HoaDon> getHoaDonArrayList() {
        return hoaDonArrayList;
    }

    public void setHoaDonArrayList(ArrayList<HoaDon> hoaDonArrayList) {
        this.hoaDonArrayList = hoaDonArrayList;
    }
}