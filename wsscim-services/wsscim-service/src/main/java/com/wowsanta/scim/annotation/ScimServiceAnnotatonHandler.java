package com.wowsanta.scim.annotation;

import java.io.File;

import com.wowsanta.scim.annotation.SCIM_SERVICE;
import com.wowsanta.scim.annotation.ScimAnnotationHandler;
import com.wowsanta.scim.entity.ScimEntityHandler;
import com.wowsanta.scim.service.ScimServiceInfo;
import com.wowsanta.scim.service.ScimServiceManager;

@SuppressWarnings({"rawtypes","unchecked"})
public class ScimServiceAnnotatonHandler implements ScimAnnotationHandler{
	@Override
	public boolean visit(File file, String class_name) {
		try {
			Class clazz = Class.forName(class_name);
			SCIM_SERVICE service_annotation = (SCIM_SERVICE) clazz.getAnnotation(SCIM_SERVICE.class);
			if(service_annotation != null) {
				String service_name = service_annotation.name();
				String entity_name  = service_annotation.entity();
				
				Class service_class = Class.forName(class_name);
				
				ScimServiceInfo service_info = new ScimServiceInfo();
				service_info.setServiceFile(file.getCanonicalPath());
				service_info.setServiceName(service_name);
				service_info.setServiceClass(service_class);
				
				ScimEntityHandler handler = ScimServiceManager.getInstance().getHandler(entity_name);
				if(handler == null) {
					handler = new ScimEntityHandler();
					ScimServiceManager.getInstance().addHandler(entity_name, handler);
				}
				handler.setService(service_info);
				
			}
			return true;
		}catch (Exception e) {
			System.out.println(class_name + " :: " + e.getMessage());
			e.printStackTrace();
		}
		return false;
	}

}
