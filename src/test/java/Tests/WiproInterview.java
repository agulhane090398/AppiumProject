package Tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WiproInterview {

    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup(); // for auto manage driver version with dependency from io.github.bonigarcia
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://www.flipkart.com/");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@alt=\"Mobiles & Tablets\"]")));
        driver.findElement(By.xpath("//*[@alt=\"Mobiles & Tablets\"]")).click();
        driver.findElement(By.xpath("//*[@placeholder=\"Search for products, brands and more\"]")).click();

        driver.findElement(By.xpath("//*[@placeholder=\"Search for products, brands and more\"]")).sendKeys("Samsung 5g");
        driver.findElement(By.xpath("//*[@placeholder=\"Search for products, brands and more\"]")).sendKeys(Keys.ENTER);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@type=\"checkbox\"]/following-sibling::div[text()=\"4 GB\"]")));

        driver.findElement(By.xpath("//*[@type=\"checkbox\"]/following-sibling::div[text()=\"4 GB\"]")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class=\"JPBFuF vRd1kF\"]//*[text()=\"4 GB\"]")));

        driver.findElement(By.xpath("//*[@class=\"RG5Slk\"]")).getText();

    }
}
