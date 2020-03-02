package com.wowsanta.scim.provider.json;

import java.io.IOException;
import java.time.Instant;
import java.util.Date;

import com.google.gson.stream.JsonReader;
import com.wowsanta.scim.entity.ScimMeta;
import com.wowsanta.scim.provider.model.WsScimMeta;

public class WsScimMetaJsonParser {

	public static ScimMeta fromJson(JsonReader reader) throws IOException {
		WsScimMeta meta = new WsScimMeta();
		reader.beginObject();
		while (reader.hasNext()) {
			switch (reader.nextName()) {
			case "resourceType":
				meta.setResourceType(reader.nextString());
				break;
			case "created":
				meta.setCreated(Date.from(Instant.parse(reader.nextString())));
				break;
			case "lastModified":
				meta.setLastModified(Date.from(Instant.parse(reader.nextString())));
				break;
			case "expired":
				meta.setExpired(Date.from(Instant.parse(reader.nextString())));
				break;
			case "active":
				meta.setActive((reader.nextBoolean()));
				break;
			default:
				System.out.println(reader.nextString());
				break;
			}
		}
		reader.endObject();
		return meta;
	}

}
