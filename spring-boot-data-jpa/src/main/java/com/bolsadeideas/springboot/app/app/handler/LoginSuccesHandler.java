package com.bolsadeideas.springboot.app.app.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.support.SessionFlashMapManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class LoginSuccesHandler extends SimpleUrlAuthenticationSuccessHandler {
        public void  onAutheticactionSuccess(HttpServletRequest request, HttpServletResponse response,
                                             Authentication authentication) throws IOException, ServletException{
            SessionFlashMapManager flashMapManager = new SessionFlashMapManager();
            FlashMap flashMap = new FlashMap();
            flashMap.put("success","hola"+authentication.getName()+", has Iniciado sesion con exito!");
            flashMapManager.saveOutputFlashMap(flashMap,request,response);
            if(authentication != null){
                logger.info("El usuario"+authentication.getName()+" ha iniciado sesion con Exito");
            }
            super.onAuthenticationSuccess(request,response,authentication);
        }
}
