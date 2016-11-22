package testestefanini.service;

public class Credencial {
	private String appId = "6b1cdc3043724fe5842742ca179be498";
	private String apiBase = "https://openexchangerates.org/api/";
	private String httpLatest = "https://openexchangerates.org/api/latest.json?app_id=" + appId;
	private String httpHistorical = "https://openexchangerates.org/api/historical/";
	private String httpConvert = "https://openexchangerates.org/api/convert/";

	public String getAppId() {
		return appId;
	}

	public String getHttpLatest() {
		return httpLatest;
	}
	
	public String getHttpHistorical(String data) {
		return httpHistorical + data + ".json?app_id=" + appId;
	}
	
	public String getHttpConvert(String moeda1, String moeda2, Integer valor) {
		return httpConvert + valor.toString() + "/" + moeda1 + "/" + moeda2 + "?" + appId;
	}
}	
