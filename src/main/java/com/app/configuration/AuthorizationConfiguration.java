package com.app.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
@Configuration
@EnableAuthorizationServer
@Order(100)
public class AuthorizationConfiguration extends AuthorizationServerConfigurerAdapter {
    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;
    
    private static final String RESOURCE_ID = "xxx";
    
    @Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.authenticationManager(this.authenticationManager).tokenStore(tokenStore());
 	}
    
    @Bean
    public TokenStore tokenStore() {
    	return new InMemoryTokenStore();
    }
    
    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer.checkTokenAccess("permitAll()");
        
    }
    
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients
                .inMemory()
                .withClient("clientapp")
                    .secret("123456")
                    .authorizedGrantTypes("password", "authorization_code", "refresh_token")
                    .scopes("read", "write")
                    .resourceIds(RESOURCE_ID)
                    .redirectUris("http://localhost:8081/login")
                    .accessTokenValiditySeconds(200)
                    .refreshTokenValiditySeconds(100)
                    .and()
                    .withClient("jsclient")
                        .secret("jspasswd")
                        .authorizedGrantTypes("implicit")
                        .scopes("read", "write")
                        .resourceIds(RESOURCE_ID)
                        .authorities("CLIENT")
                        .redirectUris("http://localhost:20000/implicit-client/")
                        .accessTokenValiditySeconds(60 * 60 * 24) // token berlaku seharian, besok harus login ulang
                        .autoApprove(true)
                  
  ;

     
    }
}
