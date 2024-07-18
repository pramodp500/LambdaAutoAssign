package org.example;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    protected WebDriver driver;

    @BeforeClass
    @Parameters({"platform", "browser", "version"})
    public void setup(String platform, String browser, String version) throws MalformedURLException {
        MutableCapabilities capabilities = new MutableCapabilities();
        capabilities.setCapability("browserName", browser);
        capabilities.setCapability("browserVersion", version);

        MutableCapabilities ltOptions = new MutableCapabilities();
        ltOptions.setCapability("platformName", platform);
        ltOptions.setCapability("project", "Selenium 4 TestNG Parallel");
        ltOptions.setCapability("build", "Selenium 4 TestNG Parallel Build");
        ltOptions.setCapability("name", "Test Scenario");
        ltOptions.setCapability("network", true);
        ltOptions.setCapability("visual", true);
        ltOptions.setCapability("video", true);
        ltOptions.setCapability("console", true);

        capabilities.setCapability("LT:Options", ltOptions);

        driver = new RemoteWebDriver(new URL("https://pramod.pati3155:BQFwguJXb1wpgB6l3o2sLpJnloYG2aIyzeRV8TAAJj5byMtsJw@hub.lambdatest.com/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

