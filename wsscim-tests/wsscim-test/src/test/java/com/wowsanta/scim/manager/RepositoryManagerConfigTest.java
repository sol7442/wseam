package com.wowsanta.scim.manager;

import org.junit.Test;

import com.wowsanta.scim.hibernate.HibernateRepositoryFactory;
import com.wowsanta.scim.repository.ScimRepositoryManager;

public class RepositoryManagerConfigTest {
	public String fileName = "../../config/scim-hibernate-provider-repository.json";
	
	@Test
	public void save_modify_load_test() {
		ScimRepositoryManager mgr = ScimRepositoryManager.load(fileName);
		
		mgr.setRepositoryConfig("../../config/scim-hibernate-provider-factory.json");
		mgr.setRepositoryFactoryClass(HibernateRepositoryFactory.class.getName());
		mgr.addEntity("../../config/provider-entity-scim.json");
		
		
		System.out.println(mgr.toString(true));
		
		mgr.save(fileName);
	}

	private void create() {

	}
}
