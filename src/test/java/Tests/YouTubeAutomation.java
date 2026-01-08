package Tests;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.PointOption.point;
import static java.time.Duration.ofSeconds;


public class YouTubeAutomation {

    public static void waitUntilElementIsClickable(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try{
            wait.until(ExpectedConditions.elementToBeClickable(element));
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void threadSleep(Duration duration) throws InterruptedException {
        Thread.sleep(duration.toMillis());

    }
    public static void scrollUntilElementIsDisplayed(WebDriver driver, WebElement element) {
        Dimension size = driver.manage().window().getSize();
        int startY = (int) (size.height * 0.80); // Start near the bottom
        int endY = (int) (size.height * 0.20);   // End near the top (moving finger up to scroll down)
        int startX = size.width / 2;             // Middle of the screen
        while (!element.isDisplayed()) {
            new TouchAction((PerformsTouchActions) driver)
                    .press(point(startX, startY))
                    .waitAction(waitOptions(ofSeconds(1))) // Optional: add a wait duration
                    .moveTo(point(startX, endY))
                    .release()
                    .perform();
        }
    }
    public static void main(String[] args) throws MalformedURLException, InterruptedException {

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("appium:deviceName", "Medium Phone");
        caps.setCapability("appium:appPackage", "com.google.android.youtube");
        caps.setCapability("appium:appActivity", "com.google.android.youtube.HomeActivity");
        caps.setCapability("appium:automationName", "uiautomator2");


        AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),caps);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        try {
            if (driver.findElement(By.id("com.android.permissioncontroller:id/permission_allow_button")).isDisplayed()) {
                driver.findElement(By.id("com.android.permissioncontroller:id/permission_allow_button")).click();
            }
        }catch(Exception e) {
            System.out.println( e.getMessage() );
        }
        WebElement searchBox = driver.findElement(By.xpath("//*[@content-desc='Search YouTube']"));
        waitUntilElementIsClickable(driver,searchBox);
        searchBox.click();
        driver.findElement(By.id("com.google.android.youtube:id/search_edit_text")).sendKeys("Pokemon");
        driver.findElement(By.xpath("//*[@resource-id=\"com.google.android.youtube:id/search_type_icon\"]")).click();
        WebElement selectVideo = driver.findElement(By.xpath("//*[contains(@content-desc, \"Pokémon: Diamond and Pearl\")]"));
        waitUntilElementIsClickable(driver,selectVideo);
        scrollUntilElementIsDisplayed(driver,selectVideo);
        selectVideo.click();
        threadSleep(Duration.ofSeconds(10));
        driver.quit();
    }
}
