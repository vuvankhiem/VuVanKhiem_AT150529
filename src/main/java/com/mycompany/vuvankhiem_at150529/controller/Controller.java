/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vuvankhiem_at150529.controller;


import com.mycompany.vuvankhiem_at150529.model.SanPham;
import java.util.List;

/**
 *
 * @author Administrator
 */
public interface Controller {
    List<SanPham> getAll ();
    boolean addProduct (String maSP, String tenSP, int soLuong, int donGia);
    SanPham getProductByID (String maSP);
    void removeProduct (String maSP);
    void editProduct (String maSP, String tenSP, int soLuong, int donGia);
}
