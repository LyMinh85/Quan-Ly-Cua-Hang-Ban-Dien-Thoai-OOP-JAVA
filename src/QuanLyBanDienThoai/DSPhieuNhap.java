package QuanLyBanDienThoai;

import java.io.*;
import java.util.ArrayList;

public class DSPhieuNhap implements Serializable{
    private ArrayList<PhieuNhap> dsPhieuNhap;
    private int count = 0;

    public DSPhieuNhap(DanhSachDT danhSachDT) {
        dsPhieuNhap = new ArrayList<>();
        DienThoai dt1 = danhSachDT.getListDT()[0];
        DienThoai dt2 = danhSachDT.getListDT()[1];
        DienThoai dt3 = danhSachDT.getListDT()[2];

        DSChiTietPhieu ct1 = new DSChiTietPhieu();
        ct1.add(new ChiTietPhieu(dt1.getmaDienThoai(), 5, dt1.getgiathanh()));
        dsPhieuNhap.add(new PhieuNhap(getID(), "23/8/2020", "NCC001", "vu", ct1, ct1.tinhTongTien()));

        DSChiTietPhieu ct2 = new DSChiTietPhieu();
        ct2.add(new ChiTietPhieu(dt2.getmaDienThoai(), 6, dt2.getgiathanh()));
        ct2.add(new ChiTietPhieu(dt2.getmaDienThoai(), 9, dt3.getgiathanh()));
        dsPhieuNhap.add(new PhieuNhap(getID(), "8/8/2021", "NCC001", "vu", ct2, ct2.tinhTongTien()));
    }

    public String getID() {
        count++;
        int a = count;
        String str = Integer.toString(a);
        while(str.length() != 3)
            str = "0" + str;
        str = "PN" + str;
        return str;
    }

    //┘ └ ┌ ┐ ├ ┤ ┴ ┬ ┼ │ ─
    public void xuatTuaDePhieu() {
        System.out.printf("┌%-16s┬%-16s┬%-16s┬%-16s┬%-16s┐\n",
                Lib.repeatStr("─", 16), Lib.repeatStr("─", 16),
                Lib.repeatStr("─", 16), Lib.repeatStr("─", 16),
                Lib.repeatStr("─", 16));
        System.out.printf("│%-16s│%-16s│%-16s│%-16s│%-16s│\n", "ID phiếu", "Date", "Tổng tiền", "ID nhân viên", "ID nhà cung cấp");
        System.out.printf("├%-16s┼%-16s┼%-16s┼%-16s┼%-16s┤\n",
                Lib.repeatStr("─", 16), Lib.repeatStr("─", 16),
                Lib.repeatStr("─", 16), Lib.repeatStr("─", 16),
                Lib.repeatStr("─", 16));
    }

    public void xemDSPhieu() {
        if (!dsPhieuNhap.isEmpty()) {
            for (PhieuNhap phieuNhap : dsPhieuNhap) {
                phieuNhap.inPhieu();
            }
        } else {
            Lib.printError("Danh sách phiếu đang rỗng");
        }
    }

    public void xemChiTietPhieu(){
        if(dsPhieuNhap.isEmpty())
            Lib.printError("Danh sách đang rỗng");
        else
        {
            String id = Lib.takeStringInput("Nhập ID phiếu cần xem chi tiết: ");
            Phieu obj = timKiemTheoIDPhieu(id);
            if(obj == null)
            {
                Lib.printError("Không có ID đó trong danh sách");
                return;
            }
            obj.xemChiTietPhieu();
        }
    }

    public Phieu timKiemTheoIDPhieu(String id) {
        return dsPhieuNhap.stream().filter(x -> x.getIDPhieu().equals(id)).findFirst().orElse(null);
    }

    public void xoaPhieu() {
        if (dsPhieuNhap.isEmpty()) {
            Lib.printError("Danh sách phiếu đang rỗng");
            return;
        }

        String idPhieu = Lib.takeStringInput("Nhập vào id phiếu cần xóa: ");
        PhieuNhap phieu = dsPhieuNhap.stream()
                .filter(x -> x.getIDPhieu().equals(idPhieu))
                .findAny()
                .orElse(null);
        if (phieu == null)
            Lib.printError("Không có mã phiếu đó trong danh sách");
        else {
            dsPhieuNhap.remove(phieu);
            Lib.printMessage("Xóa phiếu thành công");
        }
    }

