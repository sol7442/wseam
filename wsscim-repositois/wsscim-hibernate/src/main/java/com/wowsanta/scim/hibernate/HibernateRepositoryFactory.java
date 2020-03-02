package com.wowsanta.scim.hibernate;



import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wowsanta.scim.entity.ScimEntityHandler;
import com.wowsanta.scim.repository.HibernateDefaultRepository;
import com.wowsanta.scim.repository.ScimRepository;
import com.wowsanta.scim.repository.ScimRepositoryException;
import com.wowsanta.scim.repository.ScimRepositoryFactory;
import com.wowsanta.scim.repository.ScimRepositoryInfo;
import com.wowsanta.scim.repository.ScimSession;
import com.wowsanta.scim.service.ScimServiceManager;



public class HibernateRepositoryFactory implements ScimRepositoryFactory {
	Logger logger = LoggerFactory.getLogger(HibernateRepositoryFactory.class);
	
	private SessionFactory sessionFactory;
	
	@Override
	public void close() throws ScimRepositoryException {
		this.sessionFactory.close();
	}
	
	@Override
	public void build(String file_name) {
		try {
			HibernateConfiguration config = HibernateConfiguration.load(file_name);
			
			sessionFactory = config.build();
			
		} catch (ScimRepositoryException e) {
			logger.error("{} ",file_name,e);
		}
	}
	
	@Override
	public HibernateSession openSession() throws ScimRepositoryException {
		return new HibernateSession(this.sessionFactory.openSession());
	}

	@Override
	public ScimRepository getRepository(String entity, ScimSession session) throws ScimRepositoryException {
		HibernateRepository repository = null;
		try {

			ScimEntityHandler service_info = ScimServiceManager.getInstance().getHandler(entity);
			ScimRepositoryInfo repository_info = service_info.getRepository() ;
			if(repository_info != null) {
				repository = newRepository(repository_info.getRepositoryClass());
			}else {
				repository = new HibernateDefaultRepository();
				((HibernateDefaultRepository) repository).setResourceClass(service_info.getEntity().getEntityClass());
			}
			repository.setSession(session);
			
		} catch (Exception e) {
			throw new ScimRepositoryException(entity + " : ERROR : ",e);
		}
		return repository;
	}

	@Override
	@SuppressWarnings("rawtypes")
	public HibernateRepository newRepository(Class repository_class) throws ScimRepositoryException {
		
		HibernateRepository repository = null;
		try {
			if(repository_class == null) {
				repository = new HibernateDefaultRepository();
			}else {
				repository = (HibernateRepository) repository_class.newInstance();
			}
		} catch (Exception e) {
			throw new ScimRepositoryException("Create Object Error : ",e);
		} 
		
		return repository;
	}

}
