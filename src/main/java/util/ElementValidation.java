package util;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.browserlaunchers.Sleeper;
import org.testng.log4testng.Logger;

public class ElementValidation {
	private static final Logger LOG = Logger.getLogger(ElementValidation.class);
	protected SetDriver driver;
	private long defaultWait = 5;

	public ElementValidation(SetDriver driver) {
		super();
		this.driver = driver;

		LOG.debug("element validation class instantiated");
	}

	/**
	 * Verifies if the element represented by the SeBlob is displayed on current
	 * page. Allows for a custom amount of time to wait for the element to
	 * appear
	 * 
	 * @param seBlob
	 *            - element to search for
	 * @param long - seconds to wait before giving up the search
	 * @return boolean - true if element is found and displayed
	 */
	public boolean isDisplayed(By locator, long secondsToWait) {

		try {
			driver.waitForElementVisible(locator, secondsToWait);
		} catch (TimeoutException e) {
			LOG.info("isDisplayed didn't find element before time ran out");
			return false;
		}

		LOG.info("isDisplayed = true");
		return true;
	}

	/**
	 * Verifies if the element represented by the SeBlob is displayed on current
	 * page.
	 * 
	 * @param seBlob
	 *            - element to search for
	 * @return boolean - true if element is found and displayed
	 */
	public boolean isDisplayed(By locator) {
		return this.isDisplayed(locator, defaultWait);

	}

	/**
	 * Verifies if the element represented by the SeBlob can be selected on
	 * current page. The element must be visible to be selectable. Allows for a
	 * custom amount of time to wait for the element to appear on the page
	 * 
	 * @param seBlob
	 *            - element to search for
	 * @param long - seconds to wait before giving up search
	 * @return boolean - true if element is selectable
	 * @throws Exception
	 */
	public boolean isSelected(By locator, long secondsToWait)
			throws TimeoutException {
		WebElement element;
		boolean selected = false;

		try {
			element = driver.waitForElementVisible(locator, secondsToWait);
			selected = element.isSelected();
		} catch (TimeoutException e) {
			LOG.info("isSelected did not find the element on the page within the allotted time");
			throw e;

		}
		LOG.info("isSelected = " + selected);
		return selected;
	}

	/**
	 * Verifies if the element represented by the SeBlob can be selected on
	 * current page. The element must be visible to be selectable.
	 * 
	 * @param seBlob
	 *            - element to search for
	 * @return boolean - true if element is selectable
	 * @throws Exception
	 */
	public boolean isSelected(By locator) throws TimeoutException {
		return this.isSelected(locator, defaultWait);
	}

	/**
	 * Verifies if the element represented by the SeBlob is enabled on current
	 * page. The element must be visible to be enabled. Allows for a custom
	 * amount of time to wait for the element to appear on the page
	 * 
	 * @param seBlob
	 *            - element to search for
	 * @param long - seconds to wait before giving up search
	 * @return boolean - true if element is enabled
	 * @throws TimeoutException
	 */
	public boolean isEnabled(By locator, long secondsToWait)
			throws TimeoutException {
		WebElement element;
		boolean enabled = false;

		try {
			element = driver.waitForElementVisible(locator, secondsToWait);
			enabled = element.isEnabled();
		} catch (TimeoutException e) {
			LOG.info("isEnabled did not find the element on the page within the allotted time");
			throw e;

		}

		LOG.info("isEnabled = " + enabled);
		return enabled;
	}

	/**
	 * Verifies if the element represented by the SeBlob is enabled on current
	 * page. The element must be visible to be enabled.
	 * 
	 * @param seBlob
	 *            - element to search for
	 * @return boolean - true if element is enabled
	 * @throws TimeoutException
	 */
	public boolean isEnabled(By locator) throws TimeoutException {
		return this.isEnabled(locator, defaultWait);
	}

