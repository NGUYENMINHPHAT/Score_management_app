package com.example.appquanlidiem.tuyen_database_thongtin;

public class ThongTin {
    private String tenNguoiDung;
    private String tenHocKi;

    public ThongTin() {
    }

    public ThongTin(String tenNguoiDung, String tenHocKi) {
        this.tenNguoiDung = tenNguoiDung;
        this.tenHocKi = tenHocKi;
    }

    public String getTenHocKi() {
        return tenHocKi;
    }

    public void setTenHocKi(String tenHocKi) {
        this.tenHocKi = tenHocKi;
    }

    public String getTenNguoiDung() {
        return tenNguoiDung;
    }

    public void setTenNguoiDung(String tenNguoiDung) {
        this.tenNguoiDung = tenNguoiDung;
    }
}
