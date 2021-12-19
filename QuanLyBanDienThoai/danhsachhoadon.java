/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
*/
package QuanLyBanDienThoai;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class danhsachhoadon implements Serializable {
    private ArrayList<HoaDon> hd = new ArrayList<>();

    private HoaDon timKiemHoaDonTheoID(String id)
    {
        return hd.stream().filter(x -> x.getMahd().equals(id)).findAny().orElse(null);
    }

    public String nhapMaHoaDon()
    {
        do {
            String mahd = Lib.takeStringInput("Mã hóa đơn: ");
            if(timKiemHoaDonTheoID(mahd) != null)
                Lib.printError("Mã này đã có");
            else
                return mahd;
        }while (true);
    }
    
public void nhapHD(DanhSachDT danhSachDT, Nguoi nguoi, Shop shop){
        
        System.out.println("Nhập danh sách hóa đơn");            
        int n = Lib.takeSoLuongCanTao("Nhập số lượng hóa đơn:");
         for (int i = 0; i < n; i++) {
             String mahd = nhapMaHoaDon();
             String manv = nguoi.getId();
             String makh = Lib.nhapIDKhachHang(shop, "KhachHang");
             if(makh.equals("stop"))
             {
                 Lib.printMessage("Đã dừng");
                 return;
             }

        danhsachChitietHD ds = new danhsachChitietHD();
        ds.NhapCTHD(danhSachDT);

        Date date = new Date();
        String timexuat = String.format("%tc",date);
        System.out.println(date); 
        hd.add(new HoaDon(mahd, manv,makh,timexuat, ds.Tien(), ds, "Đang chờ xử lý"));
    }
               
        
             
}   
public void themHD(DanhSachDT danhSachDT, Nguoi nguoi, Shop shop){
        nhapHD(danhSachDT, nguoi, shop);
    }

    public void themHDCuaKhachHang(DanhSachDT danhSachDT, Nguoi nguoi, Shop shop){
        System.out.println("Nhập danh sách hóa đơn");
        int n = Lib.takeSoLuongCanTao("Nhập số lượng hóa đơn:");
        for (int i = 0; i < n; i++) {
            String mahd = nhapMaHoaDon();
            String manv = "";
            String makh = nguoi.getId();

            danhsachChitietHD ds = new danhsachChitietHD();
            ds.NhapCTHD(danhSachDT);

            Date date = new Date();
            String timexuat = String.format("%tc",date);
            System.out.println(date);
            hd.add(new HoaDon(mahd, manv,makh,timexuat, ds.Tien(), ds, "Đang chờ xác nhận"));
        }
    }
    public void suaHD(DanhSachDT danhSachDT, Nguoi nguoi, Shop shop){
        System.out.println("Sủa thông tin hóa đơn");
        String xo = Lib.takeStringInput("Mã hóa đơn cần sửa: ");
        for (HoaDon hoaDon : hd) {
            if (hoaDon.getMakh().equals(xo)) {
                System.out.println("Nhập danh sách hóa đơn");
                Date date = new Date();
                String timexuat = String.format("%tc",date);
                System.out.println(date);
                String manv = Lib.nhapIDNhanVien(shop, "NhanVienBanHang");
                String makh;
                if(nguoi instanceof KhachHang)
                {
                    makh = nguoi.getId();
                }
                else
                {
                    makh = Lib.nhapIDKhachHang(shop, "KhachHang");
                    if(makh.equals("stop"))
                    {
                        Lib.printMessage("Đã dừng");
                        return;
                    }

                }
                hoaDon.setManv(manv);
                hoaDon.setMakh(makh);
                hoaDon.getTimexuat();
            }                
            }           
        }
    public void timHD(){
         System.out.println("Tìm hóa đơn");
        String timhd = Lib.takeStringInput("Mã hóa đơn cần tìm: ");
        for (HoaDon hoaDon : hd) {
            if(hoaDon.getMahd().equals(timhd)){
                String tinhTrang;
                if(hoaDon.getTinhTrang().equals("Đang chờ xác nhận"))
                    tinhTrang = Lib.TEXT_BLUE + hoaDon.getTinhTrang() + Lib.TEXT_RESET;
                else if(hoaDon.getTinhTrang().equals("Đã xác nhận"))
                    tinhTrang = Lib.TEXT_GREEN + hoaDon.getTinhTrang() + Lib.TEXT_RESET;
                else
                    tinhTrang = Lib.TEXT_RED + hoaDon.getTinhTrang() + Lib.TEXT_RESET;
                System.out.println("Tình trạng hóa đơn: " + tinhTrang);
                System.out.printf("+%-20s+%-20s+%-20s+%-35s+%-20s+ \n", Lib.repeatStr("-", 20), Lib.repeatStr("-", 20), Lib.repeatStr("-", 20), Lib.repeatStr("-", 35), Lib.repeatStr("-", 20));
                System.out.printf("|%-20s|%-20s|%-20s|%-35s|%-20s| \n", "Mã hóa đơn" , "Mã nhân viên","Mã khách hàng","Thời gian","Tổng tiền");
                System.out.printf("+%-20s+%-20s+%-20s+%-35s+%-20s+ \n", Lib.repeatStr("-", 20) , Lib.repeatStr("-", 20), Lib.repeatStr("-", 20), Lib.repeatStr("-", 35), Lib.repeatStr("-", 20));
                System.out.printf("|%-20s|%-20s|%-20s|%-35s|%-20s| \n", hoaDon.getMahd() , hoaDon.getManv(),hoaDon.getMakh(),hoaDon.getTimexuat(),hoaDon.getTongtien());
                hoaDon.getDsCTHD().XuatCTHD();
                break;
            }
            else{
            System.out.println("Hóa đơn không có trong danh sách");
            
        }

       }
    }

    public void timHDCuaKhachHang(Nguoi nguoi){
        System.out.println("Tìm hóa đơn");
        String timhd = Lib.takeStringInput("Mã hóa đơn cần tìm: ");
        for (HoaDon hoaDon : hd) {
            if(hoaDon.getMakh().equals(nguoi.getId())){
                if(hoaDon.getMahd().equals(timhd)){
                    String tinhTrang;
                    if(hoaDon.getTinhTrang().equals("Đang chờ xác nhận"))
                        tinhTrang = Lib.TEXT_BLUE + hoaDon.getTinhTrang() + Lib.TEXT_RESET;
                    else if(hoaDon.getTinhTrang().equals("Đã xác nhận"))
                        tinhTrang = Lib.TEXT_GREEN + hoaDon.getTinhTrang() + Lib.TEXT_RESET;
                    else
                        tinhTrang = Lib.TEXT_RED + hoaDon.getTinhTrang() + Lib.TEXT_RESET;
                    System.out.println("Tình trạng hóa đơn: " + tinhTrang);
                    System.out.printf("+%-20s+%-20s+%-20s+%-35s+%-20s+ \n", Lib.repeatStr("-", 20), Lib.repeatStr("-", 20), Lib.repeatStr("-", 20), Lib.repeatStr("-", 35), Lib.repeatStr("-", 20));
                    System.out.printf("|%-20s|%-20s|%-20s|%-35s|%-20s| \n", "Mã hóa đơn" , "Mã nhân viên","Mã khách hàng","Thời gian","Tổng tiền");
                    System.out.printf("+%-20s+%-20s+%-20s+%-35s+%-20s+ \n", Lib.repeatStr("-", 20) , Lib.repeatStr("-", 20), Lib.repeatStr("-", 20), Lib.repeatStr("-", 35), Lib.repeatStr("-", 20));
                    System.out.printf("|%-20s|%-20s|%-20s|%-35s|%-20s| \n", hoaDon.getMahd() , hoaDon.getManv(),hoaDon.getMakh(),hoaDon.getTimexuat(),hoaDon.getTongtien());
                    hoaDon.getDsCTHD().XuatCTHD();
                    break;
                }
                else{
                    System.out.println("Hóa đơn không có trong danh sách");

                }
            }
        }
    }
public void xoaHD(){
       System.out.println("Xóa hóa đơn");
        String xoa = Lib.takeStringInput("Mã hóa đơn cần xóa: ");
        HoaDon findHD = null;
        for (HoaDon hoaDon : hd) {
            if(hoaDon.getMahd().equals(xoa)){
                findHD = hoaDon;
                break;
            }
           
       }
       
        if(findHD != null){
            hd.remove(findHD);
            System.out.println("Hóa đơn đã xóa khỏi danh sách");
        }else{
            System.out.println("Hóa đơn không có trong danh sách");
        }
   }
public void xuatHD(){
        System.out.println("Danh sách hóa đơn");
        for (HoaDon hoaDon : hd) {
            String tinhTrang;
            if(hoaDon.getTinhTrang().equals("Đang chờ xác nhận"))
                tinhTrang = Lib.TEXT_BLUE + hoaDon.getTinhTrang() + Lib.TEXT_RESET;
            else if(hoaDon.getTinhTrang().equals("Đã xác nhận"))
                tinhTrang = Lib.TEXT_GREEN + hoaDon.getTinhTrang() + Lib.TEXT_RESET;
            else
                tinhTrang = Lib.TEXT_RED + hoaDon.getTinhTrang() + Lib.TEXT_RESET;
            System.out.println("Tình trạng hóa đơn: " + tinhTrang);
            System.out.printf("+%-20s+%-20s+%-20s+%-35s+%-20s+ \n", Lib.repeatStr("-", 20), Lib.repeatStr("-", 20), Lib.repeatStr("-", 20), Lib.repeatStr("-", 35), Lib.repeatStr("-", 20));
            System.out.printf("|%-20s|%-20s|%-20s|%-35s|%-20s| \n", "Mã hóa đơn" , "Mã nhân viên","Mã khách hàng","Thời gian","Tổng tiền");
            System.out.printf("+%-20s+%-20s+%-20s+%-35s+%-20s+ \n", Lib.repeatStr("-", 20) , Lib.repeatStr("-", 20), Lib.repeatStr("-", 20), Lib.repeatStr("-", 35), Lib.repeatStr("-", 20));
            System.out.printf("|%-20s|%-20s|%-20s|%-35s|%-20s| \n", hoaDon.getMahd() , hoaDon.getManv(),hoaDon.getMakh(),hoaDon.getTimexuat(),hoaDon.getTongtien());
            hoaDon.getDsCTHD().XuatCTHD();
            System.out.println();
        }
       
    }

    public void xuatHDCuaKhachHang(Nguoi nguoi){
        System.out.println("Danh sách hóa đơn");
        for (HoaDon hoaDon : hd) {
            if(hoaDon.getMakh().equals(nguoi.getId()))
            {
                String tinhTrang;
                if(hoaDon.getTinhTrang().equals("Đang chờ xác nhận"))
                    tinhTrang = Lib.TEXT_BLUE + hoaDon.getTinhTrang() + Lib.TEXT_RESET;
                else if(hoaDon.getTinhTrang().equals("Đã xác nhận"))
                    tinhTrang = Lib.TEXT_GREEN + hoaDon.getTinhTrang() + Lib.TEXT_RESET;
                else
                    tinhTrang = Lib.TEXT_RED + hoaDon.getTinhTrang() + Lib.TEXT_RESET;
                System.out.println("Tình trạng hóa đơn: " + tinhTrang);
                System.out.printf("+%-20s+%-20s+%-20s+%-35s+%-20s+ \n", Lib.repeatStr("-", 20), Lib.repeatStr("-", 20), Lib.repeatStr("-", 20), Lib.repeatStr("-", 35), Lib.repeatStr("-", 20));
                System.out.printf("|%-20s|%-20s|%-20s|%-35s|%-20s| \n", "Mã hóa đơn" , "Mã nhân viên","Mã khách hàng","Thời gian","Tổng tiền");
                System.out.printf("+%-20s+%-20s+%-20s+%-35s+%-20s+ \n", Lib.repeatStr("-", 20) , Lib.repeatStr("-", 20), Lib.repeatStr("-", 20), Lib.repeatStr("-", 35), Lib.repeatStr("-", 20));
                System.out.printf("|%-20s|%-20s|%-20s|%-35s|%-20s| \n", hoaDon.getMahd() , hoaDon.getManv(),hoaDon.getMakh(),hoaDon.getTimexuat(),hoaDon.getTongtien());
                hoaDon.getDsCTHD().XuatCTHD();
                System.out.println();
            }
        }

    }

    public void menuHD(DanhSachDT danhSachDT, Nguoi nguoi, Shop shop){
        boolean out = false;
        do{
            System.out.println("MENU HÓA ĐƠN");
            System.out.println("1. Xuất danh sách hóa đơn");
            System.out.println("2. Thêm hóa đơn");
            System.out.println("3. Sửa thông tin hóa đơn");
            System.out.println("4. Tìm hóa đơn");
            System.out.println("5. Xóa hóa đơn");
            System.out.println("6. Xác nhận hóa đơn");
            System.out.println("7. Thoát menu");
            System.out.print("Lựa chọn: ");
            switch(Lib.takeInputChoice(1, 7)){
                case 1:
                    xuatHD();
                    break;
                case 2:
                    themHD(danhSachDT, nguoi, shop);
                    break;
                case 3:
                    suaHD(danhSachDT, nguoi, shop);
                    break;
                case 4:
                    timHD();
                    break;
                case 5:
                    xoaHD();
                    break;
                case 6:
                    String id = Lib.takeStringInput("Nhập mã hóa đơn cần xác nhận: ");
                    HoaDon hoaDon = hd.stream().filter(x -> x.getMahd().equals(id)).findAny().orElse(null);
                    if(hoaDon == null)
                        Lib.printError("Không tìm thấy");
                    else
                    {
                        hoaDon.setManv(nguoi.getId());
                        hoaDon.setTinhTrang("Đã xác nhận");
                    }

                    break;
                case 7:
                    out = true;
                    break;
                default:
                    System.out.println("Giá trị lựa chọn không đúng");
            }
        }while(!out);
    }

    public void menuHDCuaKhachHang(DanhSachDT danhSachDT, Nguoi nguoi, Shop shop){
        boolean out = false;
        do{
            System.out.println("MENU HÓA ĐƠN");
            System.out.println("1. Xuất danh sách hóa đơn");
            System.out.println("2. Thêm hóa đơn");
            System.out.println("3. Tìm hóa đơn");
            System.out.println("4. Yêu cầu hủy hóa đơn");
            System.out.println("5. Thoát menu");
            System.out.print("Lựa chọn: ");
            switch(Lib.takeInputChoice(1, 5)){
                case 1:
                    xuatHDCuaKhachHang(nguoi);
                    break;
                case 2:
                    themHDCuaKhachHang(danhSachDT, nguoi, shop);
                    break;
                case 3:
                    timHDCuaKhachHang(nguoi);
                    break;
                case 4:
                    String id = Lib.takeStringInput("Nhập mã hóa đơn cần hủy: ");
                    HoaDon hoaDon = hd.stream().filter(x -> x.getMahd().equals(id)).findAny().orElse(null);
                    if(hoaDon == null)
                        Lib.printError("Không tìm thấy");
                    else
                        hoaDon.setTinhTrang("Yêu cầu hủy");
                    break;
                case 5:
                    out = true;
                    break;
            }
        }while(!out);
    }
}