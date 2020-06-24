/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spark.fiscalias_mp.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Leonel
 * Singleton
 */
public class DBConnection {
    private static Connection conn = null; //conexion a la BD
    private static final String USER = "Fiscalia"; //
    private static final String PASS = "tbDpgY6hsB";
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=MP";
    
    public static Connection getConnection() 
        throws SQLException, ClassNotFoundException {
        
        if(conn == null){
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            System.out.println("Driver encontrado");
            System.out.println("intentando conexion " + URL);
            conn = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("Conexion exitosa! con " + URL);
        }
        
        return conn;
    }
    
}
