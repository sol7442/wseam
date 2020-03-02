package com.wowsanta.scim.hibernate;


import org.hibernate.Session;

import com.wowsanta.scim.repository.ScimRepository;
import com.wowsanta.scim.repository.ScimSession;

public abstract class HibernateRepository implements ScimRepository {
	protected Session session;
	@Override
	public void setSession(ScimSession session) {
		if (session instanceof HibernateSession) {
			HibernateSession hibernate_session = (HibernateSession) session;
			this.session = hibernate_session.getSession();
		}
	}
}
