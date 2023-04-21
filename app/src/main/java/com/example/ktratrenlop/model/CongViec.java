package com.example.ktratrenlop.model;

import java.io.Serializable;

public class CongViec implements Serializable {
    private int id;
    private String ten, noidung, ngay;
    private boolean tinhtrang, congtac;

    public CongViec() {
    }

    public CongViec(int id, String ten, String noidung, String ngay, boolean tinhtrang, boolean congtac) {
        this.id = id;
        this.ten = ten;
        this.noidung = noidung;
        this.ngay = ngay;
        this.tinhtrang = tinhtrang;
        this.congtac = congtac;
    }

    public CongViec(String ten, String noidung, String ngay, boolean tinhtrang, boolean congtac) {
        this.ten = ten;
        this.noidung = noidung;
        this.ngay = ngay;
        this.tinhtrang = tinhtrang;
        this.congtac = congtac;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public boolean isTinhtrang() {
        return tinhtrang;
    }

    public void setTinhtrang(boolean tinhtrang) {
        this.tinhtrang = tinhtrang;
    }

    public boolean isCongtac() {
        return congtac;
    }

    public void setCongtac(boolean congtac) {
        this.congtac = congtac;
    }
}
