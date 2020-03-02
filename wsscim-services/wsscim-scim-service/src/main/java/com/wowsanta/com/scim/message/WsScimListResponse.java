package com.wowsanta.com.scim.message;

import java.util.ArrayList;
import java.util.List;

import com.wowsanta.scim.entity.ScimListResponse;
import com.wowsanta.scim.entity.ScimResource;
import com.wowsanta.scim.json.ScimJsonException;
import com.wowsanta.scim.json.ScimMessageJsonBinder;
import com.wowsanta.scim.json.ScimResourceJsonBinder;

public class WsScimListResponse implements ScimListResponse{
	private int totalResults;
	private int itemsPerPage;
	private int startIndex;
	
	private List<ScimResource> resources;

	public int getTotalResults() {
		return totalResults;
	}

	public void setTotalResults(int totalResults) {
		this.totalResults = totalResults;
	}

	public int getItemsPerPage() {
		return itemsPerPage;
	}

	public void setItemsPerPage(int itemsPerPage) {
		this.itemsPerPage = itemsPerPage;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public void addResource(ScimResource resource) {
		if(this.resources == null) {
			this.resources = new ArrayList<ScimResource>();
		}
		this.resources.add(resource);
	}
	public List<ScimResource> getResources() {
		return resources;
	}

	public void setResources(List<ScimResource> resources) {
		this.resources = resources;
	}

	public String toString(boolean pretty, ScimMessageJsonBinder msg_binder, ScimResourceJsonBinder res_binder) {
		String json = "";
		try {
			msg_binder.setPretty(pretty);
			msg_binder.setResourceBinder(res_binder);
			json =  msg_binder.toJson(this);
		} catch (ScimJsonException e) {
			e.printStackTrace();
		}
		return json;
	}
}
