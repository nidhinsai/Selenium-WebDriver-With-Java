package org.automation.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

/**
 * WaitUtils — convenience static helpers for common explicit-wait patterns.
 *
 * <p>Wraps {@link WebDriverWait} so callers don't need to repeat the
 * boilerplate of constructing a wait and calling {@code .until()} every time.</p>
 *
 * <p>All methods default to a 10-second timeout. Pass a custom
 * {@code timeoutSeconds} argument when a page requires more time.</p>
 */
public final class WaitUtils {

    private static final int DEFAULT_TIMEOUT = 10;

    // Utility class — no instances
    private WaitUtils() {}

    /**
     * Waits until {@code locator} is visible, then returns the element.
     */
    public static WebElement waitForVisible(WebDriver driver, By locator) {
        return waitForVisible(driver, locator, DEFAULT_TIMEOUT);
    }

    /**
     * Waits up to {@code timeoutSeconds} for {@code locator} to be visible.
     */
    public static WebElement waitForVisible(WebDriver driver, By locator, int timeoutSeconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Waits until {@code locator} is clickable (visible + enabled), then clicks it.
     */
    public static void waitAndClick(WebDriver driver, By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT))
                .until(ExpectedConditions.elementToBeClickable(locator))
                .click();
    }

    /**
     * Waits until all elements matching {@code locator} are visible.
     *
     * @return The list of visible elements.
     */
    public static List<WebElement> waitForAllVisible(WebDriver driver, By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    /**
     * Waits until {@code locator} is no longer visible (element gone or hidden).
     */
    public static void waitForInvisible(WebDriver driver, By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT))
                .until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }
}
