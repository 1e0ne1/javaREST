/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spark.fiscalias_mp.controller;


import com.spark.fiscalias_mp.model.Fiscalia;
import com.spark.fiscalias_mp.model.FiscaliaDAO;
import com.spark.fiscalias_mp.utils.ParamsUtil;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Leonel
 */
@WebServlet(name = "FiscaliasServlet", urlPatterns = {"/fiscalias/*"})
public class FiscaliasServlet extends HttpServlet {
    FiscaliaDAO fiscaliadao = new FiscaliaDAO();
    List<Fiscalia> fiscalias = fiscaliadao.queryAll();
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        setAccessControlHeaders(response);
        int id = 0;
        if(request.getParameter("id") != null){
            id = Integer.parseInt(request.getParameter("id"));
        }
        
        PrintWriter out = response.getWriter();
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        if(id == 0){
            out.write(new com.google.gson.Gson().toJson(fiscalias));
        } else {
            Fiscalia temp = (Fiscalia) fiscaliadao.findById(id);
            if(temp.getId() != 0){
                out.write(temp.toString());
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        }
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        setAccessControlHeaders(response);
        String name = ParamsUtil.getStringValue(request, "name", "");
        String lat = ParamsUtil.getStringValue(request, "lat", "");
        String lon = ParamsUtil.getStringValue(request, "lon", "");
        String phone = ParamsUtil.getStringValue(request, "phone", "");
        String dept = ParamsUtil.getStringValue(request, "dept", "");
        
        if(name.isEmpty() || lat.isEmpty() || lon.isEmpty() || phone.isEmpty() || dept.isEmpty()){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } else {
            Fiscalia nuevo = new Fiscalia(0, name, dept, phone, lat, lon);
            int id = fiscaliadao.create(nuevo);
            if(id > 0){
                response.getWriter().print(id);
                fiscalias = fiscaliadao.queryAll();
            }
            
        }
    }
    
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        setAccessControlHeaders(response);
        int id = ParamsUtil.getIntValue(request, "id", 0);
        if(id != 0){
            fiscaliadao.delete((long) id);
            fiscalias = fiscaliadao.queryAll();
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
    
     @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        setAccessControlHeaders(response);
        int id = ParamsUtil.getIntValue(request, "id", 0);
        String name = ParamsUtil.getStringValue(request, "name", "");
        String lat = ParamsUtil.getStringValue(request, "lat", "");
        String lon = ParamsUtil.getStringValue(request, "lon", "");
        String phone = ParamsUtil.getStringValue(request, "phone", "");
        String dept = ParamsUtil.getStringValue(request, "dept", "");
        
        if(id == 0 ||name.isEmpty() || lat.isEmpty() || lon.isEmpty() || phone.isEmpty() || dept.isEmpty()){
            System.out.println("mala solicitud");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } else {
            Fiscalia nuevo = new Fiscalia(id, name, dept, phone, lat, lon);
            fiscaliadao.edit(id, nuevo);
        }
    }

    @Override
  protected void doOptions(HttpServletRequest req, HttpServletResponse resp)
          throws ServletException, IOException {
      setAccessControlHeaders(resp);
      resp.setStatus(HttpServletResponse.SC_OK);
  }

  private void setAccessControlHeaders(HttpServletResponse resp) {
      resp.setHeader("Access-Control-Allow-Origin", "*");
      resp.setHeader("Access-Control-Allow-Methods", "GET");
      resp.setHeader("Access-Control-Allow-Methods", "POST");
      resp.setHeader("Access-Control-Allow-Methods", "PUT");
  }

}
