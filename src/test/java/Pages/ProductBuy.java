package Pages;

import net.jodah.failsafe.internal.util.Assert;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Properties;

public class ProductBuy {
    WebDriver driver;
    WebDriverWait wait;
    List<WebElement> allElementDisplay;
    String s = RandomStringUtils.randomAlphanumeric(8);
    String product = "//a[contains(text(),'%s')]";
    String categories = "//div[@class='item-grid']//a[contains(text(),'%s')]";
    String options = "//option[contains(text(),'%s')]";
    String displaySize = "//option[contains(text(),'%s')]";
    String allProducts = "//h2[@class='product-title']";
    String price = "//div[@class='prices']";
    String products = "//h2[@class='product-title']//a[contains(text(),'%s')]";
    String checkout1 = "//button[contains(text(),'%s')]";
    String userDetail = "//input[@id='%s' and @name='%s']";
    String continueButton = "//button[@onclick='%s']";
    By display = By.xpath("//select[@name='products-pagesize']");
    By sortByDropDown = By.xpath("//select[@name='products-orderby']");
    By addtoCart = By.xpath("//div[@class='add-to-cart-panel']//button[@type='button']");
    By verifyMessage = By.xpath("//p[@class='content'][contains(text(),'The product has been added to your ')]");
    By verifyShopingCart = By.xpath("//td[@class='product']/a[contains(text(),'Apple MacBook Pro 13-inch')]");
    By closeMessage = By.xpath("//span[@class='close']");
    By checkBox = By.xpath("//input[@id='termsofservice']");
    By gender = By.xpath("//span[@class='male']");
    By quantity = By.xpath("//input[@class='qty-input']");
    By countryDropDown = By.xpath("//select[@name='BillingNewAddress.CountryId']");
    By country = By.xpath("//option[contains(text(),'Austria')]");
    By verifyOrder = By.xpath("//strong[contains(text(),'Your order has been successfully processed!')]");

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

    public void Computer() {
        driver.findElement(By.xpath(String.format(product, "Computers "))).click();
    }

    public void Categories() {
        driver.findElement(By.xpath(String.format(categories, " Notebooks "))).click();
    }

    public void Sort() {
        driver.findElement(sortByDropDown).click();
        driver.findElement(By.xpath(String.format(options, "Price: Low to High"))).click();
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
    }

    public void VerifyMessage() {
        try {
            driver.findElement(By.xpath(String.format(products, "Apple MacBook Pro 13-inch"))).click();

        } catch (StaleElementReferenceException elementHasDisappeared) {
            driver.findElement(By.xpath(String.format(products, "Apple MacBook Pro 13-inch"))).click();

        }
        driver.findElement(addtoCart).click();
        String actual = driver.findElement(verifyMessage).getText();
        Assert.isTrue(actual.equals("The product has been added to your shopping cart"), "Expected result does not match with actual result");
    }

    public void ShoppingCart(String shoppingCart) {
        driver.findElement(closeMessage).click();
        driver.findElement(By.linkText(shoppingCart)).click();
        String actual = driver.findElement(verifyShopingCart).getText();
        Assert.isTrue(actual.equals("Apple MacBook Pro 13-inch"), "Expected result does not match with actual result");
    }

    public void Checkout() {
        driver.findElement(checkBox).click();
        driver.findElement(By.xpath(String.format(checkout1, "Checkout "))).click();
    }

