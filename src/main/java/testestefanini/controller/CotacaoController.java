package testestefanini.controller;

import java.io.IOException;
import java.io.Serializable;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import testestefanini.dao.CotacaoDao;
import testestefanini.model.Cotacao;

@ManagedBean(name="cotacaoController")
@SessionScoped
public class CotacaoController implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private CotacaoDao cotacaoDao;
	private List<Cotacao> listaHistoricoCotacao;
	private Cotacao cotacaoDia;
	private Cotacao cotacaoMoeda;
	private Cotacao cotacaoData;
	private String data;
	
	@PostConstruct()
	public void init() {
		cotacaoDao = new CotacaoDao();
		obterUltimaCotacaoDia();
	}
	
	public void obterUltimaCotacaoDia() {
		try {
			setCotacaoDia(cotacaoDao.obterUltimaCotacao());
			
		} catch (KeyManagementException | NoSuchAlgorithmException | IOException e) {
			e.printStackTrace();
		}
	}

	public void obterCotacaoData() {
		try {
			setCotacaoData(cotacaoDao.obterCotacaoData(data));
		} catch (KeyManagementException | NoSuchAlgorithmException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public void obterConversaoCotacaoMoeda(String moeda1, String moeda2, Integer valor) {
		try {
			setCotacaoMoeda(cotacaoDao.obterConversaoCotacaoMoeda(moeda1, moeda2, valor));
		} catch (KeyManagementException | NoSuchAlgorithmException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<Cotacao> getListaHistoricoCotacao() {
		return listaHistoricoCotacao;
	}

	public void setListaHistoricoCotacao(List<Cotacao> listaHistoricoCotacao) {
		this.listaHistoricoCotacao = listaHistoricoCotacao;
	}

	public Cotacao getCotacaoDia() {
		return cotacaoDia;
	}

	public void setCotacaoDia(Cotacao cotacaoDia) {
		this.cotacaoDia = cotacaoDia;
	}

	public Cotacao getCotacaoMoeda() {
		return cotacaoMoeda;
	}

	public void setCotacaoMoeda(Cotacao cotacaoMoeda) {
		this.cotacaoMoeda = cotacaoMoeda;
	}

	public Cotacao getCotacaoData() {
		return cotacaoData;
	}

	public void setCotacaoData(Cotacao cotacaoData) {
		this.cotacaoData = cotacaoData;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
}
