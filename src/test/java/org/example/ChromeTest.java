package org.example;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ChromeTest extends BaseTest {
    @Test
    public void testScenario() {
        driver.get("https://www.lambdatest.com");

        // Perform an explicit wait until all elements in the DOM are available
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));

        // Scroll to the 'SEE ALL INTEGRATIONS' WebElement
        WebElement integrationsLink = driver.findElement(By.xpath("//body/div[@id='__next']/div[1]/section[8]/div[1]/div[1]/div[1]/div[1]/a[1]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", integrationsLink);

        // Use JavaScript to open the link in a new tab
        ((JavascriptExecutor) driver).executeScript("window.open(arguments[0].href, '_blank');", integrationsLink);

        // Wait for the new tab to open
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        // Save the window handles in a List and print them
        List<String> windowHandles = new ArrayList<>(driver.getWindowHandles());
        for (String handle : windowHandles) {
            System.out.println("Window Handle: " + handle);
        }

        // Switch to the new tab
        driver.switchTo().window(windowHandles.get(1));

        // Verify the URL
        String expectedUrl = "https://www.lambdatest.com/integrations";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl, "The URL is not as expected!");

        // Close the current browser window
        driver.close();

        // Switch back to the original tab
        driver.switchTo().window(windowHandles.get(0));
    }
}

