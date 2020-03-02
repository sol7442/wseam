package com.wowsanta.scim.hibernate;

import com.wowsanta.scim.entity.ScimResource;

public interface HibernateEntity {
	public void copy(ScimResource resource);
}
