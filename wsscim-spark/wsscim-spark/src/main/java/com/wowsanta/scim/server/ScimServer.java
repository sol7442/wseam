package com.wowsanta.scim.server;

import java.util.List;

public interface ScimServer{
	public void initialize(List<String> file_name) ;
	public void start();
	public void stop();
}
