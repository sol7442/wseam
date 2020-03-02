package com.wowsanta.scim.annotation;

import java.io.File;

public interface ScimAnnotationHandler {
	public static final String DEFAULT_NULL = "";
	public static final String DEFAULT_CONTROL = "DEFAULT_CONTROL";
	public static final String DEFAULT_SERVICE = "DEFAULT_SERVICE";
	public static final String DEFAULT_REPOSITORY = "DEFAULT_REPOSITORY";
	public boolean visit(File file, String class_name);
}
