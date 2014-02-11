package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;

import org.ho.yaml.Yaml;
import org.openqa.selenium.By;
import org.testng.log4testng.Logger;

public class DataBase {

	private static final Logger LOG = Logger.getLogger(DataBase.class);

	// Current database.
	private HashMap hmap;

	// Current language.
	public String language;

	public DataBase(String language) {
		this.language = language;
		try {
			hmap = Yaml.loadType(new FileInputStream(new File(
					"data/ele.yaml")), HashMap.class);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getPageUrl(String page) {
		return (String) ((HashMap) hmap.get(page)).get("url");
	}

	public String getPageEleId(String page, String ele) {
		return (String) ((HashMap) ((HashMap) hmap.get(page)).get(ele))
				.get("id");
	}

	public String getPageEleXpath(String page, String ele) {
		return (String) ((HashMap) ((HashMap) hmap.get(page)).get(ele))
				.get("xpath");
	}

	public String getPageEleHref(String page, String ele) {
		return (String) ((HashMap) ((HashMap) hmap.get(page)).get(ele))
				.get("href");
	}

	public String getPageEleText(String page, String ele) {
		return (String) ((HashMap) ((HashMap) ((HashMap) hmap.get(page))
				.get(ele)).get("text")).get(language);
	}
	
	public By getPageEleIdBy(String page, String ele) {
		return By.id((String) ((HashMap) ((HashMap) hmap.get(page)).get(ele))
				.get("id"));
	}

	public By getPageEleXpathBy(String page, String ele) {
		return By.xpath((String) ((HashMap) ((HashMap) hmap.get(page)).get(ele))
				.get("xpath"));
	}

}
