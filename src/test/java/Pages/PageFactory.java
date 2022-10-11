package Pages;

import org.openqa.selenium.WebDriver;

public class PageFactory {
    WebDriver driver;
     private ProductBuy productBuy;
    public PageFactory(WebDriver driver) {
        this.driver = driver;
    }
    public ProductBuy getProductBuy()
    {
        if (productBuy == null)
        {
            productBuy = new ProductBuy(driver);
        }
        return productBuy;
    }

}
