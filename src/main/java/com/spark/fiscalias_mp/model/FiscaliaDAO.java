/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spark.fiscalias_mp.model;

import com.spark.fiscalias_mp.utils.DBConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Leonel
 */
public class FiscaliaDAO implements GeneralDAO{

    @Override
    public List<Fiscalia> queryAll() {
        int id=0;
        String nombre, lat, lon, phone, dept = "";
        List<Fiscalia> fiscalias = new ArrayList<>();
        Statement stmt;
        try {
            String SPsql = "EXEC sp_all_fiscalias"; 
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(SPsql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                id = rs.getInt(1);
                nombre = rs.getString(2);
                lat = rs.getString(3);
                lon = rs.getString(4);
                phone = rs.getString(5);
                dept = rs.getString(6);
                fiscalias.add(new Fiscalia(id, nombre, dept, phone, lat, lon));
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return fiscalias;
    }

    @Override
    public Object findById(long id) {
        Fiscalia nueva = new Fiscalia();
        
        String nombre, lat, lon, phone, dept = "";
        Statement stmt;
        try {
            String SPsql = "EXEC sp_fiscalia ?"; 
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(SPsql);
            ps.setInt(1, (int) id);
            ResultSet rs = ps.executeQuery();

            if(rs.next() != false){
                nombre = rs.getString(2);
                lat = rs.getString(3);
                lon = rs.getString(4);
                phone = rs.getString(5);
                dept = rs.getString(6);
                nueva.setId((int) id);
                nueva.setName(nombre);
                nueva.setLat(lat);
                nueva.setLon(lon);
                nueva.setPhone(phone);
                nueva.setDept(dept);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return nueva;
    }

    @Override
    public int create(Object element) {
        Fiscalia nuevo = (Fiscalia) element;
        int resultado = 0;
        Statement stmt;
        try {
            String SPsql = "EXEC sp_add_fiscalia ?, ?, ?, ?, ?"; 
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(SPsql);
            ps.setString(1, nuevo.getName());
            ps.setString(2, nuevo.getLat());
            ps.setString(3, nuevo.getLon());
            ps.setString(4, nuevo.getPhone());
            ps.setString(5, nuevo.getDept());
            ResultSet rs = ps.executeQuery();
            
//            resultado = stmt.executeUpdate(consulta);

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(FiscaliaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        return 1;
    }

    @Override
    public void edit(long id, Object element) {
        Fiscalia nuevo = (Fiscalia) element;
        int resultado = 0;
        Statement stmt;
        try {
            String SPsql = "EXEC sp_mod_fiscalia ?, ?, ?, ?, ?, ?"; 
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(SPsql);
            ps.setInt(1, (int) id);
            ps.setString(2, nuevo.getName());
            ps.setString(3, nuevo.getLat());
            ps.setString(4, nuevo.getLon());
            ps.setString(5, nuevo.getPhone());
            ps.setString(6, nuevo.getDept());
            ResultSet rs = ps.executeQuery();
            
//            resultado = stmt.executeUpdate(consulta);

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(FiscaliaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }

    @Override
    public void delete(long id) {
        int resultado = 0;
        Statement stmt;
        try {
            String SPsql = "EXEC sp_del_fiscalia ?"; 
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(SPsql);
            ps.setInt(1, (int) id);
            ResultSet rs = ps.executeQuery();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(FiscaliaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
}
