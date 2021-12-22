/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vuvankhiem_at150529.controller;


import com.mycompany.vuvankhiem_at150529.model.SanPham;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class ControllerImpl implements Controller {
    
    private Connection connection;
    
    public ControllerImpl () {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/swing_demo", "root", "251125");
        } catch (SQLException e) {
            System.err.println("-> Not Connected !");
        }
    }
    
    
    @Override
    public List<SanPham> getAll() {
        List<SanPham> list = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("select * from sanpham");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {                
                SanPham sp = new SanPham(
                        rs.getString(1), 
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getInt(4)
                );
                list.add(sp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControllerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public boolean addProduct(String maSP, String tenSP, int soLuong, int donGia) {
        boolean check = true;
        String sql = "insert into sanpham values (?,?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, maSP);
            statement.setString(2, tenSP);
            statement.setInt(3, soLuong);
            statement.setInt(4, donGia);
            statement.executeUpdate();
        } catch (SQLException e) {
            check = false;
        }
        return check;
    }

    @Override
    public SanPham getProductByID(String maSP) {
        SanPham sp = new SanPham();
        String sql = "select * from sanpham where maSP = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, maSP);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {                
                sp.setMaSP(rs.getString(1));
                sp.setTenSP(rs.getString(2));
                sp.setSoLuong(rs.getInt(3));
                sp.setDonGia(rs.getInt(4));
                
            }
        } catch (SQLException e) {
        }
        return sp;
    }

    @Override
    public void removeProduct(String maNV) {
        String sql = "delete from sanpham where maSP = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, maNV);
            statement.executeUpdate();
        } catch (SQLException e) {
            
        }
    }

    @Override
    public void editProduct(String maSP, String tenSP, int soLuong, int donGia) {
        String sql = "update sanpham set tenSP = ?, soLuong = ?, donGia = ? where maSP = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, tenSP);
            statement.setInt(2, soLuong);
            statement.setInt(3, donGia);
            statement.setString(4, maSP);
            statement.executeUpdate();
        } catch (SQLException e) {
            
        }
    }
}
