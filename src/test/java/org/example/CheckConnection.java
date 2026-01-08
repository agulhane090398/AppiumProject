package org.example;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.time.Duration;


public class CheckConnection {
    public static void main(String[] args) throws MalformedURLException, InterruptedException {

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("appium:deviceName", "Medium Phone");
//        caps.setCapability("platformVersion", "16.0");
//        caps.setCapability("deviceOrientation", "portrait");
        caps.setCapability("appium:appPackage", "com.google.android.youtube");
        caps.setCapability("appium:appActivity", "com.google.android.youtube.HomeActivity");
//        caps.setCapability("noReset", "true");
        caps.setCapability("appium:automationName", "uiautomator2");

//        URL url = URI.create("http://127.0.0.1:4723/").toURL();
//        URL url = URI.create("localhost:4723/").toURL();
        AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),caps);
        Thread.sleep(10000);
        try {
            if (driver.findElement(By.id("com.android.permissioncontroller:id/permission_allow_button")).isDisplayed()) {
                driver.findElement(By.id("com.android.permissioncontroller:id/permission_allow_button")).click();
            }
        }catch(Exception e) {
            System.out.println( e.getMessage() );
        }
        driver.findElement(By.xpath("//*[@content-desc='Search YouTube']")).click();
        driver.findElement(By.id("com.google.android.youtube:id/search_edit_text")).sendKeys("Pokemon");
        driver.findElement(By.xpath("(//*[@resource-id= 'com.google.android.youtube:id/linearLayout'])[1]")).click();
        Thread.sleep(10000);
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().wait(10000);
        driver.findElement(By.xpath("//*[@content-desc='Since you searched for pokemon']")).click();
//        wait.until(driver.findElement(By.xpath("//*[@content-desc='Since you searched for pokemon']")).isDisplayed());
        Thread.sleep(10000);
        driver.quit();
    }
}
