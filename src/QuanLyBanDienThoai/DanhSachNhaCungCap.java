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
public class DanhSachNhaCungCap implements Serializable, DanhSach {
    private ArrayList<NhaCungCap> listNhaCungCap = new ArrayList<>();
    private int count = 0;

    //Khởi tạo các nhà cung cấp mặc định
    public DanhSachNhaCungCap()
    {
        listNhaCungCap.add(new NhaCungCap("Meow","TPHCM","9045495", "Meow@email", getID()));
        listNhaCungCap.add(new NhaCungCap("Fish","Hà nội","549844", "Fish@email", getID()));
    }

    //Lấy mã nhà cung cấp tiếp theo
    public String getID()
    {
        count++;
        Integer a = count;
        String str = a.toString();
        while(str.length() != 3)
            str = "0" + str;
        str = "NCC" + str;
        return str;
    }

    // Nhập danh sách nhà cung cấp
    public void themncc(){
        System.out.println(Lib.toBlueText("Nhập nhà cung cấp mới"));
        String tencc = Lib.takeStringInput("Tên nhà cung cấp:");
        String diachi = Lib.takeStringInput("Địa chỉ: ");
        String sdt = Lib.takeStringInput("Số diện thoại liên lạc: ");
        String email = Lib.takeStringInput("Email: ");
        String macc = getID();

        listNhaCungCap.add(new NhaCungCap(tencc,diachi,sdt,email,macc));
    }

    public void xuatTieuDe()
    {
        System.out.printf("┌%-20s┬%-20s┬%-20s┬%-20s┬%-20s┐ \n", Lib.repeatStr("─", 20), Lib.repeatStr("─", 20), Lib.repeatStr("─", 20), Lib.repeatStr("─", 20), Lib.repeatStr("─", 20));
        System.out.printf("│%-20s│%-20s│%-20s│%-20s│%-20s│ \n","Mã nhà cung cấp","Tên nhà cung cấp","Địa chỉ","Số điện thoại","Email");
        System.out.printf("├%-20s┼%-20s┼%-20s┼%-20s┼%-20s┤ \n", Lib.repeatStr("─", 20), Lib.repeatStr("─", 20), Lib.repeatStr("─", 20), Lib.repeatStr("─", 20), Lib.repeatStr("─", 20));
    }

    //Xuất danh sách nhà cung cấp
    public void xuatDS(){
        System.out.println(Lib.toBlueText("Danh sánh nhà cung cấp"));
        xuatTieuDe();
        for(NhaCungCap nhaCungCap : listNhaCungCap){
            nhaCungCap.xuatThongTin();
        }
    }

    //Sửa thông tin nhà cung cấp
    public void suaNcc(){
        String id = Lib.takeStringInput("Mã nhà cung cấp cần sửa: ");
        NhaCungCap nhaCungCap = timKiemTheoID(id);
        if(nhaCungCap != null)
        {
            boolean out = false;
            do {
                xuatTieuDe();
                nhaCungCap.xuatThongTin();
                System.out.println(Lib.toBlueText("Sửa thông tin nhà cung cấp"));
                System.out.println("1. Sửa tên nhà cung cấp");
                System.out.println("2. Sửa địa chỉ");
                System.out.println("3. Sửa số điện thoại");
                System.out.println("4. Sửa email");
                System.out.println("0. Thoát");
                switch (Lib.takeInputChoice(0, 4))
                {
                    case 1 -> nhaCungCap.setTen(Lib.takeStringInput("Nhập tên mới: "));
                    case 2 -> nhaCungCap.setDiaChi(Lib.takeStringInput("Nhập địa chỉ mới: "));
                    case 3 -> nhaCungCap.setSdt(Lib.takeStringInput("Nhập điện thoại mới: "));
                    case 4 -> nhaCungCap.setEmail(Lib.takeStringInput("Nhập email mới: "));
                    case 0 -> out = true;
                }
                if(!out)
                    Lib.clearScreen();
            }while (!out);
        }
    }

    public NhaCungCap timKiemTheoID(String id)
    {
        for(NhaCungCap nhaCungCap : listNhaCungCap)
            if(nhaCungCap.getMaNCC().equals(id))
                return nhaCungCap;
        return null;
    }

    //Xóa nhà cung cấp
    public void xoaNcc(){
        String id = Lib.takeStringInput("Tên nhà cung cấp cần xóa: ");
        NhaCungCap find = timKiemTheoID(id);

        if(find != null){
            listNhaCungCap.remove(find);
            Lib.printMessage("Nhà cung cấp đã xóa khỏi danh sách");
        }else{
            Lib.printError("Nhà cung cấp không có trong danh sách");
        }
    }

    public void timKiem()
    {
        String tuKhoa = Lib.takeStringInput("Nhập từ khóa cần tìm: ");
        ArrayList<NhaCungCap> listFilter = new ArrayList<>();
        for(NhaCungCap nhaCungCap : listNhaCungCap)
        {
            if(
                    nhaCungCap.getMaNCC().toLowerCase(Locale.ROOT).contains(tuKhoa.toLowerCase(Locale.ROOT)) ||
                    nhaCungCap.getDiaChi().toLowerCase(Locale.ROOT).contains(tuKhoa.toLowerCase(Locale.ROOT)) ||
                    nhaCungCap.getEmail().toLowerCase(Locale.ROOT).contains(tuKhoa.toLowerCase(Locale.ROOT)) ||
                    nhaCungCap.getTen().toLowerCase(Locale.ROOT).contains(tuKhoa.toLowerCase(Locale.ROOT)) ||
                    nhaCungCap.getSdt().toLowerCase(Locale.ROOT).contains(tuKhoa.toLowerCase(Locale.ROOT))
            )
            {
                listFilter.add(nhaCungCap);
            }
        }

        System.out.println(Lib.toBlueText("Kết quả tìm kiếm theo từ khóa: ")  + Lib.toGreenText(tuKhoa));
        if(listFilter.isEmpty())
            Lib.printError("Không tìm được từ khóa này trong danh sách");
        else
        {
            xuatTieuDe();
            for(NhaCungCap nhaCungCap : listFilter)
                nhaCungCap.xuatThongTin();
        }
    }

    public void menuNhacc(){
        boolean out = false;
        do{
            xuatDS();
            System.out.println(Lib.toBlueText("MENU NHÀ CUNG CẤP"));
            System.out.println("1. Thêm nhà cung cấp mới");
            System.out.println("2. Sửa thông tin nhà cung cấp");
            System.out.println("3. Xóa nhà cung cấp");
            System.out.println("4. Tìm kiếm trong bảng");
            System.out.println("0. Thoát menu");
            switch (Lib.takeInputChoice(0, 4)) {
                case 1 -> themncc();
                case 2 -> suaNcc();
                case 3 -> xoaNcc();
                case 4 -> timKiem();
                case 0 -> out = true;
            }
            if(!out)
                Lib.clearScreen();
        }while(!out);
    }

    public ArrayList<NhaCungCap> getListNhaCungCap() {
        return listNhaCungCap;
    }

    public void setListNhaCungCap(ArrayList<NhaCungCap> listNhaCungCap) {
        this.listNhaCungCap = listNhaCungCap;
    }
}
