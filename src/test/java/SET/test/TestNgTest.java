package SET.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import org.ho.yaml.Yaml;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;

public class TestNgTest {

	public WebDriver driver;
	public FirefoxProfile firefoxProfile;

	// @Factory(dataProviderClass = utils.SetTestClassNG.class, dataProvider =
	// "configureRun")
	public TestNgTest() {
		super();
		// TODO Auto-generated constructor stub
	}

	@BeforeClass(alwaysRun = true)
	public void setupClass() {
		System.setProperty("webdriver.firefox.bin",
				"D:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
		File file = new File(
				"C:\\Users\\mengxue.shi\\Desktop\\Selenium\\Tools\\firebug-1.11.4-fx.xpi");
		firefoxProfile = new FirefoxProfile();
		try {
			firefoxProfile.addExtension(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		firefoxProfile.setPreference("extensions.firebug.currentVersion",
				"1.11.4"); // 避免启动画面
		System.out.println("<@BeforeClass> run");
	}

	@BeforeMethod(alwaysRun = true)
	public void setupTest() throws Exception {

		driver = new FirefoxDriver(firefoxProfile);
		System.out.println("<@BeforeMethod> run");
	}

	@Test(enabled = true, groups = { "functest", "checkintest" })
	public void f1() {

		assertTrue(true);
	}

	@Test(enabled = true, groups = { "functest", "checkintest" })
	public void f2() {

		assertTrue(true);
	}

	@AfterMethod(alwaysRun = true)
	public void AfterMethod() {
		System.out.println("<@AfterMethod> run");
		driver.quit();
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		System.out.println("<@AfterClass> run");
	}

}
