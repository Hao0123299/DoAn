/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nguyenmanhhao.qlhv.controller;

import com.nguyenmanhhao.qlhv.model.HocVien;
import com.nguyenmanhhao.qlhv.service.HocVienService;
import com.nguyenmanhhao.qlhv.service.HocVienServiceImpl;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import javax.swing.*;

/**
 *
 * @author HAO
 */
 public class HocVienController {
   
    private  JButton btnSubmit;
    private JTextField jtfMaHocSinh;
    private  JTextField jtfTenHocSinh;
    
    private JDateChooser jdcNgaySinh;
    private JTextField jtfSoDienThoai;
    private JRadioButton jrdNam;
    private JRadioButton jrdNu;
    private JTextField jtfDiaChi;
    private JCheckBox jcbTinhTrang;
    private JTextField jtfMsg;

    private HocVien hocVien = null;

    private HocVienService hocVienService = null;
    
    public HocVienController(JButton btnSubmit, JTextField jtfMaHocSinh, JTextField jtfTenHocSinh,
            JDateChooser jdcNgaySinh, JTextField jtfSoDienThoai, JRadioButton jrdNam, JRadioButton jrdNu,
            JTextField jtfDiaChi, JCheckBox jcbTinhTrang, JTextField jtfMsg) {
        this.btnSubmit = btnSubmit;
        this.jtfMaHocSinh = jtfMaHocSinh;
        this.jtfTenHocSinh = jtfTenHocSinh;
        this.jdcNgaySinh = jdcNgaySinh;
        this.jtfSoDienThoai = jtfSoDienThoai;
        this.jrdNam = jrdNam;
        this.jrdNu = jrdNu;
        this.jtfDiaChi = jtfDiaChi;
        this.jcbTinhTrang = jcbTinhTrang;
        this.jtfMsg = jtfMsg;

        this.hocVienService = new HocVienServiceImpl();
    }
    public void setView(HocVien hocVien) {
        this.hocVien = hocVien;
        // set data
        jtfMaHocSinh.setText("#" + hocVien.getma_hoc_sinh());
        jtfTenHocSinh.setText(hocVien.getho_ten());
        jdcNgaySinh.setDate(hocVien.getngay_sinh());
        if (hocVien.gioi_tinh()) {
            jrdNam.setSelected(true);
        } else {
            jrdNu.setSelected(true);
        }
        jtfSoDienThoai.setText(hocVien.getso_dien_thoai());
        
             jtfDiaChi.setText(hocVien.getdia_chi());
        jcbTinhTrang.setSelected(hocVien.tinh_trang());
        // set event
        setEvent();
    }

    public void setEvent() {
        btnSubmit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    if (!checkNotNull()) {
                        jtfMsg.setText("Vui lòng nhập dữ liệu bắt buộc!");
                    } else {
                        hocVien.setho_ten(jtfTenHocSinh.getText().trim());
                        hocVien.setngay_sinh((Date)jdcNgaySinh.getDate());
                        hocVien.setso_dien_thoai(jtfSoDienThoai.getText());
                        hocVien.setdia_chi(jtfDiaChi.getText());
                        hocVien.setgioi_tinh(jrdNam.isSelected());
                        hocVien.settinh_trang(jcbTinhTrang.isSelected());
                        if (showDialog()) {
                            int lastId = hocVienService.createOrUpdate(hocVien);
                            if (lastId != 0) {
                                hocVien.setma_hoc_sinh(lastId);
                                jtfMaHocSinh.setText("#" + hocVien.getma_hoc_sinh());
                                jtfMsg.setText("Xử lý cập nhật dữ liệu thành công!");
                            } else {
                                jtfMsg.setText("Có lỗi xảy ra, vui lòng thử lại!");
                            }
                        }
                    }
                } catch (Exception ex) {    
                    jtfMsg.setText(ex.toString());
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnSubmit.setBackground(new Color(0, 200, 83));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnSubmit.setBackground(new Color(100, 221, 23));
            }
        });
    }

    private boolean checkNotNull() {
        return jtfTenHocSinh.getText() != null && !jtfTenHocSinh.getText().equalsIgnoreCase("");
    }

    private boolean showDialog() {
        int dialogResult = JOptionPane.showConfirmDialog(null,
                "Bạn muốn cập nhật dữ liệu hay không?", "Thông báo", JOptionPane.YES_NO_OPTION);
        return dialogResult == JOptionPane.YES_OPTION;
    }
    
    
    
}
