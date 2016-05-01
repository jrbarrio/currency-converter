package com.roldan.currencyconverter.infrastructure.rest;

import java.util.Map;

public class Response {
	
	private String disclaimer;
	private String license;
	private String timestamp;
	private String base;
	private Map<String, Float> rates;
	
	public String getDisclaimer() {
		return disclaimer;
	}
	public void setDisclaimer(String disclaimer) {
		this.disclaimer = disclaimer;
	}
	public String getLicense() {
		return license;
	}
	public void setLicense(String license) {
		this.license = license;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getBase() {
		return base;
	}
	public void setBase(String base) {
		this.base = base;
	}
	public Map<String, Float> getRates() {
		return rates;
	}
	public void setRates(Map<String, Float> rates) {
		this.rates = rates;
	}
}
