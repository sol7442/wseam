package com.wowsanta.scim.annotation;

import java.io.File;

import com.wowsanta.scim.annotation.SCIM_ENTITY_CONTROL;
import com.wowsanta.scim.annotation.ScimAnnotationHandler;
import com.wowsanta.scim.control.ScimControlInfo;
import com.wowsanta.scim.entity.ScimEntityHandler;
import com.wowsanta.scim.service.ScimServiceManager;

@SuppressWarnings({"rawtypes","unchecked"})
public class ScimControlAnnotationHandler implements ScimAnnotationHandler {
	
	@Override
	public boolean visit(File file, String class_name) {
		try {
			Class control_class = Class.forName(class_name);
			SCIM_ENTITY_CONTROL control_annotation = (SCIM_ENTITY_CONTROL) control_class.getAnnotation(SCIM_ENTITY_CONTROL.class);
			if(control_annotation != null) {
					
					String control_name   = control_annotation.service();
					String entity_name    = control_annotation.entity();
					String control_path   = control_annotation.path();
					String control_method = control_annotation.method();
					
					ScimControlInfo control_info = new ScimControlInfo();
					control_info.setPath(control_path);
					control_info.setMethod(control_method);
					control_info.setName(control_name);
					control_info.setControlName(class_name);
					control_info.setControlFile(file.getCanonicalPath());
					control_info.setControlClass(control_class);
					
//					ScimEntityHandler handler = ScimServiceManager.getInstance().getHandler(entity_name);
//					if(handler == null) {
//						handler = new ScimEntityHandler();
//						ScimServiceManager.getInstance().addHandler(entity_name, handler);
//					}
//					handler.putControl(control_name, control_info);
					
					ScimServiceManager.getInstance().addHandler(control_info);
			}
			return true;
		}catch (Exception e) {
			System.out.println(class_name + " : " + e.getMessage());
		}
		return false;
	}

}
