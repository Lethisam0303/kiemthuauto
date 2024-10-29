package PageObject.Railway;

import Common.Constant.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class GeneralPage {
    //Locators
    public final By tabLogin = By.xpath("//div[@id='menu']//a[@href='/Account/Login.cshtml']");
    public final By tabLogout = By.xpath("//div[@id='menu']//a[@href='/Account/Logout']");
    public final By lblWelcomeMessage = By.xpath("//div[@class='account']/strong");
    public final By _tabForgotPW = By.xpath("//*[@id='content']/ul/li[3]/a");
    private final By tabRegister = By.xpath("//div[@id='menu']//a[@href='/Account/Register.cshtml']");
    public final By tabChangePassword = By.xpath("//*[@id=\"menu\"]/ul/li[8]/a");
    public final By tabTrainTimetable = By.xpath("//*[@id=\"menu\"]/ul/li[4]/a");

    //Elements: getter method for retrieving WebElements
    protected WebElement getTabLogin(){

        return Constant.WEBDRIVER.findElement(tabLogin);
    }
    protected WebElement getTabLogout(){
        return Constant.WEBDRIVER.findElement(tabLogout);
    }
    protected WebElement getLblWelcomeMessage(){
        return Constant.WEBDRIVER.findElement(lblWelcomeMessage);
    }
    public  final By tabForgotPassword = By.xpath("//div[@id='menu']//a[@href='/Account/ForgotPassword.cshtml']");
    protected WebElement getTabRegister(){
        return Constant.WEBDRIVER.findElement(tabRegister);
    }
    protected WebElement getTabChangePassword(){return Constant.WEBDRIVER.findElement(tabChangePassword);}

    //Methods: page's methods
    public String getWelcomeMessage(){
        return this.getLblWelcomeMessage().getText();
    }
    public LoginPage gotoLoginPage(){
        this.getTabLogin().click();
        return new LoginPage();
    }
    public  RegisterPage gotoRegister(){
        this.getTabRegister().click();
        return new RegisterPage();
    }
    public WebElement getforgotpw(){
        return Constant.WEBDRIVER.findElement(_tabForgotPW);
    }
    public ForgotPasswordPage gotoForgotPasswordPage(){
        JavascriptExecutor js = (JavascriptExecutor) Constant.WEBDRIVER;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        this.getforgotpw().click();
        return new ForgotPasswordPage();
    }


    public ChangePasswordPage gotoChangePasswordPage(){
        this.getTabChangePassword().click();
        return new ChangePasswordPage();
    }
    protected WebElement getTabTrainTimetable(){
        return Constant.WEBDRIVER.findElement(tabTrainTimetable);
    }
    public TimeTablePage gotoTrainTimetablePage(){
        this.getTabTrainTimetable().click();
        return new TimeTablePage();
    }


}