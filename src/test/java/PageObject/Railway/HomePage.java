package PageObject.Railway;

import Common.Constant.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class HomePage extends  GeneralPage{
    //Locators
    //Elements
    //Methods
    //Locators for Book ticket tab
    private final By tabBookTicket = By.xpath("//div[@id='menu']//a[@href='/Page/BookTicketPage.cshtml']");
    public void clickBookTicket(){
        Constant.WEBDRIVER.findElement(tabBookTicket).click();
    }
    private final By tabRegister = By.xpath("//div[@id='menu']//a[@href='/Page/BookTicketPage.cshtml']");
    public void clickRegister() {
        Constant.WEBDRIVER.findElement(tabRegister).click();
    }


    public HomePage open(){
        Constant.WEBDRIVER.navigate().to(Constant.RAILWAY_URL);
        return this;
        //This is a method that starts ours test cases
    }

}
