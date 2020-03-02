package com.wowsanta.scim.json;

import java.util.HashMap;
import java.util.Map;

public class ScimJsonConfiguration {
	private Map<String,ScimJsonInfo> scimJson = new HashMap<String, ScimJsonInfo>();

	public void put(String name, ScimJsonInfo info) {
		this.scimJson.put(name,info);
	}
	public Map<String,ScimJsonInfo> getScimJson() {
		return scimJson;
	}
	public void setScimJson(Map<String,ScimJsonInfo> scimJson) {
		this.scimJson = scimJson;
	}
}
