package com.wowsanta.scim.entity;

import java.util.List;

public interface ScimGroup extends ScimResource {
	public List<ScimUser> getUsers();
}
