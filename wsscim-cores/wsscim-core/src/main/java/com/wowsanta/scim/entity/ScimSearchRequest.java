package com.wowsanta.scim.entity;

import java.util.List;

public interface ScimSearchRequest {
	public List<String> getAttributes();
	public List<String> getExcludedAttributes();
	public String getFilter();
	public String getSortBy();
	public String getSortOrder();
	public int getStartIndex();
	public int getCount();
	public void setStartIndex(int nextInt);
	public void setCount(int nextInt);
	public void setFilter(String nextString);
}
