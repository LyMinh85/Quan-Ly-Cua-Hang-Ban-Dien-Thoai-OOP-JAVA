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
public class danhsachcungcap implements Serializable {
    private ArrayList<NhaCungCap> list = new ArrayList<>();
    // Nhập danh sách nhà cung cấp
    public void themncc(){
        System.out.println("Nhập danh sách nhà cung cấp");
        int n = Lib.takeSoLuongCanTao("Nhập số lượng nhà cung cấp: ");
        for (int i = 0; i < n; i++) {
                String tencc = Lib.takeStringInput("Tên nhà cung cấp:");        
                String diachi = Lib.takeStringInput("Địa chỉ: ");
                int sdt = Lib.takeIntegerInput("Số diện thoại liên lạc: ");
                String email = Lib.takeStringInput("Email: ");
                String macc = nhapIDNhaCungCap();
        list.add(new NhaCungCap(tencc,diachi,sdt,email,macc));            
        }
      
        
       
        
}   
    //Xuất danh sách nhà cung cấp
    public void xuat(){
        System.out.println("Danh sánh nhà cung cấp");
        for(NhaCungCap nhaCungCap : list){
            System.out.printf("+%-20s+%-20s+%-20s+%-20s+%-20s \n", Lib.repeatStr("-", 20), Lib.repeatStr("-", 20), Lib.repeatStr("-", 20), Lib.repeatStr("-", 20), Lib.repeatStr("-", 20));
            System.out.printf("|%-20s|%-20s|%-20s|%-20s|%-20s \n","Mã nhà cung cấp","Tên nhà cung cấp","Địa chỉ","Số điện thoại","Email");
            System.out.printf("+%-20s+%-20s+%-20s+%-20s+%-20s \n", Lib.repeatStr("-", 20), Lib.repeatStr("-", 20), Lib.repeatStr("-", 20), Lib.repeatStr("-", 20), Lib.repeatStr("-", 20));
            System.out.printf("|%-20s|%-20s|%-20s|%-20s|%-20s \n",nhaCungCap.getMacc(),nhaCungCap.getTencc(),nhaCungCap.getDiachi(),nhaCungCap.getSdt(),nhaCungCap.getEmail());
           
           
        }
    }
    //Sửa thông tin nhà cung cấp
    public void suaNcc(){
        String up = Lib.takeStringInput("Mã nhà cung cấp cần sửa: ");
        for (NhaCungCap nhaCungCap : list){
            if(nhaCungCap.getMacc().equals(up)){
                String tencc = Lib.takeStringInput("Tên nhà cung cấp:");        
                String diachi = Lib.takeStringInput("Địa chỉ: ");
                int sdt = Lib.takeIntegerInput("Số diện thoại liên lạc: ");
                String email = Lib.takeStringInput("Email: ");
                String macc = nhapIDNhaCungCap();
                
               nhaCungCap.setTencc(tencc);
               nhaCungCap.setDiachi(diachi);
               nhaCungCap.setSdt(sdt);
               nhaCungCap.setEmail(email);
               nhaCungCap.setMacc(macc);
            }
        }

            }

    public NhaCungCap timKiemTheoID(String id)
    {
        for(NhaCungCap nhaCungCap : list)
            if(nhaCungCap.getMacc().equals(id))
                return nhaCungCap;
        return null;
    }

    public String nhapIDNhaCungCap()
    {
        String idncc;
        do {
            idncc = Lib.takeStringInput("Nhập mã nhà cung cấp: ");
            if(timKiemTheoID(idncc) != null)
                Lib.printError("Mã này đã có vui lòng nhập mã khác");
            else
                return idncc;
        }while (true);
    }
    
    //Tìm nhà cung cấp
    public void timNcc(){
        String timkiem = Lib.takeStringInput("Mã nhà cung cấp cần tìm: ");
        NhaCungCap find = null;
        
        for (NhaCungCap nhaCungCap : list){
            if(nhaCungCap.getMacc().equals(timkiem)){
                find = nhaCungCap;
                System.out.printf("%-20s|%-20s|%-20s|%-20s|%-20s \n","Mã nhà cung cấp","Tên nhà cung cấp","Địa chỉ","Số điện thoại","Email");
                System.out.printf("%-20s%-20s|%-20s|%-20s|%-20s \n",nhaCungCap.getMacc(),nhaCungCap.getTencc(),nhaCungCap.getDiachi(),nhaCungCap.getSdt(),nhaCungCap.getEmail());
                break;
            }else{
                System.out.println("Nhà cung cấp không có trong danh sách ");
            }
        }
    }
    //Xóa nhà cung cấp
    public void xoaNcc(){
        String xoacc = Lib.takeStringInput("Tên nhà cung cấp cần xóa: ");
        NhaCungCap find = null;
        
        for (NhaCungCap nhaCungCap : list){
            if(nhaCungCap.getMacc().equals(xoacc)){
                find = nhaCungCap;
                break;
            }
        }
        if(find != null){
            list.remove(find);
            System.out.println("Nhà cung cấp đã xóa khỏi danh sách ");
        }else{
            System.out.println("Nhà cung cấp không có trong danh sách ");
        }
    }
    public void menuNhacc(){
        boolean out = false;
        do{
        System.out.println("--------------------------------------------------------");    
        System.out.println("MENU NHÀ CUNG CẤP");
        System.out.println("1. Xuất danh sách nhà cung cấp");
        System.out.println("2. Thêm nhà cung cấp mới");
        System.out.println("3. Sửa thông tin nhà cung cấp");
        System.out.println("4. Tìm thông tin nhà cung cấp");
        System.out.println("5. Xóa nhà cung cấp");
        System.out.println("6. Thoát menu");
        System.out.print("Lựa chọn: ");
        switch(Lib.takeInputChoice(1, 6)){
            case 1:
               xuat();
                break;
            case 2:
                themncc();
                break;
            case 3:
                 suaNcc();
                break;
            case 4:
               timNcc();
                break;
            case 5:
                 xoaNcc();
                break;
            case 6:
                out = true;
                break;
            default:
                System.out.println("Giá trị lựa chọn không đúng");
        }
        }while(!out);
    }
} 
