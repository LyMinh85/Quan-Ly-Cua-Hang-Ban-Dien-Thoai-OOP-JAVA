package QuanLyBanDienThoai;

import java.util.ArrayList;

public interface DSPhieu {
    //Xuất tự đề phiếu
    public void xuatTuaDePhieu();
    //Xem danh sách các phiếu
    public void xemDSPhieu();
    //Tìm kiếm theo mã phiếu
    public Phieu timKiemTheoIDPhieu(String id);
    //Nhập hàm nhập và kiểm tra mã phiếu
    public String nhapIDPhieu();
    public boolean taoPhieu(DanhSachDT danhSachDT, Nguoi nguoi, danhsachcungcap dsncc);
    public void xoaPhieu();
    public ArrayList<Phieu> timKiemTheoNgay(String date);
    public ArrayList<Phieu> timkiemTheoTongTien(int tongtien);
    public ArrayList<Phieu> timKiemTheoMaNhanVien(String idnv);
    public void sapXepTheoTongTien();
    //Menu các lựa chọn tìm kiếm
    public void menuTimKiem();
    //Menu chính
    public void menu(DanhSachDT danhSachDT , Nguoi nguoi, Shop shop, danhsachcungcap dsncc);
}
