package com.aws.bluage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

	Properties prop = new Properties();

	public ConfigReader() throws IOException {
		try {
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir") + "/resources/config.properties");

			prop.load(ip);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String getUrl() {
		return prop.getProperty("url");
	}

}
