/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QuanLyBanDienThoai;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class DanhSachChiTietHoaDon implements Serializable {
    private ArrayList<ChiTietHoaDon> cthd = new ArrayList<>();

    public ChiTietHoaDon timKiemTheoIDHoaDon(String id)
    {
        return cthd.stream().filter(x -> x.getMadienthoai().equals(id)).findFirst().orElse(null);
    }

    public String nhapMaDienThoai(DanhSachDT danhSachDT)
    {
        do {
            String madienthoai = Lib.takeStringInput("Nhập mã điện thoại:");
            if(danhSachDT.timkiemmaDienThoai(madienthoai) == -1)
            {
                Lib.printError("Không có mã điện thoại này");
            }else
                return madienthoai;
        }while (true);

    }

    //Nhập chi tiết hóa đơn
    public void NhapCTHD(DanhSachDT danhSachDT){
       danhSachDT.xuatDS();
       boolean stop = false;
       do{
           String madienthoai = nhapMaDienThoai(danhSachDT);
           DienThoai dienThoai = danhSachDT.getListDT()[danhSachDT.timkiemmaDienThoai(madienthoai)];
           int sluong = Lib.takeIntegerInput("Nhập số lượng:");
           int gia = dienThoai.getgiathanh();
           if(!dienThoai.mua(sluong))
           {
               Lib.printError("Không thể mua");
               System.out.println("Trong kho chỉ còn " + dienThoai.getSoLuong());
               continue;
           }

           boolean daCo = false;
           for(ChiTietHoaDon chiTietHoaDon : cthd)
           {
               if(chiTietHoaDon.getMadienthoai().equals(madienthoai))
               {
                   chiTietHoaDon.setSluong(chiTietHoaDon.getSluong() + sluong);
                   daCo = true;
               }
           }

           if(!daCo)
               cthd.add(new ChiTietHoaDon(madienthoai,sluong,gia));

           System.out.println("1. Mua tiếp");
           System.out.println("0. Đặt hàng");
           if(Lib.takeInputChoice(0, 1) == 0)
               stop = true;


       }while(!stop);
   }
    //┘ └ ┌ ┐ ├ ┤ ┴ ┬ ┼ │ ─
   public void XuatCTHD()
   {
        int length = 60 - "Chi tiết hóa đơn".length();
        System.out.printf("│%-50s%-65s│ \n", Lib.repeatStr(" ", 50), "Chi tiết hóa đơn" + Lib.repeatStr(" ", length));
        System.out.printf("│%-20s┌%-20s┬%-20s┬%-25s─┐%-25s│ \n", Lib.repeatStr(" ", 20), Lib.repeatStr("─", 20), Lib.repeatStr("─", 20), Lib.repeatStr("─", 25), Lib.repeatStr(" ", 25));
        System.out.printf("│%-20s│%-20s│%-20s│%-25s │%-25s│ \n", Lib.repeatStr(" ", 20), "Mã điện thoại","Số lượng","Giá", Lib.repeatStr(" ", 25));
        System.out.printf("│%-20s├%-20s┼%-20s┼%-25s─┤%-25s│ \n", Lib.repeatStr(" ", 20), Lib.repeatStr("─", 20), Lib.repeatStr("─", 20), Lib.repeatStr("─", 25), Lib.repeatStr(" ", 25));
        for (ChiTietHoaDon chiTietHoaDon : cthd) {
            System.out.printf("│%-20s│%-20s│%-20s│%-25s │%-25s│ \n", Lib.repeatStr(" ", 20), chiTietHoaDon.getMadienthoai(),chiTietHoaDon.getSluong(),chiTietHoaDon.getGia(), Lib.repeatStr(" ", 25));
        }
    }

    public void delete(ChiTietHoaDon chiTietHoaDon)
    {
        cthd.remove(chiTietHoaDon);
    }

   public int tinhTongTien(){
        int sumMoney = 0;
        for (ChiTietHoaDon ChiTietHoaDonHoaDon : cthd)
        {
            sumMoney += ChiTietHoaDonHoaDon.getGia()*ChiTietHoaDonHoaDon.getSluong();
        }
        return sumMoney;
    }

    public ArrayList<ChiTietHoaDon> getCthd() {
        return cthd;
    }

    public void setCthd(ArrayList<ChiTietHoaDon> cthd) {
        this.cthd = cthd;
    }
   
}