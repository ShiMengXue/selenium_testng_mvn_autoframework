package SET.test;

import static org.testng.AssertJUnit.assertEquals;

import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

import util.DataBase;
import util.ReadConfig;
import util.SetDriver;

public class Test2Test {

	public SetDriver driver;
	public DataBase database;
	public Properties props;
	public long default_timeout;

	@Factory(dataProviderClass = util.ReadConfig.class, dataProvider = "configureRun")
	public Test2Test(String userName, String password) {
		System.out.println("----------@@Factory-----------");
		System.out.println("----------"+password+"----------");
	}
	
	@DataProvider(name = "www")
	public static Object[][] www() throws IOException {
		Object[][] returnArray = { new Object[] { "username1", "password1" },
				new Object[] { "username2", "password2" },
				new Object[] { "username3", "password3" } };
		return returnArray;
	}

	@BeforeClass
	public void beforeClass() {
		props = ReadConfig.readValue();
		default_timeout = Long.parseLong(props.get("implicit_timeout_seconds")
				.toString());
		database = new DataBase(props.get("language").toString());
		System.out.println("----------@@BeforeClass-----------");
	}

	@BeforeMethod
	public void beforeMethod() {
		driver = new SetDriver(props.get("browser").toString(), props.get(
				"browser").toString());
		driver.manage().window().maximize();
		System.out.println("----------@BeforeMethod----");
	}

	@Test(enabled = true, groups = { "f1", "checkintest" }, dataProvider = "www")
	public void f1(String userName, String password) {
		System.out.println("----------"+userName+"----------");
		driver.get(database.getPageUrl("baidu"));
		assertEquals(
				driver.findElement(database.getPageEleXpathBy("baidu", "baike"))
						.getText(), database.getPageEleText("baidu", "baike"));
		System.out.println("----------f1------------");
	}

	@Test(enabled = true, groups = { "f2", "checkintest" })
	public void f2() {
		driver.get(database.getPageUrl("baidu"));
		driver.findElement(database.getPageEleXpathBy("baidu", "baike"))
				.click();
		String url = driver.getCurrentUrl();
		System.out.println(url);
		assertEquals(url, database.getPageEleHref("baidu", "baike"));
		driver.waitForElementVisible(
				database.getPageEleXpathBy("baike", "news"), default_timeout);
		driver.findElement(database.getPageEleXpathBy("baike", "news")).click();
		System.out.println("----------f2------------");
	}

	@AfterMethod
	public void afterMethod() {
		driver.quit();
		System.out.println("----------@AfterMethod-----------");
	}

	@AfterClass
	public void afterClass() {
		System.out.println("----------@@AfterClass-----------");
	}

}
