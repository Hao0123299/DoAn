package com.nguyenmanhhao.qlhv.dao;

import com.nguyenmanhhao.qlhv.model.HocVien;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;


public class HocVienDAOImpl implements HocVienDAO {

    @Override
    public List<HocVien> getList() {
        Connection cons = DBConnect.getConnection();
        String sql = "SELECT * FROM Hoc_sinh";
        List<HocVien> list = new ArrayList<>();
        try {
            PreparedStatement ps = (PreparedStatement) cons.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HocVien hocVien = new HocVien();
                hocVien.setma_hoc_sinh(rs.getInt("ma_hoc_sinh"));
                hocVien.setho_ten(rs.getString("ho_ten"));
                hocVien.setngay_sinh(rs.getDate("ngay_sinh"));
                hocVien.setgioi_tinh(rs.getBoolean("gioi_tinh"));
                hocVien.setso_dien_thoai(rs.getString("so_dien_thoai"));
                hocVien.setdia_chi(rs.getString("dia_chi"));
                hocVien.settinh_trang(rs.getBoolean("tinh_trang"));
                list.add(hocVien);
            }
            rs.close();
            cons.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return  list;
    }
    public static void main(String[] args) {
        HocVienDAO hocVienDAO = new HocVienDAOImpl();
        System.out.println(hocVienDAO.getList());
    }
     @Override
    public int createOrUpdate(HocVien hocVien) {
        try {
            Connection cons = DBConnect.getConnection();
            //String sql = "INSERT INTO Hoc_sinh(ma_hoc_sinh, ho_ten, ngay_sinh, gioi_tinh, so_dien_thoai, dia_chi, tinh_trang) VALUES(?, ?, ?, ?, ?, ?, ?)";
            String sql = "INSERT INTO Hoc_sinh(ma_hoc_sinh, ho_ten, ngay_sinh, gioi_tinh, so_dien_thoai, dia_chi, tinh_trang) "
                    + "VALUES(?, ?, ?, ?, ?, ?, ?) ON DUPLICATE KEY UPDATE ho_ten = VALUES(ho_ten), ngay_sinh = VALUES(ngay_sinh), gioi_tinh = VALUES(gioi_tinh), so_dien_thoai = VALUES(so_dien_thoai), dia_chi = VALUES(dia_chi), tinh_trang = VALUES(tinh_trang);";
            PreparedStatement ps = cons.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, hocVien.getma_hoc_sinh());
            ps.setString(2, hocVien.getho_ten());
            ps.setDate(3, (hocVien.getngay_sinh()));
            ps.setBoolean(4, hocVien.gioi_tinh());
            ps.setString(5, hocVien.getso_dien_thoai());
            ps.setString(6, hocVien.getdia_chi());
            ps.setBoolean(7, hocVien.tinh_trang());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            int generatedKey = 0;
            if (rs.next()) {
                generatedKey = rs.getInt(1);
            }
            ps.close();
            cons.close();
            return generatedKey;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }
}