	/**
	 * Verifies that the element represented by the seBlob has text that matches
	 * the expected. Allows for custom text for situations where the standard
	 * dictionary look up is not appropriate. Allows for custom amount of time
	 * to wait for the element to appear on the page
	 * 
	 * @param seBlob
	 *            - page element to find
	 * @param String
	 *            - expected text that should be on the page
	 * @param long - seconds to wait for element before giving up search
	 * @return boolean - true if the text is displayed and matches the expected
	 * @throws TimeoutException
	 */
	public boolean verifyTextEqual(By locator, String expectedText,
			long secondsToWait) throws TimeoutException {
		WebElement element;
		String displayedText = null;

		try {
			element = driver.waitForElementVisible(locator, secondsToWait);
			displayedText = element.getText();
		} catch (TimeoutException e) {
			LOG.info("verifyTextEqual did not find the element on the page within the allotted time");
			throw e;

		}

		LOG.info("verifyTextEqual: Expected " + expectedText + ", found "
				+ displayedText);
		return expectedText.equals(displayedText);
	}

	/**
	 * Verifies that the element represented by the seBlob has text that matches
	 * the expected. Allows for custom text for situations where the standard
	 * dictionary look up is not appropriate.
	 * 
	 * @param seBlob
	 *            - page element to find
	 * @param String
	 *            - expected text that should be on the page
	 * @return boolean - true if the text is displayed and matches the expected
	 * @throws TimeoutException
	 */
	public boolean verifyTextEqual(By locator, String expectedText)
			throws TimeoutException {
		return this.verifyTextEqual(locator, expectedText, defaultWait);
	}

	/**
	 * Verifies that the element represented by the seBlob has a href that
	 * matches the provided href and is enabled. Intended for verification of
	 * links when the link doesn't have an applicable text/image token, or the
	 * href is modified because of language. Allows for custom amount of time to
	 * wait for the element to appear on the page
	 * 
	 * @param seBlob
	 *            - element on page to find and verify
	 * @param expectedHref
	 *            - custom href to verify against rather than SeBlob href
	 * @param secondsToWait
	 *            - seconds to wait before giving up search
	 * @return boolean - true if the expected href is contained in the found
	 *         href
	 * @throws TimeoutException
	 */
	public boolean verifyHref(By locator, String expectedHref,
			long secondsToWait) throws TimeoutException {
		WebElement element;
		boolean elementVerified = false;

		try {
			element = driver.waitForElementVisible(locator, secondsToWait);
		} catch (TimeoutException e) {
			LOG.info("verifyHref did not find the element on the page within the allotted time");
			throw e;

		}

		if (element.getAttribute("href").contains(expectedHref)) {
			if (element.isEnabled()) {
				LOG.info("verifyHref: found the link enabled, the href as expected");
				elementVerified = true;
			} else {
				LOG.info("verifyHref: Expected link to be enabled but link was not enabled");
			}
		} else {
			LOG.info("verifyHref: Expected href '" + expectedHref
					+ "', but found '" + element.getAttribute("href") + "'");
		}

		return elementVerified;
	}

	/**
	 * Verifies that the element represented by the seBlob has a href that
	 * matches the provided href and is enabled. Intended for verification of
	 * links when the link doesn't have an applicable text/image token, or the
	 * href is modified because of language.
	 * 
	 * @param seBlob
	 *            - element on page to find and verify
	 * @param expectedHref
	 *            - custom href to verify against rather than SeBlob href
	 * @return boolean - true if the expected href is contained in the found
	 *         href
	 * @throws TimeoutException
	 */
	public boolean verifyHref(By locator, String expectedHref)
			throws TimeoutException {
		return verifyHref(locator, expectedHref, defaultWait);
	}

	/**
	 * Verifies the browser url contains the expected url string
	 * 
	 * @param String
	 *            - the expected url
	 * @param long - seconds to wait before giving up
	 * @return boolean - true if url contains expected url
	 */
	public boolean verifyBrowserUrl(String expectedUrl, long delaySeconds) {

		long waited = 0;
		while (waited < delaySeconds) {

			// Check if current url matches (contains) expected
			if (driver.getCurrentUrl().contains(expectedUrl)) {
				return true;

			} else {
				Sleeper.sleepTightInSeconds(1);
				waited += 1;
			}

		}

		// Failed to find expected Url
		LOG.info("verifyUrl did not find the expected url within the allotted time");
		LOG.info("Actual URL:              " + driver.getCurrentUrl());
		LOG.info("Expected URL (contains): " + expectedUrl);
		return false;
	}

	/**
	 * Verifies the browser url contains the expected url string
	 * 
	 * @param String
	 *            - the expected url
	 * @return boolean - true if url contains expected url
	 */
	public boolean verifyBrowserUrl(String expectedUrl) {
		return verifyBrowserUrl(expectedUrl, defaultWait);
	}
}
