package com.wowsanta.scim.hibernate;

import java.io.File;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.wowsanta.scim.repository.ScimSession;
import com.wowsanta.scim.repository.ScimSessionFactory;

public class HibernateSessonFactory extends ScimSessionFactory {

	private SessionFactory sessionFactory = null;
	
	public HibernateSessonFactory(String config_file) {
		
		Configuration config = new Configuration();
		config.configure(new File(config_file));
		
		sessionFactory = config.buildSessionFactory();
	}


	@Override
	public ScimSession openSession() {
		return new HibernateSession(this.sessionFactory.openSession());
	}


	@Override
	public void close() {
		this.sessionFactory.close();
	}

}
