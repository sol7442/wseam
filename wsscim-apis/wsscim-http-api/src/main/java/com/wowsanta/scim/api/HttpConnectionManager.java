package com.wowsanta.scim.api;

import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;

public class HttpConnectionManager {
	private static HttpConnectionManager instance;
	
	private SSLConnectionSocketFactory sslConnectionSocketFactory;
	private PoolingHttpClientConnectionManager poolManager;
	public static HttpConnectionManager getInstance() {
		if(instance == null) {
			instance = new HttpConnectionManager();
		}
		return instance;
	}
	
	public void initialize() throws ScimApiException {
		try {
	    	SSLContextBuilder builder = new SSLContextBuilder();
	    	builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
	    	sslConnectionSocketFactory = new SSLConnectionSocketFactory(builder.build(),SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
	    	Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
	    	        .register("http", new PlainConnectionSocketFactory())
	    	        .register("https", sslConnectionSocketFactory)
	    	        .build();
	    	
	    	poolManager = new PoolingHttpClientConnectionManager(registry);
	    	poolManager.setMaxTotal(2000);
		}catch (Exception e) {
			throw new ScimApiException("INITIALIZE FAILED : ",e);
		}



	}
	
	public CloseableHttpClient get() {
    	return HttpClients.custom()
	    .setSSLSocketFactory(sslConnectionSocketFactory)
	    .setConnectionManager(poolManager)
	    .build();
	}
}
