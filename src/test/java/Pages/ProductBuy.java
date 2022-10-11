package Pages;

import net.jodah.failsafe.internal.util.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Properties;

public class ProductBuy {
    WebDriver driver;
    WebDriverWait wait;
    List<WebElement> allElementDisplay;
    String product = "//a[contains(text(),'%s')]";
    String categories = "//div[@class='item-grid']//a[contains(text(),'%s')]";
    By sortByDropDown = By.xpath("//select[@name='products-orderby']");
    String options = "//option[contains(text(),'%s')]";
    By display = By.xpath("//select[@name='products-pagesize']");
    String displaySize = "//option[contains(text(),'%s')]";
    String allProducts = "//h2[@class='product-title']";
    String price = "//div[@class='prices']";
//    By addtoCart = By.xpath("(//button[contains(text(),'Add to cart')])[1]");
    By all = By.xpath("//div[@class='page-body']");

    By addtoCart = By.linkText("HP Spectre XT Pro UltraBook");
    By verifyMessage = By.xpath("//p[contains(text(),'The product has been added to your ')]");


    public ProductBuy(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(50));
    }

    public void WebsiteURL() throws IOException {

        FileReader reader = new FileReader("src/test/java/PropertiesFile/Config.properties");
        Properties data = new Properties();
        data.load(reader);
        driver.get(data.getProperty("url"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
    }

    public void Computer(String lable) {
        driver.findElement(By.xpath(String.format(product, lable))).click();
    }

    public void Categories(String lable) {
        driver.findElement(By.xpath(String.format(categories, lable))).click();
    }

    public void Sort(String lable) {
        driver.findElement(sortByDropDown).click();
        driver.findElement(By.xpath(String.format(options, lable))).click();
        driver.findElement(display).click();
        driver.findElement(By.xpath(String.format(displaySize, "9"))).click();
    }

    public void ProductDetails() {
        allElementDisplay = driver.findElements(By.xpath(allProducts));
        for (WebElement allElements : allElementDisplay) {
            {
                String StrLinkText = allElements.getText();
                System.out.println(StrLinkText);
            }
        }

        allElementDisplay = driver.findElements(By.xpath(price));
        for (WebElement allElements : allElementDisplay) {
            {
                String StrLinkTextt = allElements.getText();
                System.out.println(StrLinkTextt);
            }
        }
        wait.until(ExpectedConditions.visibilityOf((WebElement) all));
    }

    public void VerifyMessage() throws InterruptedException {
//        Thread.sleep(5000);
        wait.until(ExpectedConditions.elementToBeClickable(addtoCart));
        driver.findElement(addtoCart).click();
        String actual = driver.findElement(verifyMessage).getText();
        Assert.isTrue(actual.equals("The product has been added to your "), "Expected result does not match with actual result");
    }


}
