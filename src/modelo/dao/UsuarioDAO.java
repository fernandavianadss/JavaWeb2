package modelo.dao;

import modelo.dominio.Usuario;

/**
 * Classe de persist�ncia Usu�rio
 * 
 * @author Fernanda Viana
 * @version 4.0.0 - 13/11/2018
 */

public class UsuarioDAO {

	public Usuario obter(String login) {
		Usuario novo = new Usuario();
		novo.setLogin(login);
		novo.setSenha("123");

		return novo;
	}

}
