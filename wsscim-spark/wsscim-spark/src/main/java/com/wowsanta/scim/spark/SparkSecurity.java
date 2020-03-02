package com.wowsanta.scim.spark;

import spark.Spark;


public class SparkSecurity {
	private String keystoreFilePath;
	private String keystorePassword;
	private String truststoreFilePath;
	private String truststorePassword;
	
	public String getKeystoreFilePath() {
		return keystoreFilePath;
	}
	public void setKeystoreFilePath(String keystoreFilePath) {
		this.keystoreFilePath = keystoreFilePath;
	}
	public String getKeystorePassword() {
		return keystorePassword;
	}
	public void setKeystorePassword(String keystorePassword) {
		this.keystorePassword = keystorePassword;
	}
	public String getTruststoreFilePath() {
		return truststoreFilePath;
	}
	public void setTruststoreFilePath(String truststoreFilePath) {
		this.truststoreFilePath = truststoreFilePath;
	}
	public String getTruststorePassword() {
		return truststorePassword;
	}
	public void setTruststorePassword(String truststorePassword) {
		this.truststorePassword = truststorePassword;
	}
}
