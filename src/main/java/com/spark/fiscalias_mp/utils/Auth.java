/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spark.fiscalias_mp.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.spark.fiscalias_mp.model.User;
import com.spark.fiscalias_mp.model.UserDAO;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Leonel
 */
@WebFilter(filterName = "Auth", urlPatterns = {"/fiscalias/*"})
public class Auth implements Filter {
    UserDAO userdao = new UserDAO();
    
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
            
//            HttpServletRequest httpRequest = (HttpServletRequest) request;
//            Enumeration<String> headerNames = httpRequest.getHeaderNames();
//
//            if (headerNames != null) {
//                    while (headerNames.hasMoreElements()) {
//                        String name = headerNames.nextElement();
//                        System.out.println("Header: " + name + ":   " + httpRequest.getHeader(name));
//                    }
//            }
//        
//            HttpServletResponse resp = (HttpServletResponse) response;
//            resp.setHeader("Access-Control-Allow-Origin", "*");
//            resp.setHeader("Access-Control-Allow-Methods", "GET");
//            resp.setHeader("Access-Control-Allow-Methods", "POST");
//            
//            HttpServletRequest res = (HttpServletRequest) request;
            boolean seguir = true;
            
//            if(res.getHeader("Authorization") != null){
//                System.out.println("En filtro");
//                String token = res.getHeader("Authorization");
//                System.out.println(token);
//                 try {
//                     DecodedJWT jwt = JWT.decode(token);
//                     System.out.println("aca estoy");
//                     Map<String, Claim> claims = jwt.getClaims();    //Key is the Claim name
//                     int id = claims.get("id").asInt();
//                     User usuario = (User) userdao.findById(id);
//                     System.out.println(usuario.toString());
//                    Algorithm algorithm = Algorithm.HMAC256("MPFiscalias;!" + usuario.getEmail());
//                    JWTVerifier verifier = JWT.require(algorithm)
//                         .withClaim("id", id)
//                         .build();
//                     jwt = verifier.verify(token);
//                     System.out.println(jwt.getAlgorithm());
//                     
//                 } catch (JWTDecodeException exception){
//                     resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//                     seguir = false;
//                 }
//                 
//                 
//           } else {
//               resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//               seguir = false;
//           }
           
           if(seguir){
                chain.doFilter(request, response);
            }
                
           
        
           
    }

    public void destroy() {        
    }

    
    public void init(FilterConfig filterConfig) {        
        System.out.println("Hola desde filtro");
    }

}
