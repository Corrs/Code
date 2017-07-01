package com.chinesejr.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="static")
public class StaticProperties {
	private String maxFileSize;
	private String maxRequestSize;
	private String upload;

	public String getMaxFileSize() {
		return maxFileSize;
	}

	public String getMaxRequestSize() {
		return maxRequestSize;
	}

	public String getUpload() {
		return upload;
	}

	public void setMaxFileSize(String maxFileSize) {
		this.maxFileSize = maxFileSize;
	}

	public void setMaxRequestSize(String maxRequestSize) {
		this.maxRequestSize = maxRequestSize;
	}

	public void setUpload(String upload) {
		this.upload = upload;
	}
	
	
}
