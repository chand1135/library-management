package com.atik.librarymanagement.util;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class JsonResponseUtil {

	public static String getResponse(String token, String name) {

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("message", token);
		jsonObject.put("name", name);
		return jsonObject.toString();
	}
}
