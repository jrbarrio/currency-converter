package com.roldan.currencyconverter.domain.model;

import java.util.Date;

public class Query {
	
	private String username;
	private String from;
	private String to;
	private Date date;
	private float rate;
	private Date postedTime;
	
	public Query(String username, String from, String to, Date date, float rate, Date postedTime) {
		super();
		this.username = username;
		this.from = from;
		this.to = to;
		this.date = date;
		this.rate = rate;
		this.postedTime = postedTime;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public float getRate() {
		return rate;
	}
	public void setRate(float rate) {
		this.rate = rate;
	}
	public Date getPostedTime() {
		return postedTime;
	}
	public void setPostedTime(Date postedTime) {
		this.postedTime = postedTime;
	}
}