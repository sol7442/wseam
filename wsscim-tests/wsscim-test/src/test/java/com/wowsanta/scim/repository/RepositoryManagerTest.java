package com.wowsanta.scim.repository;

import org.junit.Test;

import com.wowsanta.hibernate.scim.extra.WsScimUser;
import com.wowsanta.scim.object.extra.ScimExtObjectType;
import com.wowsanta.scim.repository.jpa.HibernateConfiguration;
import com.wowsanta.scim.repository.jpa.HibernateRepository;


public class RepositoryManagerTest {
	
	String fileName  = "../../config/scim-hibernate-repository.json";
	String data_info = "../../config/scim-data-info.json";
	@Test
	public void load_modify_save_test() {
		try {
			
			ScimRepositoryManager.load();
			
//			
//			ScimEntityInfo user_data = new ScimEntityInfo();
//			user_data.setEntityClassName(WsScimUser.class.getCanonicalName());
//			user_data.setRepositoryClassName(HibernateRepository.class.getCanonicalName());
//			
//			WsScimDataManager.getInstance().addDataInfo(ScimExtObjectType.USER, user_data);
//		
//			WsScimDataManager.getInstance().build(
//					HibernateConfiguration.class.getCanonicalName(),
//					fileName);
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
