package com.wowsanta.scim.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.wowsanta.scim.repository.ScimRepositoryException;
import com.wowsanta.scim.repository.ScimSession;

public class HibernateSession implements ScimSession {
	private Session session;
	private Transaction transaction;
	
	public HibernateSession(Session session) {
		this.session = session;
		this.transaction = this.session.beginTransaction();
	}
	
	public Session getSession() {
		return this.session;
	}
	
	@Override
	public void close() throws ScimRepositoryException {
		this.session.close();
	}
	@Override
	public void commit() throws ScimRepositoryException {
		this.transaction.commit();
	}
	@Override
	public void rollback() throws ScimRepositoryException {
		this.transaction.rollback();
	}
}
