package PageObject.Railway;

import Common.Constant.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginPage extends GeneralPage{
    //Locators
    private final By _txtUsername = By.xpath("//input[@id='username']");
    private final By _txtPassword = By.xpath("//input[@id='password']");
    private final By _btnLogin = By.xpath("//input[@value='Login']");
    private final By _lblLoginErrorMsg = By.xpath("//p[@class='message error LoginForm']");
    private final By _MyTicket = By.xpath("//div[@id='menu']//a[@href='/Page/ManageTicket.cshtml']");
    private final By _ChangePassword = By.xpath("//div[@id='menu']//a[@href='/Account/ChangePassword.cshtml']");
    public WebElement getMyTicket(){
        return Constant.WEBDRIVER.findElement(_MyTicket);
    }
    public WebElement getChangePassword(){
        return Constant.WEBDRIVER.findElement(_ChangePassword);
    }
    //Elements
    public WebElement getTxtUsername(){
        return Constant.WEBDRIVER.findElement(_txtUsername);
    }
    public WebElement getTxtPassword(){
        return Constant.WEBDRIVER.findElement(_txtPassword);
    }
    public WebElement getBtnLogin(){
        return Constant.WEBDRIVER.findElement(_btnLogin);
    }
    public WebElement getLblLoginErrorMsg(){
        return Constant.WEBDRIVER.findElement(_lblLoginErrorMsg);
    }
    public void clickMyTicket() {
        Constant.WEBDRIVER.findElement(_MyTicket).click();
    }
    public void clickChangePassword() {
        Constant.WEBDRIVER.findElement(_ChangePassword).click();
    }

    private final By _clickRegisterTab = By.xpath("//input[@value='Login']");

    public void clickRegisterTab() {
        Constant.WEBDRIVER.findElement(_clickRegisterTab).click();
    }

    //Methods: login method returns HomePage
    public HomePage login(String username, String password){
        //Submit login credentials: there are LoginPage's WebElements
        this.getTxtUsername().sendKeys(username);
        this.getTxtPassword().sendKeys(password);
        this.getBtnLogin().click();
        //Land on Home page
        return new HomePage();
    }
}