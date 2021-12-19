/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QuanLyBanDienThoai;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class danhsachChitietHD implements Serializable {
    private ArrayList<ChiTietHoaDon> cthd = new ArrayList<>();


    public String nhapMaDienThoai(DanhSachDT danhSachDT)
    {
        do {
            String madienthoai = Lib.takeStringInput("Nhập mã điện thoại:");
            if(danhSachDT.timkiemmaDienThoai(madienthoai) == -1)
            {
                Lib.printError("Mã điện thoại này chưa có trong danh sách điện thoại");
                System.out.println("1. Xem danh sách điện thoại rồi nhập lại");
                System.out.println("2. Dừng thêm");
                switch (Lib.takeInputChoice(1, 2))
                {
                    case 1 -> {danhSachDT.xuat();}
                    case 2 -> {return "stop";}
                }
            }else
                return madienthoai;
        }while (true);

    }
   
      //Nhập chi tiết hóa đơn
   public void NhapCTHD(DanhSachDT danhSachDT){
       System.out.println("Nhập danh sách chi tiết hóa đơn");
        int n = Lib.takeSoLuongCanTao("Nhập số lượng chi tiết hóa đơn: ");
       for (int i = 0; i < n; i++) {
            String madienthoai = nhapMaDienThoai(danhSachDT);
            int sluong = Lib.takeIntegerInput("Nhập số lượng:");
            int gia = danhSachDT.getDt()[danhSachDT.timkiemmaDienThoai(madienthoai)].getgiathanh();
            cthd.add(new ChiTietHoaDon(madienthoai,sluong,gia));
       }
            
       
   }
   public void XuatCTHD(){
       System.out.println("Danh sách chi tiết hóa đơn");
       System.out.printf("+%-20s+%-35s+%-20s+ \n", Lib.repeatStr("-", 20), Lib.repeatStr("-", 35), Lib.repeatStr("-", 20));
       System.out.printf("%-20s|%-35s|%-20s \n","Mã điện thoại","Số lượng","Giá");
       System.out.printf("+%-20s+%-35s+%-20s+ \n", Lib.repeatStr("-", 20), Lib.repeatStr("-", 35), Lib.repeatStr("-", 20));
        for (ChiTietHoaDon chiTietHoaDon : cthd) {
            System.out.printf("%-20s|%-35s|%-20s \n",chiTietHoaDon.getMadienthoai(),chiTietHoaDon.getSluong(),chiTietHoaDon.getGia());
       }                 
       }
   public void themCTHD(DanhSachDT danhSachDT){
       System.out.println("Thêm chi tiết hóa đơn");
       NhapCTHD(danhSachDT);
   }
   public void suaCTHD(){
       System.out.println("Sửa chi tiết hóa đơn");
        Scanner sc = new Scanner (System.in);
       System.out.println("Mã hóa đơn cần xóa: ");
       String o = sc.nextLine();
       for (ChiTietHoaDon chiTietHoaDon : cthd) {
           if(chiTietHoaDon.getMadienthoai().equals(o)){
            String madienthoai= Lib.takeStringInput("Nhập mã điện thoại:");
            int sluong= Lib.takeIntegerInput("Nhập số lượng:");
            int gia= 0;

            chiTietHoaDon.setMadienthoai(madienthoai);
            chiTietHoaDon.setSluong(sluong);
            chiTietHoaDon.setGia(gia);
           }
           
       }
   }
   public void timCTHD(){
       System.out.println("Tìm chi tiết hóa đơn");
        Scanner sc = new Scanner (System.in);
       System.out.print("Mã chi tiết hóa đơn cần tìm: ");
        String tim =sc.nextLine();
        ChiTietHoaDon find = null;
        for (ChiTietHoaDon chiTietHoaDon : cthd) {
            if(chiTietHoaDon.getMadienthoai().equals(tim)){
                find = chiTietHoaDon;
                System.out.printf("%-20s|%-35s|%-20s \n","Mã điện thoại","Số lượng","Giá");
                System.out.printf("%-20s|%-35s|%-20s \n",chiTietHoaDon.getMadienthoai(),chiTietHoaDon.getSluong(),chiTietHoaDon.getGia());
                break;
            }else{
                System.out.println("Chi tiết hóa đơn không có trong danh sách");
            }
        }
        
            
   }
   public void xoaCTHD(){
       System.out.println("Xóa chi tiết hóa đơn");
        Scanner sc = new Scanner (System.in);
       System.out.print("Mã chi tiết hóa đơn cần xóa: ");
        String xoa =sc.nextLine();
        ChiTietHoaDon find = null;
        for (ChiTietHoaDon chiTietHoaDon : cthd) {
            if(chiTietHoaDon.getMadienthoai().equals(xoa)){
                find = chiTietHoaDon;
                break;
            }
           
       }
       
        if(find != null){
            cthd.remove(find);
            System.out.println("Chi tiết hóa đơn đã xóa khỏi danh sách");
        }else{
            System.out.println("Chi tiết hóa đơn không có trong danh sách");
        }
   }
   public int Tien(){
        int sumMoney = 0;
                   for (ChiTietHoaDon ChiTietHoaDonHoaDon : cthd) {
             sumMoney += ChiTietHoaDonHoaDon.getGia()*ChiTietHoaDonHoaDon.getSluong();
         }                                         
                return sumMoney;
    }
   public void menuCTHD(DanhSachDT danhSachDT){
        do{
        System.out.println("MENU CHI TIÊT HÓA ĐƠN");
        System.out.println("1. Xuất danh sách chi tiết hóa đơn");
        System.out.println("2. Thêm chi tiết hóa đơn");
        System.out.println("3. Sửa thông tin chi tiết hóa đơn");
        System.out.println("4. Tìm chi tiết hóa đơn");
        System.out.println("5. Xóa chi tiết hóa đơn");
        System.out.println("6. Thoát menu");
        switch(Lib.takeInputChoice(1, 6)){
          case 1:
               XuatCTHD();
                break;
            case 2:
                themCTHD(danhSachDT);
                break;
            case 3:
                 suaCTHD();
                break;
            case 4:
               timCTHD();
                break;
            case 5:
                 xoaCTHD();
                break;
            case 6:
                System.exit(0);
                break;
            default:
                System.out.println("Giá trị lựa chọn không đúng");
        }
        }while(true);
    }    
   
}