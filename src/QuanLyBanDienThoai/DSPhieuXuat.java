package QuanLyBanDienThoai;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public class DSPhieuXuat implements Serializable{
    private ArrayList<PhieuXuat> dsPhieuXuat;
    private int count = 0;

    public DSPhieuXuat(Nguoi nguoi, DanhSachHoaDon danhSachHoaDon)
    {
        dsPhieuXuat = new ArrayList<>();
        for (HoaDon hoaDon : danhSachHoaDon.getHoaDonArrayList())
        {
            if(hoaDon.getTinhTrang().equals("Đã xác nhận"))
            {
                DSChiTietPhieu dsctp = new DSChiTietPhieu();
                DanhSachChiTietHoaDon dscthd = hoaDon.getDsCTHD();
                for (ChiTietHoaDon cthd : dscthd.getCthd())
                {
                    ChiTietPhieu obj = new ChiTietPhieu(cthd.getMadienthoai(), cthd.getSluong(), cthd.getGia());
                    dsctp.add(obj);
                }
                PhieuXuat phieuXuat = new PhieuXuat(getID(), hoaDon.getTimexuat(), hoaDon.getManv(),
                        nguoi.getId(), dsctp, dsctp.tinhTongTien(), hoaDon.getMahd());
                dsPhieuXuat.add(phieuXuat);
                phieuXuat.xuatHang(nguoi, danhSachHoaDon);
            }
        }

        for (HoaDon hoaDon : danhSachHoaDon.getHoaDonArrayList())
            if(hoaDon.getTinhTrang().equals("Đã xuất kho"))
                hoaDon.setTinhTrang("Đã nhận hàng");
   }

    public String getID() {
        count++;
        int a = count;
        String str = Integer.toString(a);
        while(str.length() != 3)
            str = "0" + str;
        str = "PX" + str;
        return str;
    }

    //┘ └ ┌ ┐ ├ ┤ ┴ ┬ ┼ │ ─
    public void xuatTuaDePhieu() {
        System.out.println(Lib.toBlueText("Danh sách phiếu xuất"));
        System.out.printf("┌%-8s┬%-8s┬%-12s┬%-16s┬%-25s┬%-25s┬%-16s┐\n", Lib.repeatStr("─", 8),
                Lib.repeatStr("─", 8), Lib.repeatStr("─", 12),
                Lib.repeatStr("─", 16), Lib.repeatStr("─", 25),
                Lib.repeatStr("─", 25), Lib.repeatStr("─", 16));
        System.out.printf("│%-8s│%-8s│%-12s│%-16s│%-25s│%-25s│%-16s│\n", "ID phiếu", "Hóa đơn", "Date", "Tổng tiền",
                "ID nhân viên bán hàng", "ID nhân viên thủ kho", "Tình trạng");
        System.out.printf("├%-8s┼%-8s┼%-12s┼%-16s┼%-25s┼%-25s┼%-16s┤\n", Lib.repeatStr("─", 8),
                Lib.repeatStr("─", 8), Lib.repeatStr("─", 12),
                Lib.repeatStr("─", 16), Lib.repeatStr("─", 25),
                Lib.repeatStr("─", 25), Lib.repeatStr("─", 16));
    }

    public void xemDSPhieu() {
        if (!dsPhieuXuat.isEmpty()) {
            for (PhieuXuat phieuXuat : dsPhieuXuat) {
                phieuXuat.inPhieu();
            }
        } else {
            Lib.printError("Danh sách phiếu đang rỗng");
        }
    }

    public Phieu timKiemTheoIDPhieu(String id) {
        return dsPhieuXuat.stream().filter(x -> x.getIDPhieu().equals(id)).findFirst().orElse(null);
    }

    public void xoaPhieu() {
        if (dsPhieuXuat.isEmpty()) {
            Lib.printError("Danh sách phiếu đang rỗng");
            return;
        }
        String idPhieu = Lib.takeStringInput("Nhập vào id phiếu cần xóa: ");
        PhieuXuat phieu = dsPhieuXuat.stream()
                .filter(x -> x.IDPhieu.equals(idPhieu))
                .findAny()
                .orElse(null);
        if (phieu == null)
            Lib.printError("Không có mã phiếu đó trong danh sách");
        else {
            dsPhieuXuat.remove(phieu);
            Lib.printMessage("Xóa phiếu thành công");
        }
    }

    private void timKiemTrongBang(){
        String tuKhoa = Lib.takeStringInput("Nhập từ khóa cần tìm: ");
        ArrayList<PhieuXuat> filter = new ArrayList<>();
        int tongTien = 0;

        for (PhieuXuat phieuXuat : dsPhieuXuat)
        {
            try{tongTien = Integer.parseInt(tuKhoa);}
            catch (NumberFormatException ignored){}

            boolean check = Lib.subStrInStrIgnoreCase(phieuXuat.getDate(), tuKhoa) ||
                    Lib.subStrInStrIgnoreCase(phieuXuat.getIDNhanVien(), tuKhoa) ||
                    Lib.subStrInStrIgnoreCase(phieuXuat.getIDNhanVienThuKho(), tuKhoa) ||
                    Lib.subStrInStrIgnoreCase(phieuXuat.getTinhTrang(), tuKhoa) ||
                    phieuXuat.getSumMoney() == tongTien;
            if(check)
                filter.add(phieuXuat);
        }

        System.out.println(Lib.toBlueText("Kết quả tìm kiếm theo từ khóa: ")  + Lib.toGreenText(tuKhoa));
        if(filter.isEmpty())
            Lib.printError("Không tìm thấy");
        else
        {
            xuatTuaDePhieu();
            for(PhieuXuat phieuXuat : filter)
                phieuXuat.inPhieu();
        }
    }

    public void add(HoaDon hoaDon){
        DSChiTietPhieu dsctp = new DSChiTietPhieu();
        DanhSachChiTietHoaDon dscthd = hoaDon.getDsCTHD();
        for (ChiTietHoaDon cthd : dscthd.getCthd())
        {
            ChiTietPhieu obj = new ChiTietPhieu(cthd.getMadienthoai(), cthd.getSluong(), cthd.getGia());
            dsctp.add(obj);
        }
        dsPhieuXuat.add(new PhieuXuat(getID(), hoaDon.getTimexuat(), hoaDon.getManv(),
                "", dsctp, dsctp.tinhTongTien(), hoaDon.getMahd()));
    }

    public void xemChiTietPhieu(Nguoi nguoi, DanhSachHoaDon danhSachHoaDon){
        if(dsPhieuXuat.isEmpty())
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
            boolean out = false;
            do
            {
                obj.xemChiTietPhieu();
                System.out.println("1. Xác nhận xuất kho");
                System.out.println("0. Thoát");
                switch (Lib.takeInputChoice(0, 1))
                {
                    case 1 -> ((PhieuXuat) obj).xuatHang(nguoi, danhSachHoaDon);
                    case 0 -> out = true;
                }
                if(!out)
                    Lib.clearScreen();
            }while(!out);

        }
    }

    public void menu(Nguoi nguoi, DanhSachHoaDon danhSachHoaDon)
    {
        boolean thoatXemDSPhieu = false;
        while(true)
        {
            xuatTuaDePhieu();
            xemDSPhieu();
            System.out.println(Lib.toBlueText("Menu phiếu xuất"));
            System.out.println("1. Xem chi tiết phiếu xuất");
            System.out.println("2. Xóa phiếu xuất");
            System.out.println("3. Tìm kiếm trong bảng");
            System.out.println("0. Quay lại");
            switch (Lib.takeInputChoice(0,3))
            {
                case 1 -> xemChiTietPhieu(nguoi, danhSachHoaDon);
                case 2 -> xoaPhieu();
                case 3 -> timKiemTrongBang();
                case 0 -> thoatXemDSPhieu = true;
            }
            if (thoatXemDSPhieu)
                break;
            Lib.clearScreen();
        }

    }

}
