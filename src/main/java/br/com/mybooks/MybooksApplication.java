/*
 * mybooks 1.0 17 de jan de 2017
 * 
 * MIT License - Copyright (c) 2017
 *
 */
package br.com.mybooks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * A classe <code>mybooksApplication</code> é responsável
 * pela inicialização da aplicação.
 * 
 * @author Augusto dos Santos
 * @version 1.0 18 de jan de 2017
 */
@SpringBootApplication
@EnableConfigurationProperties
public class MybooksApplication {

	public static void main(String[] args) {
		SpringApplication.run(MybooksApplication.class, args);
	}
}
