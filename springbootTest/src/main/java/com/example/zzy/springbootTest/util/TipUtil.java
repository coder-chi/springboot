package com.example.zzy.springbootTest.util;

import org.apache.tomcat.util.http.fileupload.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class TipUtil {
	
	private static final Properties TIP = new Properties();

	static {
		InputStream in = TipUtil.class.getClassLoader().getResourceAsStream(
				"tip.properties");
		try {
			TIP.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			IOUtils.closeQuietly(in);
		}
		
	}

	public static final String getTip(String key) {
		Object tip = TIP.get(key);
		if (tip == null) {
			return "";
		} else {
			return "" + tip;
		}
	}
}
