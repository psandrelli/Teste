package testestefanini.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;

import testestefanini.model.Cotacao;

public class ServiceCotacao {
	
	public Cotacao obterUltimaCotacao() throws KeyManagementException, NoSuchAlgorithmException, IOException {
		Credencial credencial = new Credencial();
		Cotacao cotacao = new Cotacao();
		
		corrigeErroCertificado();
		
		ObjectMapper mapper = new ObjectMapper();
		
		URL url = new  URL(credencial.getHttpLatest());
		URLConnection conn = url.openConnection();
		JsonNode node = mapper.readTree(conn.getInputStream());
		
		List<String> lst = new ArrayList<String>();
		
		for(Iterator<Map.Entry<String, JsonNode>> it = node.get("rates").getFields(); it.hasNext();) {
        	lst.add(it.next().toString());
        }
		
		for(String s : lst) {
        	if (s.contains("BRL")) {
        		String array[] = new String[2];
        		array =s.split("=");
        		cotacao.setMoeda(array[0]);
        		cotacao.setCotacaoDia(array[1]);
        	}
        }
		
		return cotacao;
	}

	public Cotacao obterCotacaoData(String data) throws KeyManagementException, NoSuchAlgorithmException, IOException {
		Credencial credencial = new Credencial();
		Cotacao cotacao = new Cotacao();
		
		corrigeErroCertificado();
		
		ObjectMapper mapper = new ObjectMapper();
		
		URL url = new  URL(credencial.getHttpHistorical(data));
		URLConnection conn = url.openConnection();
		
		JsonNode node = mapper.readTree(conn.getInputStream());
		
		List<String> lst = new ArrayList<String>();
		
		for(Iterator<Map.Entry<String, JsonNode>> it = node.get("rates").getFields(); it.hasNext();) {
        	lst.add(it.next().toString());
        }
		
		for(String s : lst) {
        	if (s.contains("BRL")) {
        		String array[] = new String[2];
        		array =s.split("=");
        		cotacao.setMoeda(array[0]);
        		cotacao.setCotacaoDia(array[1]);
        	}
        }
		
		return cotacao;
	}

	public Cotacao obterConversaoCotacaoMoeda(String moeda1, String moeda2, Integer valor) throws KeyManagementException, NoSuchAlgorithmException, IOException {
		Credencial credencial = new Credencial();
		Cotacao cotacao = new Cotacao();
		
		corrigeErroCertificado();
		
		ObjectMapper mapper = new ObjectMapper();
		
		URL url = new  URL(credencial.getHttpConvert(moeda1, moeda2, valor));
		URLConnection conn = url.openConnection();
		
		JsonNode node = mapper.readTree(conn.getInputStream());
		
		List<String> lst = new ArrayList<String>();
		
		for(Iterator<Map.Entry<String, JsonNode>> it = node.get("rates").getFields(); it.hasNext();) {
        	lst.add(it.next().toString());
        }
		
		return cotacao;
	}
	
	/* Correção de erro de certificado */
	private void corrigeErroCertificado() throws NoSuchAlgorithmException, KeyManagementException {
		TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
			public java.security.cert.X509Certificate[] getAcceptedIssuers() {
				return null;
			}

			public void checkClientTrusted(X509Certificate[] certs,
					String authType) {
			}

			public void checkServerTrusted(X509Certificate[] certs,
					String authType) {
			}

		} };

		SSLContext sc = SSLContext.getInstance("SSL");
		sc.init(null, trustAllCerts, new java.security.SecureRandom());
		HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

		HostnameVerifier allHostsValid = new HostnameVerifier() {
			public boolean verify(String hostname, SSLSession session) {
				return true;
			}
		};

		HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
	}
}
