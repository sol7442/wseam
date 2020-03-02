package com.wowsanta.scim.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface SCIM_ENTITY_CONTROL {
	String path()  ;
	String method();
	String transformer() 	default "";
	String entity()  		default "";
	String service() 		default "";
	double version() 		default 1.0;
}
