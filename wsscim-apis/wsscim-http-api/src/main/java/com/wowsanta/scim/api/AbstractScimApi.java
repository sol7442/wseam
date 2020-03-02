package com.wowsanta.scim.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.HttpClientUtils;

import com.wowsanta.scim.ScimException;
import com.wowsanta.scim.ScimState;
import com.wowsanta.scim.json.ScimJsonException;
import com.wowsanta.scim.json.ScimResourceJsonParser;
import com.wowsanta.scim.json.ScimStateJsonBinder;
import com.wowsanta.scim.json.ScimStateJsonParser;

import ch.qos.logback.classic.Logger;


public abstract class AbstractScimApi  {
	protected String url;
	
	protected ScimTargetInfo target;
	public void setTargetInfo(ScimTargetInfo target) {
		this.target = target;
	}
	protected String responseResult(CloseableHttpResponse response) throws IOException, ScimException {
		String result = "";
		StringBuilder buffer = new StringBuilder();
		int http_res_code = response.getStatusLine().getStatusCode();
		HttpEntity entity = response.getEntity();
		InputStream content = entity.getContent();
		
		String line;
		BufferedReader reader = new BufferedReader(new InputStreamReader(content));
		while ((line = reader.readLine()) != null) {
			buffer.append(line);
		}
		result = buffer.toString();
		
		reader.close();
		HttpClientUtils.closeQuietly(response);
		
		ScimState state = ScimState.Ok;
		if( http_res_code >= HttpStatus.SC_OK && http_res_code <= HttpStatus.SC_MULTI_STATUS) {
			//result = buffer.toString();
		}else {
			try {
				state = ScimStateJsonParser.fromJson(result);
			} catch (ScimJsonException e) {
				state = ScimState.InternalServerError;
			}
			throw new ScimException("HTTP ERROR : " + http_res_code , state);
		}
		return result;
	}

	protected HttpUriRequest requestHeader(HttpUriRequest request) {
		request.addHeader("Content-Type", "application/json;UTF-8");
		request.addHeader("Accept-Charset", "UTF-8");
		return request;
	}
	
	protected String ErrorHandler(String result, ScimState state) {
		try {
			result = ScimStateJsonBinder.toJson(state);
		} catch (ScimJsonException e1) {
			e1.printStackTrace();
		}
		return result;
	}

	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
