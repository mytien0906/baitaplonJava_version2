/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication7;

/**
 *
 * @author Admin
 */
public class TraiCay {

    private String maTraiCay;
    private String tenTraiCay;
    private int soLuong;
    private double giaBan;

    public TraiCay() {
    }

    public TraiCay(TraiCay traiCay) {
        this.maTraiCay = traiCay.getMaTraiCay();
        this.tenTraiCay = traiCay.getTenTraiCay();
        this.soLuong = traiCay.getSoLuong();
        this.giaBan = traiCay.getGiaBan();
    }

    public TraiCay(String maTraiCay, String tenTraiCay, int soLuong, double giaBan) {
        this.maTraiCay = maTraiCay;
        this.tenTraiCay = tenTraiCay;
        this.soLuong = soLuong;
        this.giaBan = giaBan;
    }

    public String getMaTraiCay() {
        return maTraiCay;
    }

    public void setMaTraiCay(String maTraiCay) {
        this.maTraiCay = maTraiCay;
    }

    public String getTenTraiCay() {
        return tenTraiCay;
    }

    public void setTenTraiCay(String tenTraiCay) {
        this.tenTraiCay = tenTraiCay;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(double giaBan) {
        this.giaBan = giaBan;
    }
}
