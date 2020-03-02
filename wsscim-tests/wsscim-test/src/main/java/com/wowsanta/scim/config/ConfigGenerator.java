package com.wowsanta.scim.config;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

import com.wowsanta.scim.annotation.SCIM_JSON;

public class ConfigGenerator {
	public static void main(String[] args) {
		PathMatcher bin_matcher = FileSystems.getDefault().getPathMatcher("glob:**/bin/main/**");
		try {
		    Files.walkFileTree(Paths.get("../../"), new SimpleFileVisitor<Path>() {
		        @Override
		        public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
		        	if(bin_matcher.matches(path)) {
		        		int start_index = path.toString().lastIndexOf("\\bin\\main\\");
		        		int end_index = path.toString().lastIndexOf(".class");
		        		String class_file = path.toString().substring(start_index + 10,end_index);
		        		String class_name = class_file.replace("\\",".");
		        		try {
							Class clazz = Class.forName(class_name);
							SCIM_JSON annotation = (SCIM_JSON) clazz.getAnnotation(SCIM_JSON.class);
							if(annotation != null) {
								System.out.println(annotation.type());
								System.out.println(annotation.entity());
								System.out.println(clazz.getName());
							}
							
						} catch (ClassNotFoundException e) {
							e.printStackTrace();
						}
		        	}
		            return FileVisitResult.CONTINUE;
		        }
		    });
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
