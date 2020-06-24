/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spark.fiscalias_mp.model;

import com.spark.fiscalias_mp.utils.DBConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 *
 * @author Leonel
 */
public class UserDAO implements LoginDAO{

    @Override
    public int authenticate(String email, String password){
        int authenticated = 0;
        String pass;
        Statement stmt;
        try {
            stmt = DBConnection.getConnection().createStatement();
            String SQL = "SELECT user_id, password FROM [dbo].[users] where email = '" + email + "';";
            ResultSet rs = stmt.executeQuery(SQL);

            if(rs.next() != false){
                pass = rs.getString(2);
//                System.out.println(BCrypt.hashpw(pass, BCrypt.gensalt()));
                if(BCrypt.checkpw(password, pass)){
                    authenticated = rs.getInt(1);
                }
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return authenticated;
    }

    @Override
    public Object findById(int id) {
        Statement stmt;
        User usuario = new User();
        try {
            stmt = DBConnection.getConnection().createStatement();
            String SQL = "SELECT * FROM [dbo].[users] where user_id = " + id + ";";
            ResultSet rs = stmt.executeQuery(SQL);

            if(rs.next() != false){
                usuario.setEmail(rs.getString(2));
                usuario.setName(rs.getString(4));
                usuario.setPhone(rs.getString(5));
                usuario.setUser_id(id);
                
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return usuario;
    }

    @Override
    public int getRol(int i) {
        int rol = 0;
        Statement stmt;
        String consulta = "SELECT rol_id from user_rol where user_id = " + i;
        
        try {
            stmt = DBConnection.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(consulta);
            if(rs.next() != false){
                rol = rs.getInt(1);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        //devuelve rol 1=sa, 2=admin, 3=user
        return rol;
    }

    
    
}
