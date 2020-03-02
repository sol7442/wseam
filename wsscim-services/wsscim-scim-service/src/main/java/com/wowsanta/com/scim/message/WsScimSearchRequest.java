package com.wowsanta.com.scim.message;

import java.util.List;

import com.wowsanta.scim.entity.ScimSearchRequest;
import com.wowsanta.scim.json.ScimJsonException;
import com.wowsanta.scim.json.ScimMessageJsonBinder;
import com.wowsanta.scim.json.ScimResourceJsonBinder;

public class WsScimSearchRequest implements ScimSearchRequest {
	private List<String> attributes;
	private String filter;
	private String sortBy;
	private String sortOrder;
	private int startIndex;
	private int count;
	
	@Override
	public List<String> getAttributes() {
		return this.attributes;
	}

	@Override
	public List<String> getExcludedAttributes() {
		return null;
	}

	@Override
	public String getFilter() {
		return this.filter;
	}

	@Override
	public String getSortBy() {
		return this.sortBy;
	}

	@Override
	public int getStartIndex() {
		return this.startIndex;
	}

	@Override
	public int getCount() {
		return this.count;
	}

	@Override
	public String getSortOrder() {
		return this.sortOrder;
	}

	public void setAttributes(List<String> attributes) {
		this.attributes = attributes;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

	public void setStartIndex(int index) {
		this.startIndex = index;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String toString(boolean pretty ,ScimMessageJsonBinder msg_binder, ScimResourceJsonBinder res_binder) {
		String json = "";
		try {
			msg_binder.setResourceBinder(res_binder);
			json =  msg_binder.toJson(this);
		} catch (ScimJsonException e) {
			e.printStackTrace();
		}
		return json;
	}
	  
}
