/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spark.fiscalias_mp.utils;

import javax.servlet.ServletRequest;

/**
 *
 * @author Leonel
 */
public class ParamsUtil {
    
    public static int getIntValue(ServletRequest request, String paramName, int defaultValue){
        if(request.getParameter(paramName) != null){
            return Integer.parseInt(request.getParameter(paramName));
        } else{
            return defaultValue;
        }
    }
    
    public static String getStringValue(ServletRequest request, String paramName, String defaultValue){
        if(request.getParameter(paramName) != null){
            return request.getParameter(paramName);
        } else{
            return defaultValue;
        }
    }
}
