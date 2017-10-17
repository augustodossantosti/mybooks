/*
 * mybooks 1.0 13 de jan de 2017
 * 
 * MIT License - Copyright (c) 2017
 *
 */
package br.com.mybooks.infra.security.auth.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * A classe <code>JwtSettings</code> contém as configurações do JWT.
 * Os valores padrão são definidos no aquivo application.yml.
 * 
 * @author Augusto dos Santos
 * @version 1.0 13 de jan de 2017
 */
@Configuration
@ConfigurationProperties(prefix = "config.security.jwt")
public class JwtSettings {

	private Integer tokenExpirationTime;
    private String tokenIssuer;
    private String tokenSigningKey;
    
    public Integer getTokenExpirationTime() {
        return tokenExpirationTime;
    }
    
    public void setTokenExpirationTime(Integer tokenExpirationTime) {
        this.tokenExpirationTime = tokenExpirationTime;
    }
    
    public String getTokenIssuer() {
        return tokenIssuer;
    }
    public void setTokenIssuer(String tokenIssuer) {
        this.tokenIssuer = tokenIssuer;
    }
    
    public String getTokenSigningKey() {
        return tokenSigningKey;
    }
    
    public void setTokenSigningKey(String tokenSigningKey) {
        this.tokenSigningKey = tokenSigningKey;
    }
    
}
