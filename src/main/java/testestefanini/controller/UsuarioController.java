package testestefanini.controller;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import testestefanini.dao.UsuarioDao;
import testestefanini.model.Usuario;

@ManagedBean(name="usuarioController")
@SessionScoped
public class UsuarioController implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String nome;
	private String senha;
	
	private UsuarioDao usuarioDao;

	@PostConstruct()
	public void init() {
		usuarioDao = new UsuarioDao();
	}
	
	public String login() {
		try {
			Usuario usuario = usuarioDao.obterUsuario(nome, senha);
			if (usuario != null) {
				FacesContext ctx = FacesContext.getCurrentInstance();
				HttpSession session = (HttpSession) ctx.getExternalContext().getSession(false);
				session.setAttribute("logado", usuario);
				
				return "cotacao.xhtml?faces-redirect=true";
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public String logout() {
		FacesContext ctx = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) ctx.getExternalContext().getSession(false);
		session.setAttribute("", null);
		
		return "login.xhtml?faces-redirect=true";
	}
	
	public UsuarioDao getUsuarioDao() {
		return usuarioDao;
	}

	public void setUsuarioDao(UsuarioDao usuarioDao) {
		this.usuarioDao = usuarioDao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
}
