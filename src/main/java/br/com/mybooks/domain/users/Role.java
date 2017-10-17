/*
 * mybooks 1.0 24 de mar de 2017
 * 
 * MIT License - Copyright (c) 2017
 *
 */
package br.com.mybooks.domain.users;

/**
 * A enum <code>Role</code> define o papel de cada usuário
 * dentro do contexto da aplicação.
 *
 * @author Augusto dos Santos
 * @version 1.0 24 de mar de 2017
 */
public enum Role {

    LIBRARIAN, READER;
	
	/**
	 * Retorna uma representação do nível de acesso.
	 */
	public String authority() {
        return "ROLE_" + this.name();
    }
}
