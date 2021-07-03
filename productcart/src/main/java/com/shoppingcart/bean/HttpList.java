package com.shoppingcart.bean;

import org.springframework.stereotype.Component;

@Component
public class HttpList {
	private String httpUrl;
	private String status;
	public String getHttpUrl() {
		return httpUrl;
	}
	public void setHttpUrl(String httpUrl) {
		this.httpUrl = httpUrl;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	

}
