package com.app.rest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestRevokeToken {
	@Autowired
	 private TokenStore tokenStore;
	
	@RequestMapping(value="/oauth/revoke-token",method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public void revokeToken(HttpServletRequest request) {
		System.out.println("token");
		  String authHeader = request.getHeader("Authorization");
	        if (authHeader != null) {
	            String tokenValue = authHeader.replace("Bearer", "").trim();
	            OAuth2AccessToken accessToken = tokenStore.readAccessToken(tokenValue);
	            tokenStore.removeAccessToken(accessToken);
	        }
		
	}
	
}
