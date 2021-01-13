package com.app.entry;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

public class LogoutRestEntryPoint extends HttpStatusReturningLogoutSuccessHandler{

	
	
	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException {
		// TODO Auto-generated method stub
		 new SecurityContextLogoutHandler().logout(request,response,authentication);
	       SecurityContextHolder.clearContext();
	       try {
			request.logout();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	       request.getSession().invalidate();
	      super.onLogoutSuccess(request, response, authentication);
	 }


}
