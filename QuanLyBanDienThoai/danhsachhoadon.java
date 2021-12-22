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
public class danhsachhoadon implements Serializable {
    private ArrayList<HoaDon> hoaDonArrayList = new ArrayList<>();
    private int count = 0;

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

        danhsachChitietHD ds = new danhsachChitietHD();
        ds.NhapCTHD(danhSachDT);

        String timexuat = Lib.getDateNow();
        hoaDonArrayList.add(new HoaDon(mahd, manv,makh,timexuat, ds.tinhTongTien(), ds, "Đang chờ xác nhận"));
    }

    public void suaHD(DanhSachDT danhSachDT, Nguoi nguoi, Shop shop)
    {
        String id = Lib.takeStringInput("Mã hóa đơn cần sửa: ");
        HoaDon hoaDon = timKiemHoaDonTheoID(id, nguoi);
        boolean out = false;
        do {
            xuatChiTietHoaDon(hoaDon);
            System.out.println(Lib.toBlueText("Sủa thông tin hóa đơn"));
            System.out.println("1. Sửa số lượng trong chi tiết hoá đơn");
            System.out.println("0. Thoát");
            switch (Lib.takeInputChoice(0, 1))
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
        ArrayList<HoaDon> dshd = new ArrayList<>();
        for (HoaDon hoaDon: hoaDonArrayList)
        {
            int gia;
            try {gia = Integer.parseInt(tuKhoa);}
            catch (NumberFormatException ignored) {gia = Integer.MIN_VALUE;}

            if(
                    hoaDon.getMahd().toLowerCase(Locale.ROOT).contains(tuKhoa.toLowerCase(Locale.ROOT)) ||
                    hoaDon.getMakh().toLowerCase(Locale.ROOT).contains(tuKhoa.toLowerCase(Locale.ROOT)) ||
                    hoaDon.getTinhTrang().toLowerCase(Locale.ROOT).contains(tuKhoa.toLowerCase(Locale.ROOT)) ||
                    hoaDon.getTongtien() == gia ||
                    hoaDon.getTimexuat().toLowerCase(Locale.ROOT).contains(tuKhoa.toLowerCase(Locale.ROOT))
            )
            {
                dshd.add(hoaDon);
            }
        }

        int count = 0;
        System.out.println(Lib.toBlueText("Danh sách hóa đơn tìm kiếm theo từ khóa: ")  + Lib.toGreenText(tuKhoa));
        xuatTDHD();
        for (HoaDon hoaDon : dshd)
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

   private void xuatTDHD()
   {
       System.out.printf("+%-20s+%-20s+%-20s+%-10s+%-15s+%-25s+ \n", Lib.repeatStr("-", 20), Lib.repeatStr("-", 20), Lib.repeatStr("-", 20), Lib.repeatStr("-", 10), Lib.repeatStr("-", 15), Lib.repeatStr("-", 25));
       System.out.printf("|%-20s|%-20s|%-20s|%-10s|%-15s|%-25s| \n", "Mã hóa đơn" , "Mã nhân viên","Mã khách hàng","Thời gian","Tổng tiền", "Tình trạng");
       System.out.printf("+%-20s+%-20s+%-20s+%-10s+%-15s+%-25s+ \n", Lib.repeatStr("-", 20) , Lib.repeatStr("-", 20), Lib.repeatStr("-", 20), Lib.repeatStr("-", 10), Lib.repeatStr("-", 15), Lib.repeatStr("-", 25));
   }

   private void xuatChiTietHoaDon(HoaDon hoaDon)
   {
       xuatTDHD();
       hoaDon.xuatThongTin();
       System.out.printf("+%-20s+%-20s+%-20s+%-10s+%-15s+%-25s+ \n", Lib.repeatStr("-", 20), Lib.repeatStr("-", 20), Lib.repeatStr("-", 20), Lib.repeatStr("-", 10), Lib.repeatStr("-", 15), Lib.repeatStr("-", 25));
       hoaDon.getDsCTHD().XuatCTHD();
       System.out.printf("+%-20s+%-20s+%-20s+%-10s+%-15s+%-25s+ \n", Lib.repeatStr("-", 20), Lib.repeatStr("-", 20), Lib.repeatStr("-", 20), Lib.repeatStr("-", 10), Lib.repeatStr("-", 15), Lib.repeatStr("-", 25));
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

    private void thayDoiTinhTrangHoaDon(Nguoi nguoi)
    {
        if(nguoi instanceof NhanVienBanHang)
        {
            String id = Lib.takeStringInput("Nhập mã hóa đơn cần xác nhận: ");
            HoaDon hoaDon = hoaDonArrayList.stream().filter(x -> x.getMahd().equals(id)).findAny().orElse(null);
            if (hoaDon == null)
                Lib.printError("Không tìm thấy");
            else {
                hoaDon.setManv(nguoi.getId());
                hoaDon.setTinhTrang("Đã xác nhận");
            }
        }
        else
        {
            String id = Lib.takeStringInput("Nhập mã hóa đơn cần hủy: ");
            HoaDon hoaDon = hoaDonArrayList.stream().filter(x -> x.getMahd().equals(id)).findAny().orElse(null);
            if (hoaDon == null)
                Lib.printError("Không tìm thấy");
            else
                hoaDon.setTinhTrang("Yêu cầu hủy");
        }
    }

    public void menuHD(DanhSachDT danhSachDT, Nguoi nguoi, Shop shop){
        boolean out = false;
        //Menu của nhân viên bán hàng
        if(nguoi instanceof NhanVienBanHang)
        {
            do{
                xuatHD(nguoi);
                System.out.println(Lib.toBlueText("MENU HÓA ĐƠN"));
                System.out.println("1. Xem chi tiết hóa đơn");
                System.out.println("2. Sửa thông tin hóa đơn");
                System.out.println("3. Tìm kiếm");
                System.out.println("4. Xóa hóa đơn");
                System.out.println("5. Xác nhận hóa đơn");
                System.out.println("0. Thoát menu");
                switch (Lib.takeInputChoice(0, 5)) {
                    case 1 -> xemChiTietHoaDon(nguoi);
                    case 2 -> suaHD(danhSachDT, nguoi, shop);
                    case 3 -> timHD(nguoi);
                    case 4 -> xoaHD(nguoi);
                    case 5 -> thayDoiTinhTrangHoaDon(nguoi);
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
                System.out.println("3. Tìm kiếm");
                System.out.println("4. Yêu cầu hủy hóa đơn");
                System.out.println("0. Thoát menu");
                switch (Lib.takeInputChoice(0, 4)) {
                    case 1 -> xemChiTietHoaDon(nguoi);
                    case 2 -> themHDCuaKhachHang(danhSachDT, nguoi, shop);
                    case 3 -> timHD(nguoi);
                    case 4 -> thayDoiTinhTrangHoaDon(nguoi);
                    case 0 -> out = true;
                }
                if(!out)
                    Lib.clearScreen();
            }while(!out);
        }

    }

}