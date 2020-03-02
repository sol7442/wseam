package com.wowsanta.scim.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.wowsanta.scim.type.SCIM_TARGET_TYPE;


@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface SCIM_JSON {
	String target() default SCIM_TARGET_TYPE.LOCAL_TARGET ;
	String entity();
	String type();
	double version() default 1.0;
}
