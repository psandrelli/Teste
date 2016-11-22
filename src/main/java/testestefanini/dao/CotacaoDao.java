package testestefanini.dao;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import testestefanini.model.Cotacao;
import testestefanini.service.ServiceCotacao;

public class CotacaoDao {
	ServiceCotacao serviceCotacao = new ServiceCotacao();
	
	public Cotacao obterUltimaCotacao() throws KeyManagementException, NoSuchAlgorithmException, IOException {
		return serviceCotacao.obterUltimaCotacao();
	}
	
	public Cotacao obterCotacaoData(String data) throws KeyManagementException, NoSuchAlgorithmException, IOException {
		return serviceCotacao.obterCotacaoData(data);
	}
	
	public Cotacao obterConversaoCotacaoMoeda(String moeda1, String moeda2, Integer valor) throws KeyManagementException, NoSuchAlgorithmException, IOException {
		return serviceCotacao.obterConversaoCotacaoMoeda(moeda1, moeda2, valor);
	}
}
