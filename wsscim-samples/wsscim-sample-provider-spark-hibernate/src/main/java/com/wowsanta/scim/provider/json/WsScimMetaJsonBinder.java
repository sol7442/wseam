package com.wowsanta.scim.provider.json;

import java.io.IOException;

import com.google.gson.stream.JsonWriter;
import com.wowsanta.scim.entity.ScimMeta;

public class WsScimMetaJsonBinder {

	public static void toJson(JsonWriter writer) {

	}

	public static void toJson(JsonWriter writer, ScimMeta meta) throws IOException {
        writer.name("meta");
        writer.beginObject();
        if(meta.getResourceType() !=null) {
        	writer.name("resourceType").value(meta.getResourceType());
        }
        if(meta.getCreated() != null) {
        	writer.name("created").value(meta.getCreated().toInstant().toString());
        }
        if(meta.getLastModified() != null) {
        	writer.name("lastModified").value(meta.getLastModified().toInstant().toString());
        }
        if(meta.getExpired() != null) {
        	writer.name("expired").value(meta.getExpired().toInstant().toString());
        }
    	writer.name("active").value(meta.isActive());
        writer.endObject();
	}

}
