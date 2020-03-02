package com.wowsanta.scim.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.wowsanta.scim.type.ScimServerType;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface SCIM_ENTITY {
	String name() 	default "";
	String system() default "";
	String type() 	default "";//
	
	//ScimServerType type() default ScimServerType.SCIM_PROVIDER;
	//SCIM_ENTITY_CONTROL control() default @SCIM_ENTITY_CONTROL(method = "", path = "");
	//SCIM_SERVICE service() default @SCIM_SERVICE(entity = "", name = "", value = "");
	//
	//boolean primitive() default false;
	double version() default 1.0;
}
