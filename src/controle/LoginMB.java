package controle;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import modelo.dao.UsuarioDAO;
import modelo.dominio.Usuario;

/**
 * Classe Managed Bean Login
 * 
 * @author Fernanda Viana
 * @version 4.0.0 - 13/11/2018
 */

@ManagedBean(name = "loginMB")
@SessionScoped
public class LoginMB implements Serializable{
	
	private static final long serialVersionUID = 1L;
	//Dados para autentica��o
	private Usuario usuario = new Usuario();
	private boolean autenticado = false;

	//Dados para autentica��o
	private String login;
	private String senha;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public boolean isAutenticado() {
		return autenticado;
	}

	public void setAutenticado(boolean autenticado) {
		this.autenticado = autenticado;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	//M�todos

	public String home() {
		return "home.jsf?faces-redirect=true";
	}

	public String exibirLogin() {
		return "index.jsf?faces-redirect=true";
	}

	public String acaoAutenticar() {
		
		UsuarioDAO dao = new UsuarioDAO();

		// ler o usu�rio do banco
		Usuario usuDoBanco = dao.obter(this.login);
		
		if (usuDoBanco == null){
			FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login/Senha inv�lidos!", null);
			FacesContext.getCurrentInstance().addMessage(null, mensagem);
			// usu�rio n�o existe no banco
			return "index.jsf";
		}else {
			
			if (usuDoBanco.isSenhaCorreta(this.senha)){
				this.autenticado = true;
				this.usuario = usuDoBanco;
				
				return "home.jsf?faces-redirect=true";
			}
			else
			{
				FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login/Senha inv�lidos!", null);
				FacesContext.getCurrentInstance().addMessage(null, mensagem);
				
				return "index.jsf";
			}
		}
	}
	
	public String acaoSair() {
		this.usuario = null;
		this.autenticado = false;

		return "index.jsf?faces-redirect=true";
	}

}
