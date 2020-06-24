/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spark.fiscalias_mp.model;

import java.util.List;

/**
 *
 * @author Leonel
 * @param <T>
 */
public interface GeneralDAO<T> {
    List<T> queryAll(); //metodo para obtener todos los objetos en la BD
    
    T findById(long id); //metodo para busqueda
    
    int create(T element); //metodo para creacion
    
    void edit(long id, T element); //metodo para edicion
    
    void delete(long id); //metodo para eliminar
}
