package testestefanini.model;

import java.util.Date;

public class Cotacao {
	private Long id;
	private String cotacaoDia;
	private String moeda;
	private String moeda1;
	private String moeda2;
	private Date data;

	public Cotacao() {
		super();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCotacaoDia() {
		return cotacaoDia;
	}

	public void setCotacaoDia(String cotacaoDia) {
		this.cotacaoDia = cotacaoDia;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getMoeda() {
		return moeda;
	}

	public void setMoeda(String moeda) {
		this.moeda = moeda;
	}

	public String getMoeda1() {
		return moeda1;
	}

	public void setMoeda1(String moeda1) {
		this.moeda1 = moeda1;
	}

	public String getMoeda2() {
		return moeda2;
	}

	public void setMoeda2(String moeda2) {
		this.moeda2 = moeda2;
	}
	
}
