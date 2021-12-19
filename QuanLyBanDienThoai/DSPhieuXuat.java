package QuanLyBanDienThoai;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public class DSPhieuXuat implements Serializable, DSPhieu{
    private ArrayList<PhieuXuat> dsPhieuXuat;

    public DSPhieuXuat()
    {
        dsPhieuXuat = new ArrayList<>();
    }

    public void xuatTuaDePhieu() {
        System.out.printf("+%-16s+%-16s+%-16s+%-16s+%-16s+\n",
                Lib.repeatStr("-", 16), Lib.repeatStr("-", 16),
                Lib.repeatStr("-", 16), Lib.repeatStr("-", 16),
                Lib.repeatStr("-", 16), Lib.repeatStr("-", 16));
        System.out.printf("|%-16s|%-16s|%-16s|%-16s|%-16s|\n", "ID phiếu", "Date", "Tổng tiền", "ID nhân viên", "Loại phiếu");
        System.out.printf("+%-16s+%-16s+%-16s+%-16s+%-16s+\n",
                Lib.repeatStr("-", 16), Lib.repeatStr("-", 16),
                Lib.repeatStr("-", 16), Lib.repeatStr("-", 16),
                Lib.repeatStr("-", 16), Lib.repeatStr("-", 16));
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

    public void xemDSPhieu(ArrayList<Phieu> dsPhieuXuat) {
        if (!dsPhieuXuat.isEmpty()) {
            for (Phieu phieu : dsPhieuXuat) {
                phieu.inPhieu();
            }
        } else {
            Lib.printError("Danh sách phiếu đang rỗng");
        }
    }

    public Phieu timKiemTheoIDPhieu(String id) {
        return dsPhieuXuat.stream().filter(x -> x.getIDPhieu().equals(id)).findFirst().orElse(null);
    }

    public String nhapIDPhieu()
    {
        while(true)
        {
            String id = Lib.takeStringInput("Nhập ID phiếu: ");
            if(timKiemTheoIDPhieu(id) == null)
                return id;
            Lib.printError("ID phiếu này đã tồn tại vui lòng chọn ID khác");
        }
    }

    public boolean taoPhieu(DanhSachDT danhSachDT, Nguoi nguoi, danhsachcungcap dsncc) {
        String id = nhapIDPhieu();
        String date = Lib.getDateNow();
        String idnv = nguoi.getId();

        //Nhập chi tiết phiếu
        DSChiTietPhieu dsChiTietPhieu = new DSChiTietPhieu();
        if(!dsChiTietPhieu.taoDSChiTietPhieu(danhSachDT))
            return false;

        int sumMoney = dsChiTietPhieu.tinhTongTien();
        PhieuXuat px = new PhieuXuat(id, date, idnv, dsChiTietPhieu, sumMoney);
        dsPhieuXuat.add(px);
        Lib.printMessage("Tạo phiếu thành công");
        return true;
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

    public ArrayList<Phieu> timKiemTheoNgay(String date) {
        ArrayList<Phieu> list = dsPhieuXuat
                .stream()
                .filter(x -> x.getDate().equals(date))
                .collect(Collectors.toCollection(ArrayList::new));
        if (list.toString().equals("[]"))
            return null;
        return list;
    }

    public ArrayList<Phieu> timkiemTheoTongTien(int tongtien) {
        ArrayList<Phieu> list = dsPhieuXuat
                .stream()
                .filter(x -> x.getSumMoney() == tongtien)
                .collect(Collectors.toCollection(ArrayList::new));
        if (list.toString().equals("[]"))
            return null;
        return list;
    }

    public ArrayList<Phieu> timKiemTheoMaNhanVien(String idnv)
    {
        ArrayList<Phieu> list = dsPhieuXuat
                .stream()
                .filter(x -> x.getIDNhanVien().equals(idnv))
                .collect(Collectors.toCollection(ArrayList::new));
        if (list.toString().equals("[]"))
            return null;
        return list;
    }

    @Override
    public void sapXepTheoTongTien() {
        dsPhieuXuat = (ArrayList<PhieuXuat>) dsPhieuXuat
                .stream()
                .sorted(Comparator.comparingInt(Phieu::getSumMoney))
                .collect(Collectors.toList());
    }

    public void menuTimKiem()
    {
        System.out.println("1. Tìm kiếm theo mã phiếu");
        System.out.println("2. Tìm kiếm theo ngày");
        System.out.println("3. Tìm kiếm theo tổng tiền");
        System.out.println("4. Tìm kiếm theo mã nhân viên");
        switch (Lib.takeInputChoice(1, 4))
        {
            case 1 -> {
                String id = Lib.takeStringInput("Nhập ID phiếu: ");
                Phieu obj = timKiemTheoIDPhieu(id);
                xuatTuaDePhieu();
                if(obj != null)
                    obj.inPhieu();
                else
                    Lib.printError("Không tìm thấy");
            }
            case 2 -> {
                String date = Lib.takeStringInput("Nhập date cần tìm: ");
                ArrayList<Phieu> list = timKiemTheoNgay(date);
                xuatTuaDePhieu();
                if(list != null)
                    xemDSPhieu(list);
                else
                    Lib.printError("Không tìm thấy");
            }
            case 3 -> {
                int tongtien = Lib.takeIntegerInput("Nhập tổng tiền cần tìm: ");
                ArrayList<Phieu> list = timkiemTheoTongTien(tongtien);
                xuatTuaDePhieu();
                if(list != null)
                    xemDSPhieu(list);
                else
                    Lib.printError("Không tìm thấy");
            }
            case 4 -> {
                String idnv = Lib.takeStringInput("Nhập mã nhân viên cần tìm: ");
                ArrayList<Phieu> list = timKiemTheoMaNhanVien(idnv);
                xuatTuaDePhieu();
                if(list != null)
                    xemDSPhieu(list);
                else
                    Lib.printError("Không tìm thấy");
            }
        }
    }

    public void menu(DanhSachDT danhSachDT, Nguoi nguoi, Shop shop, danhsachcungcap dsncc)
    {
        boolean thoatXemDSPhieu = false;
        while(true)
        {
            xuatTuaDePhieu();
            xemDSPhieu();
            System.out.println("1. Xem chi tiết phiếu");
            System.out.println("2. Thêm phiếu");
            System.out.println("3. Xóa phiếu");
            System.out.println("4. Tìm kiếm");
            System.out.println("5. Sắp xếp theo tổng tiền");
            System.out.println("0. Quay lại");
            switch (Lib.takeInputChoice(0,5))
            {
                case 1 -> {
                    if(dsPhieuXuat.isEmpty())
                        Lib.printError("Danh sách đang rỗng không có gì để tìm");
                    else
                    {
                        String id = Lib.takeStringInput("Nhập ID phiếu cần xem chi tiết: ");
                        Phieu obj = timKiemTheoIDPhieu(id);
                        if(obj == null)
                            Lib.printError("Không có ID đó trong danh sách");
                        else
                            obj.getDsChiTietPhieu().menuChiTietPhieu(obj, danhSachDT, shop, dsncc);
                    }
                }
                case 2 -> {
                    int slpt = Lib.takeSoLuongCanTao("Số lượng phiếu cần thêm: ");
                    for (int i = 0; i < slpt; i++) {
                        System.out.println("Nhập phiếu thứ " + (i + 1));
                        if(!taoPhieu(danhSachDT, nguoi, dsncc))
                            break;
                    }
                }
                case 3 -> {xoaPhieu();}
                case 4 -> {
                    if(dsPhieuXuat.isEmpty())
                        Lib.printError("Danh sách đang rỗng không có gì để tìm");
                    else
                        menuTimKiem();
                }
                case 5 -> {sapXepTheoTongTien();}
                case 0 -> {thoatXemDSPhieu = true;}
            }
            if (thoatXemDSPhieu)
                break;
            Lib.clearScreen();
        }

    }

}
