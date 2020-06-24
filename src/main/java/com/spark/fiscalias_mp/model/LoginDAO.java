/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spark.fiscalias_mp.model;

/**
 *
 * @author Leonel
 * @param <T>
 */
public interface LoginDAO<T> {
    
    public int authenticate(String email, String password); //autenticacion de usuario. Devuelve el id
    
    T findById(int id); //busqueda de usuario
    
    int getRol(int id); //obtener rol de usuario
}
