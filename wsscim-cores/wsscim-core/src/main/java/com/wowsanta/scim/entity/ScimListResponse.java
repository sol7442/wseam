package com.wowsanta.scim.entity;

import java.util.List;

public interface ScimListResponse {
	public int getTotalResults();  
	public List<ScimResource> getResources();  
	public int getStartIndex();  
	public int getItemsPerPage();
	public void setTotalResults(int size);
	public void setStartIndex(int startIndex);
	public void setItemsPerPage(int count);
	public void addResource(ScimResource scimResource);  
}
