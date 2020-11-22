/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nguyenmanhhao.qlhv.model;

import java.io.Serializable;
import java.sql.Date;


/**
 *
 * @author HAO
 */
public class HocVien implements Serializable{
    private int ma_hoc_sinh;
    private String ho_ten;
    private String so_dien_thoai;
    private String dia_chi;
    private Date ngay_sinh;
    private boolean gioi_tinh;
    private boolean tinh_trang;

    public int getma_hoc_sinh() {
        return ma_hoc_sinh;
    }

    public void setma_hoc_sinh(int ma_hoc_sinh) {
        this.ma_hoc_sinh = ma_hoc_sinh;
    }

    public String getho_ten() {
        return ho_ten;
    }

    public void setho_ten(String ho_ten) {
        this.ho_ten = ho_ten;
    }

    public Date getngay_sinh() {
        return ngay_sinh;
    }

    public void setngay_sinh(Date ngay_sinh) {
        this.ngay_sinh = ngay_sinh;
    }
    
    public boolean gioi_tinh() {
        return gioi_tinh;
    }

    public void setgioi_tinh(boolean gioi_tinh) {
        this.gioi_tinh = gioi_tinh;
    }
    
    public String getso_dien_thoai() {
        return so_dien_thoai;
    }

    public void setso_dien_thoai(String so_dien_thoai) {
        this.so_dien_thoai = so_dien_thoai;
    }

    public String getdia_chi() {
        return dia_chi;
    }

    public void setdia_chi(String dia_chi) {
        this.dia_chi = dia_chi;
    }

    public boolean tinh_trang() {
        return tinh_trang;
    }

    public void settinh_trang(boolean tinh_trang) {
        this.tinh_trang = tinh_trang;
    }

    @Override
    public String toString() {
        return  ma_hoc_sinh + " - " + ho_ten ;
    }

   
}
