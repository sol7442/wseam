package com.wowsanta.scim.annotation;

import java.io.File;

import com.wowsanta.scim.annotation.SCIM_REPOSITORY;
import com.wowsanta.scim.annotation.ScimAnnotationHandler;
import com.wowsanta.scim.entity.ScimEntityHandler;
import com.wowsanta.scim.repository.ScimRepositoryInfo;
import com.wowsanta.scim.service.ScimServiceManager;

@SuppressWarnings({"rawtypes","unchecked"})
public class ScimRepositoryAnnotatonHandler implements ScimAnnotationHandler{
	@Override
	public boolean visit(File file, String class_name) {
		try {
			Class clazz = Class.forName(class_name);
			SCIM_REPOSITORY repository_annotation = (SCIM_REPOSITORY) clazz.getAnnotation(SCIM_REPOSITORY.class);
			if(repository_annotation != null) {
				String entity_name = repository_annotation.entity();
				
				Class repository_class = Class.forName(class_name);
				
				ScimRepositoryInfo repository_info = new ScimRepositoryInfo();
				repository_info.setRepositoryFile(file.getCanonicalPath());
				repository_info.setRepositoryName(class_name);
				repository_info.setRepositoryClass(repository_class);
				

				ScimEntityHandler handler = ScimServiceManager.getInstance().getHandler(entity_name);
				if(handler == null) {
					handler = new ScimEntityHandler();
					ScimServiceManager.getInstance().addHandler(entity_name, handler);
				}
				handler.setRepository(repository_info);
				

				
			}
			return true;
		}catch (Exception e) {
			System.out.println(class_name + " : " + e.getMessage());
		}
		return false;
	}

}
