package com.aws.bluage;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSonReader {
	public static void main(String[] args) throws ParseException {
		String fileLocation = System.getProperty("user.dir") + "\\resources\\NewJSON.json";

		try {
			JSONParser parser = new JSONParser();
			JSONObject data = (JSONObject) parser.parse(new FileReader(fileLocation));
			JSONArray emails = (JSONArray) data.get("emails");
			for (int i = 0; i < emails.size(); i++) {
				System.out.println(emails.get(i));
			}
			JSONObject family = (JSONObject) data.get("family");
			System.out.println(family.get("spouse"));

			System.out.println(family.get("children").toString());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