    private void timKiemTrongBang(){
        String tuKhoa = Lib.takeStringInput("Nhập từ khóa cần tìm: ");
        ArrayList<PhieuNhap> filter = new ArrayList<>();
        int tongTien = 0;

        for (PhieuNhap phieuNhap : dsPhieuNhap)
        {
            try{tongTien = Integer.parseInt(tuKhoa);}
            catch (NumberFormatException ignored){}

            boolean check = Lib.subStrInStrIgnoreCase(phieuNhap.getIDNhaCungCap(), tuKhoa) ||
                            Lib.subStrInStrIgnoreCase(phieuNhap.getDate(), tuKhoa) ||
                            Lib.subStrInStrIgnoreCase(phieuNhap.getIDNhanVien(), tuKhoa) ||
                            phieuNhap.getSumMoney() == tongTien;
            if(check)
                filter.add(phieuNhap);
        }

        System.out.println(Lib.toBlueText("Kết quả tìm kiếm theo từ khóa: ")  + Lib.toGreenText(tuKhoa));
        if(filter.isEmpty())
            Lib.printError("Không tìm thấy");
        else
        {
            xuatTuaDePhieu();
            for(PhieuNhap phieuNhap : filter)
                phieuNhap.inPhieu();
        }
    }

    public String nhapNCC(DanhSachNhaCungCap danhSachNhaCungCap){
        String idNCC;
        while(true)
        {
            danhSachNhaCungCap.xuatDS();
            idNCC = Lib.takeStringInput("Nhập mã nhà cung cấp: ");
            if(danhSachNhaCungCap.timKiemTheoID(idNCC) != null)
                return idNCC;
            else
                Lib.printError("Không có nhà cung cấp này");
        }
    }

    public void add(Nguoi nguoi, DanhSachDT danhSachDT, DanhSachNhaCungCap danhSachNhaCungCap){
        String idPhieu = getID();
        String idncc = nhapNCC(danhSachNhaCungCap);

        DSChiTietPhieu dsChiTietPhieu = new DSChiTietPhieu();
        boolean finish = false;
        do {
            danhSachDT.xuat();
            System.out.println("1. Nhập điện thoại");
            System.out.println("2. Xác nhận nhập hàng");
            switch (Lib.takeInputChoice(1, 2))
            {
                case 1 -> {
                    String iddt = Lib.takeStringInput("Nhập mã điện thoại: ");
                    int index = danhSachDT.timkiemmaDienThoai(iddt);
                    if(index != -1)
                    {
                        int sl = Lib.takeIntegerInput("Nhập số lượng: ");
                        ChiTietPhieu chiTietPhieu = dsChiTietPhieu.search(iddt);
                        if(chiTietPhieu != null)
                            chiTietPhieu.setSoLuong(chiTietPhieu.getSoLuong() + sl);
                        else
                            dsChiTietPhieu.add(new ChiTietPhieu(iddt, sl, danhSachDT.getListDT()[index].getgiathanh()));
                    }
                    else
                        Lib.printError("Không có điện thoại này");
                }
                case 2 -> finish = true;
            }
            if(!finish)
                Lib.clearScreen();
        }while (!finish);

        dsPhieuNhap.add(new PhieuNhap(idPhieu, Lib.getDateNow(), idncc, nguoi.getId(), dsChiTietPhieu, dsChiTietPhieu.tinhTongTien()));

        for (int i = 0; i < dsChiTietPhieu.getSize(); i++)
        {
            ChiTietPhieu chiTietPhieu = dsChiTietPhieu.getChiTietPhieus()[i];
            DienThoai dienThoai = danhSachDT.getListDT()[danhSachDT.timkiemmaDienThoai(chiTietPhieu.getIDDienThoai())];
            dienThoai.setSoLuong(dienThoai.getSoLuong() + chiTietPhieu.getSoLuong());
        }
    }

    public void menu(DanhSachDT danhSachDT, Nguoi nguoi, Shop shop, DanhSachNhaCungCap danhSachNhaCungCap)
    {
        boolean thoatXemDSPhieu = false;
        while(true)
        {
            xuatTuaDePhieu();
            xemDSPhieu();
            System.out.println("1. Xem chi tiết phiếu nhập");
            System.out.println("2. Thêm phiếu nhập");
            System.out.println("3. Xóa phiếu nhập");
            System.out.println("4. Tìm kiếm trong bảng");
            System.out.println("0. Quay lại");
            switch (Lib.takeInputChoice(0,4))
            {
                case 1 -> xemChiTietPhieu();
                case 2 -> add(nguoi, danhSachDT, danhSachNhaCungCap);
                case 3 -> xoaPhieu();
                case 4 -> timKiemTrongBang();
                case 0 -> thoatXemDSPhieu = true;
            }
            if (thoatXemDSPhieu)
                break;
            Lib.clearScreen();
        }
    }



}
