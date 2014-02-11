package util;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Keyboard;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.internal.FindsByClassName;
import org.openqa.selenium.internal.FindsById;
import org.openqa.selenium.internal.FindsByLinkText;
import org.openqa.selenium.internal.FindsByName;
import org.openqa.selenium.internal.FindsByTagName;
import org.openqa.selenium.internal.FindsByXPath;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.log4testng.Logger;

import util.enums.Browsers;

public class SetDriver implements WebDriver, FindsById, FindsByXPath,
		HasInputDevices, HasCapabilities, TakesScreenshot {

	private static final Logger LOG = Logger.getLogger(SetDriver.class);
	public RemoteWebDriver driver;
	public String browser;
	public String platform;
	private long defaultWait = 5;

	public SetDriver(String browser, String platform) {
		this.browser = browser;
		this.platform = platform;
		configureDriver(browser);
	}

	private void configureDriver(String browser) {
		LOG.info("Configuring driver");
		if (driver != null) {
			driver.quit();
			driver = null;
		}
		if (driver == null) {
			if (browser.equalsIgnoreCase(Browsers.FIREFOX.getName())) {
				System.setProperty("webdriver.firefox.bin",
						"D:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
				File file = new File(
						"C:\\Users\\mengxue.shi\\Desktop\\Selenium\\Tools\\firebug-1.11.4-fx.xpi");
				FirefoxProfile firefoxProfile = new FirefoxProfile();
				try {
					firefoxProfile.addExtension(file);
				} catch (IOException e) {
					e.printStackTrace();
				}
				firefoxProfile.setPreference(
						"extensions.firebug.currentVersion", "1.11.4"); // 避免启动画面
				driver = new FirefoxDriver(firefoxProfile);
				LOG.info("driver set to local FF driver");
			} else if (browser.equalsIgnoreCase(Browsers.CHROME.getName())) {
				System.setProperty("webdriver.chrome.driver",
						"C:\\ccmdriver\\chromedriver.exe");
				driver = new ChromeDriver();
				LOG.info("driver set to local CH driver");
			} else if (browser.equalsIgnoreCase(Browsers.SAFARI.getName())) {
				driver = new SafariDriver();
				LOG.info("driver set to local SA driver");
			} else if (browser.equalsIgnoreCase(Browsers.IE.getName())) {
				System.setProperty("webdriver.ie.driver",
						"C:\\ccmdriver\\IEDriverServer.exe");
				driver = new InternetExplorerDriver();
				LOG.info("driver set to local IE driver");
			}
		}

	}

	/**
	 * Close.
	 */
	public void close() {
		driver.close();
	}

	public WebElement highlight(WebElement ele){
		try {
			driver.executeScript("arguments[0].style.border = '2px solid red'", ele);
			Thread.sleep(500);
			driver.executeScript("arguments[0].style.border = '1px solid silver'", ele);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ele;
	}
	
	
	/**
	 * Find element.
	 * 
	 * @param arg0
	 *            the arg0
	 * @return the web element
	 * @throws org.openqa.selenium.NoSuchElementException
	 *             If no matching elements are found
	 */
	public WebElement findElement(By arg0) throws NoSuchElementException {
		return highlight(driver.findElement(arg0));
	}

	/**
	 * Find elements.
	 * 
	 * @param arg0
	 *            the arg0
	 * @return the list
	 */
	public List<WebElement> findElements(By arg0) {
		return driver.findElements(arg0);
	}

	/**
	 * Gets the.goto path
	 * 
	 * @param arg0
	 *            the arg0
	 */
	public void get(String arg0) {
		driver.get(arg0);
	}

	/**
	 * Gets the current url.
	 * 
	 * @return the current url
	 */
	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}

	/**
	 * Gets the page source.
	 * 
	 * @return the page source
	 */
	public String getPageSource() {
		return driver.getPageSource();
	}

	/**
	 * Gets the title.
	 * 
	 * @return the title
	 */
	public String getTitle() {
		return driver.getTitle();
	}

	/**
	 * Gets the window handle.
	 * 
	 * @return the window handle
	 */
	public String getWindowHandle() {
		return driver.getWindowHandle();
	}

	/**
	 * Gets the window handles.
	 * 
	 * @return the window handles
	 */
	public Set<String> getWindowHandles() {
		return driver.getWindowHandles();
	}

	/**
	 * Manage.
	 * 
	 * @return the options
	 */
	public Options manage() {
		return driver.manage();
	}

	/**
	 * Navigate.
	 * 
	 * @return the navigation
	 */
	public Navigation navigate() {
		return driver.navigate();
	}

	/**
	 * Quit.
	 */
	public void quit() {
		driver.quit();
	}

	/**
	 * Switch to.
	 * 
	 * @return the target locator
	 */
	public TargetLocator switchTo() {
		return driver.switchTo();
	}

	/**
	 * Find element by x path.
	 * 
	 * @param using
	 *            the using
	 * @return the web element
	 * @throws org.openqa.selenium.NoSuchElementException
	 *             If no matching elements are found
	 */
	public WebElement findElementByXPath(String using)
			throws NoSuchElementException {
		return highlight(driver.findElementByXPath(using));
	}

	/**
	 * Find elements by x path.
	 * 
	 * @param using
	 *            the using
	 * @return the list
	 */
	public List<WebElement> findElementsByXPath(String using)
			throws NoSuchElementException {
		return driver.findElementsByXPath(using);
	}

	/**
	 * Find element by id.
	 * 
	 * @param using
	 *            the using
	 * @return the web element
	 * @throws org.openqa.selenium.NoSuchElementException
	 *             If no matching elements are found
	 */
	public WebElement findElementById(String using)
			throws NoSuchElementException {
		return highlight(driver.findElementById(using));
	}

	/**
	 * Find elements by id.
	 * 
	 * @param using
	 *            the using
	 * @return the list
	 */
	public List<WebElement> findElementsById(String using)
			throws NoSuchElementException {
		return driver.findElementsById(using);
	}

	/**
	 * Return true if driver is a remote web driver for the grid or false if it
	 * is a web driver running locally.
	 * 
	 * @return boolean
	 */
	public boolean isRemoteWebDriver() {
		return driver.getClass().getName()
				.equals(RemoteWebDriver.class.getName());
	}

	/**
	 * Gets the screenshot as. This method works for both locally and on the
	 * grid.
	 * 
	 * @param <X>
	 *            the generic type
	 * @param target
	 *            the target
	 * @return the screenshot as
	 * @throws WebDriverException
	 *             the web driver exception
	 */
	public <X> X getScreenshotAs(OutputType<X> target)
			throws WebDriverException {
		if (isRemoteWebDriver()) {
			// The Augmenter allows the screen capture to work on the grid.
			driver = (RemoteWebDriver) new Augmenter().augment(driver);
			return ((TakesScreenshot) driver).getScreenshotAs(target);
		} else {
			// Capture the image when running locally
			return ((TakesScreenshot) driver).getScreenshotAs(target);
		}
	}

	/**
	 * Gets the capabilities.
	 * 
	 * @return the capabilities
	 */
	public Capabilities getCapabilities() {
		return driver.getCapabilities();
	}

	/**
	 * Gets the keyboard.
	 * 
	 * @return the keyboard
	 */
	public Keyboard getKeyboard() {
		return driver.getKeyboard();
	}

	/**
	 * Gets the mouse.
	 * 
	 * @return the mouse
	 */
	public Mouse getMouse() {
		return driver.getMouse();
	}

	/**
	 * Wait for element. Given a By selector object poll for that element and
	 * the text it contains
	 * 
	 * This method should only be used when needing to wait a variable amount of
	 * time. If you need to wait between 5 and 120 seconds for example this
	 * method is what you want. Use to find the last element to load by polling
	 * then the rest of the elements your looking for should already be visible.
	 * 
	 * @param locator
	 *            the locator
	 * @param text
	 *            the text
	 * @param secondsToWait
	 *            the seconds to wait
	 * @return true, if successful
	 */
	public boolean waitForElementText(By locator, String text,
			long secondsToWait) {
		WebDriverWait wait = new WebDriverWait(driver, secondsToWait);
		boolean result;
		String message;
		long start = System.currentTimeMillis();

		try {
			// Keep poling until element is visible or we have exceeded
			// secondsToWait if it's NOT visible a time out exception is thrown
			result = wait.until(ExpectedConditions.textToBePresentInElement(
					locator, text));
			message = "SetDriver.waitForElementText() found: ";
		} catch (TimeoutException toe) {
			// catch TimeoutException exception, log it, set result to false.
			message = "SetDriver.waitForElementText() did NOT find: ";
			result = false;
		}

		double secondsWaited = (System.currentTimeMillis() - start) / 1000.0;
		LOG.info(message + locator.toString() + " in " + secondsWaited
				+ " seconds out of " + secondsToWait + " seconds.");

		return result;
	}

	/**
	 * Wait for an element to become stale. Useful when a javascript call has
	 * refreshed the page, or something on the page.
	 * 
	 * @param locator
	 * @param secondsToWait
	 * @return true if it became stale, false otherwise
	 * @throws org.openqa.selenium.TimeoutException
	 *             If command does not complete in enough time
	 */
	public boolean waitForElementStaleness(By locator, long secondsToWait)
			throws TimeoutException {
		WebDriverWait wait = new WebDriverWait(driver, secondsToWait, 100L);
		// Set the implicit wait low, otherwise "wait.until" will wait a minimum
		// of the implicit wait.

		driver.manage().timeouts().implicitlyWait(1L, TimeUnit.SECONDS);

		WebElement element = driver.findElement(locator);
		boolean ret = false;

		// until element is stale or we have exceeded secondsToWait
		ret = wait.until(ExpectedConditions.stalenessOf(element));

		// reset the timeout.
		// driver.manage().timeouts()
		// .implicitlyWait(SuiteConfiguration.getInstance().getImplicitTimeoutSec(),
		// TimeUnit.SECONDS);

		return ret;
	}

	/**
	 * Wait for element. Given a By selector object poll for that element
	 * 
	 * This method should only be used when needing to wait a variable amount of
	 * time. If you need to wait between 5 and 120 seconds for example this
	 * method is what you want. Use to find the last element to load by polling
	 * then the rest of the elements your looking for should already be visible.
	 * 
	 * @param locator
	 *            the By locator to search by
	 * @param secondsToWait
	 *            the seconds to wait
	 * @return the web element
	 * @throws org.openqa.selenium.TimeoutException
	 *             If command does not complete in enough time
	 */
	public WebElement waitForElementVisible(By locator, long secondsToWait)
			throws TimeoutException {
		WebDriverWait wait = new WebDriverWait(driver, secondsToWait);
		WebElement foundElement;
		long start = System.currentTimeMillis();

		try {
			// Keep poling until element is visible or we have exceeded
			// secondsToWait if it's NOT visible a time out exception is thrown
			foundElement = wait.until(ExpectedConditions
					.visibilityOfElementLocated(locator));
		} catch (TimeoutException toe) {
			LOG.info("SetDriver.waitForElementVisible() was NOT able to find "
					+ locator.toString() + " within " + secondsToWait
					+ " seconds.");
			throw toe;
		}

		double secondsWaited = (System.currentTimeMillis() - start) / 1000.0;
		LOG.info("SetDriver.waitForElementVisible() found: "
				+ locator.toString() + " in " + secondsWaited
				+ " seconds out of " + secondsToWait + " seconds.");

		// return the element found
		return foundElement;
	}

	/**
	 * Wait for a SeBlob to be invisible.
	 * 
	 * @param blob
	 *            - SeBlob to be invisible
	 * @param secondsToWait
	 *            - long seconds to wait for SeBlob to be invisible.
	 * @return boolean - true if item became invisible, false if item is still
	 *         visible.
	 */
	public boolean waitForInvisible(By locator, long secondsToWait) {
		LOG.info("Waiting for seblob to be invisible.");
		WebDriverWait wait = new WebDriverWait(driver, secondsToWait);
		try {
			return wait.until(ExpectedConditions
					.invisibilityOfElementLocated(locator));
		} catch (TimeoutException toe) {
			// The SeBlob did NOT become invisible within the allowed time, log
			// it and return false.
			LOG.info("SeBlob '" + locator + "' is still visible after "
					+ secondsToWait + ".");
			return false;
		}
	}

	/**
	 * Wait for a SeBlob to be invisible. This method used the implicit wait
	 * time.
	 * 
	 * @param blob
	 *            - SeBlob to be invisible
	 * @return boolean - true if item became invisible, false if item is still
	 *         visible.
	 */
	public boolean waitForInvisible(By locator) {
		return waitForInvisible(locator, defaultWait);
	}

	/**
	 * Wait for any of a group of Elements found by their respective locator to
	 * become visible. Return the first one that does.
	 * 
	 * Useful Context example: wait for the payment frame on the join page, or
	 * an error that the payment system is unavailable.
	 * 
	 * @param locators
	 * @param secondsToWait
	 * @return
	 */
	public WebElement waitForOneOfAGroupToBeVisible(List<By> locators,
			long secondsToWait) {
		long start = System.currentTimeMillis();
		WebElement ret = null;
		while (true) {
			for (int i = 0; i < locators.size(); i++) {
				try {
					// Use a short wait, we want to check for the next guy in
					// our loop:
					ret = waitForElementVisible(locators.get(i), 1L);
					// we found it, return it:
					if (ret != null) {
						return ret;
					}
				} catch (TimeoutException toe) {
					// if we're out of time, throw, otherwise, do nothing:
					if ((System.currentTimeMillis() - start) > (secondsToWait * 1000)) {
						throw toe;
					}
				}
			}
		}
	}
}
