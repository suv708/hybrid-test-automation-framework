package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PageElement {

    private WebDriver driver;
    private By locator;
    private String name;

    public PageElement(WebDriver driver, By locator, String name) {
        this.driver = driver;
        this.locator = locator;
        this.name = name;
    }

    // ✅ Returns WebElement only when needed
    public WebElement getElement() {
        return driver.findElement(locator);
    }

    // ✅ Used for Extent report logging
    public String getName() {
        return name;
    }

    // ✅ Used in waits/click/sendKeys
    public By getLocator() {
        return locator;
    }

    // ✅ Optional: for debugging/logging
    @Override
    public String toString() {
        return "PageElement{name='" + name + "', locator=" + locator + "}";
    }
}