    public void RegisterUser() throws IOException {
        driver.findElement(By.xpath(String.format(checkout1, "Register"))).click();
        driver.findElement(gender).click();
        String path = System.getProperty("user.dir") + "//src//test//java//UserData//UserDetails.xlsx";
        FileInputStream prop1 = null;
        try {
            prop1 = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        XSSFWorkbook wb = new XSSFWorkbook(prop1);
        XSSFSheet sheet = wb.getSheet("Sheet1");
        String userFirstName = sheet.getRow(1).getCell(0).getStringCellValue();
        String userSecondName = sheet.getRow(1).getCell(1).getStringCellValue();
        String userEmail = sheet.getRow(1).getCell(2).getStringCellValue();
        String company = sheet.getRow(1).getCell(3).getStringCellValue();
        String password = sheet.getRow(1).getCell(4).getStringCellValue();
        String confirmPassword = sheet.getRow(1).getCell(5).getStringCellValue();

        driver.findElement(By.xpath(String.format(checkout1, "Register"))).click();
        driver.findElement(gender).click();
        driver.findElement(By.xpath(String.format(userDetail, "FirstName", "FirstName"))).sendKeys(userFirstName);
        driver.findElement(By.xpath(String.format(userDetail, "LastName", "LastName"))).sendKeys(userSecondName);
        driver.findElement(By.xpath(String.format(userDetail, "Email", "Email"))).sendKeys(s + userEmail);
        driver.findElement(By.xpath(String.format(userDetail, "Company", "Company"))).sendKeys(company);
        driver.findElement(By.xpath(String.format(userDetail, "Password", "Password"))).sendKeys(password);
        driver.findElement(By.xpath(String.format(userDetail, "ConfirmPassword", "ConfirmPassword"))).sendKeys(confirmPassword);
        driver.findElement(By.xpath(String.format(checkout1, "Register"))).click();
    }

    public void ProductQuantity(String shoppingCart) throws IOException {
        driver.findElement(By.linkText(shoppingCart)).click();
        driver.findElement(quantity).clear();
        String path = System.getProperty("user.dir") + "//src//test//java//UserData//UserDetails.xlsx";
        FileInputStream prop1 = null;
        try {
            prop1 = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        XSSFWorkbook wb = new XSSFWorkbook(prop1);
        XSSFSheet sheet = wb.getSheet("Sheet1");
        String productQuantity = sheet.getRow(1).getCell(6).getStringCellValue();
        driver.findElement(quantity).sendKeys(productQuantity);
        driver.findElement(checkBox).click();
        driver.findElement(By.xpath(String.format(checkout1, "Checkout "))).click();
    }

    public void ShippingDetails() throws IOException {
        String path = System.getProperty("user.dir") + "//src//test//java//UserData//UserDetails.xlsx";
        FileInputStream prop1 = null;
        try {
            prop1 = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        XSSFWorkbook wb = new XSSFWorkbook(prop1);
        XSSFSheet sheet = wb.getSheet("Sheet1");
        String userCity = sheet.getRow(1).getCell(7).getStringCellValue();
        String userAddress = sheet.getRow(1).getCell(8).getStringCellValue();
        String userZipCode = sheet.getRow(1).getCell(9).getStringCellValue();
        String userPhoneNumber = sheet.getRow(1).getCell(10).getStringCellValue();

        driver.findElement(countryDropDown).click();
        driver.findElement(country).click();
        driver.findElement(By.xpath(String.format(userDetail, "BillingNewAddress_City", "BillingNewAddress.City"))).sendKeys(userCity);
        driver.findElement(By.xpath(String.format(userDetail, "BillingNewAddress_Address1", "BillingNewAddress.Address1"))).sendKeys(userAddress);
        driver.findElement(By.xpath(String.format(userDetail, "BillingNewAddress_ZipPostalCode", "BillingNewAddress.ZipPostalCode"))).sendKeys(userZipCode);
        driver.findElement(By.xpath(String.format(userDetail, "BillingNewAddress_PhoneNumber", "BillingNewAddress.PhoneNumber"))).sendKeys(userPhoneNumber);
        driver.findElement(By.xpath(String.format(continueButton, "Billing.save()"))).click();
        driver.findElement(By.xpath(String.format(continueButton, "ShippingMethod.save()"))).click();
        driver.findElement(By.xpath(String.format(continueButton, "PaymentMethod.save()"))).click();
        driver.findElement(By.xpath(String.format(continueButton, "PaymentInfo.save()"))).click();
        driver.findElement(By.xpath(String.format(continueButton, "ConfirmOrder.save()"))).click();
    }

    public void VerifyOrder() {
        String actual = driver.findElement(verifyOrder).getText();
        Assert.isTrue(actual.equals("Your order has been successfully processed!"), "Expected result does not match with actual result");
    }

}
