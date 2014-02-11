package util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

import org.testng.annotations.DataProvider;

public class ReadConfig {

	@DataProvider(name = "configureRun")
	public static Object[][] configureRun() throws IOException {
		Object[][] returnArray = { new Object[] { "username1", "password1" },
				new Object[] { "username2", "password2" },
				new Object[] { "username3", "password3" } };
		return returnArray;
	}

	public static Properties readValue() {
		Properties props = new Properties();
		try {
			InputStream in = new BufferedInputStream(new FileInputStream(
					"E:\\workapace-ccm-ui\\test\\defaultConfigurationValues"));
			props.load(in);
			Enumeration en = props.propertyNames();
			while (en.hasMoreElements()) {
				String key = (String) en.nextElement();
				String Property = props.getProperty(key);
				System.out.println(key + "=" + Property);
			}
			return props;
		} catch (Exception e) {
			e.printStackTrace();
			return props;
		}
	}

}
