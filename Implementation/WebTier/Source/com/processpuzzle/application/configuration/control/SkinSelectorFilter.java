package com.processpuzzle.application.configuration.control;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.processpuzzle.application.configuration.domain.ProcessPuzzleContext;
import com.processpuzzle.application.configuration.domain.PropertyContext;
import com.processpuzzle.application.configuration.domain.PropertyKeys;
import com.processpuzzle.user_session.domain.UserRequestManager;

public class SkinSelectorFilter implements Filter {
   public static final String SKIN_ATTRIBUTE_NAME = "skinDescriptor";
   private SkinDescriptor skinDescriptor;
   private ProcessPuzzleContext applicationContext;

   @Override
   public void destroy() {
   // TODO Auto-generated method stub

   }

   @Override
   public void doFilter( ServletRequest request, ServletResponse response, FilterChain filterChain ) throws IOException, ServletException {
      HttpServletRequest httpRequest = (HttpServletRequest) request;
      HttpSession httpSession = (HttpSession) httpRequest.getSession();
      
      httpSession.setAttribute( SKIN_ATTRIBUTE_NAME, skinDescriptor );
      filterChain.doFilter( request, response );
   }

   @Override
   public void init( FilterConfig filterConfiguration ) throws ServletException {
      applicationContext = UserRequestManager.getInstance().getApplicationContext();
      skinDescriptor = determineDefaultSkin();
   }

   private SkinDescriptor determineDefaultSkin() {
      PropertyContext propertyContext = applicationContext.getPropertyContext();
      String defaultSkinName = propertyContext.getProperty( PropertyKeys.PRESENTATION_DEFALT_SKIN_NAME.getDefaultKey() );
      String skinPath = propertyContext.getProperty( PropertyKeys.PRESENTATION_DEFALT_SKIN_PATH.getDefaultKey() );
      SkinDescriptor skinDescriptor = new SkinDescriptor( defaultSkinName, skinPath );
      return skinDescriptor;
   }
}
