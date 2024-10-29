package Testcases.Railway;

import Common.Common.Utilities;
import Common.Constant.Constant;
import PageObject.Railway.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest {
    @BeforeMethod
    public void beforeMethod() {
        System.out.println("Pre-condition");
        Constant.WEBDRIVER = new ChromeDriver();
        Constant.WEBDRIVER.manage().window().maximize();
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("Post-condition");
        Constant.WEBDRIVER.quit();
    }

    @Test
    public void TC01() {
        System.out.println("TC01 - User can log into Railway with valid username and password");
        HomePage homePage = new HomePage();
        homePage.open();
        LoginPage loginPage = homePage.gotoLoginPage();
        String actualMsg = loginPage.login(Constant.USERNAME, Constant.PASSWORD).getWelcomeMessage();
        String expectedMsg = "Welcome " + Constant.USERNAME;
        Assert.assertEquals(actualMsg, expectedMsg, "Welcome message is not displayed as expected");
    }

    @Test
    public void TC02() {
        System.out.println("TC02-User can't login with blank 'Username' textbox");
        HomePage homePage = new HomePage();
        homePage.open();
        LoginPage loginPage = homePage.gotoLoginPage();
        loginPage.login("", Constant.PASSWORD);
        String actualErrorMsg = loginPage.getLblLoginErrorMsg().getText();
        String expectedErrorMsg = "There was a problem with your login and/or errors exist in your form.";
        Assert.assertEquals(actualErrorMsg, expectedErrorMsg, "Error message is not displayed as expected");
    }

    @Test
    public void TC03() {
        System.out.println("User cannot log into Railway with invalid password");
        HomePage homePage = new HomePage();
        homePage.open();
        LoginPage loginPage = homePage.gotoLoginPage();
        loginPage.login(Constant.USERNAME, " ");
        String actualErrorMsg = loginPage.getLblLoginErrorMsg().getText();
        String expectedErrorMsg = "There was a problem with your login and/or errors exist in your form.";
        Assert.assertEquals(actualErrorMsg, expectedErrorMsg, "Error message 'There was a problem with your login and/or errors exist in your form.' is displayed");

    }

    @Test
    public void TC04() {
        System.out.println("TC04 - Login page displays when un-logged User clicks on 'Book ticket' tab");
        HomePage homePage = new HomePage();
        homePage.open();
        homePage.clickBookTicket();
        LoginPage loginPage = new LoginPage();
        Assert.assertTrue(loginPage.getBtnLogin().isDisplayed(), "Login page did not display when un-logged User clicked on 'Book ticket' tab");
    }

    @Test
    public void TC05() {
        System.out.println("TC05 - System shows message when user enters wrong password several times");
        HomePage homePage = new HomePage();
        homePage.open();
        LoginPage loginPage = homePage.gotoLoginPage();
        for (int i = 0; i < 4; i++) {
            loginPage.login(Constant.USERNAME, "invalidPassword");
        }
        loginPage.login(Constant.USERNAME, "invalidPassword");
        String actualErrorMsg = loginPage.getLblLoginErrorMsg().getText();
        String expectedErrorMsg = "You have used 4 out of 5 login attempts. After all 5 have been used, you will be unable to login for 15 minutes.";
        Assert.assertEquals(actualErrorMsg, expectedErrorMsg, "Error message is not displayed as expected");
    }

    @Test
    public void TC06() {
        System.out.println("TC06-Additional pages display once user logged in");
        HomePage homePage = new HomePage();
        homePage.open();
        LoginPage loginPage = homePage.gotoLoginPage();
        loginPage.login(Constant.USERNAME, Constant.PASSWORD);
        loginPage.clickMyTicket();
        loginPage.clickChangePassword();
        Assert.assertTrue(loginPage.getMyTicket().isDisplayed(), "My ticket tab is not displayed");
        Assert.assertTrue(loginPage.getChangePassword().isDisplayed(), "Change password tab is not displayed");
    }

    @Test
    public void TC07() throws InterruptedException {
        System.out.println("User can create new account");
        HomePage homePage = new HomePage();
        homePage.open();
        RegisterPage registerPage = homePage.gotoRegister();
        String actualMsg = registerPage.register(Constant.EMAIL, Constant.PASSWORD, Constant.CONFIRMPASSWORD, Constant.ID).getWelcomeMessage();
        String expectedMsg = "Thank you for registering your account";
        Assert.assertEquals(actualMsg, expectedMsg, "");
        Thread.sleep(10000);

    }

    //        @Test
//    public void TC08(){
//        System.out.println("User can't login with an account hasn't been activated");
//        HomePage homePage = new HomePage();
//        homePage.open();
//        RegisterPage registerPage = homePage.gotoRegister();
//        LoginPage loginPage = homePage.gotoLoginPage();
//        String actualEroMsg = loginPage.getErrorMessage();
//        String expectedErrorMsg = "Invalid username or password. Please try again.";
//        Assert.assertEquals(actualEroMsg, expectedErrorMsg, "Error message mismatch.");
//    }
//    @Test
//    public void TC08() {
//        System.out.println("TC08 - User can't login with an account hasn't been activated");
//        HomePage homePage = new HomePage();
//        homePage.open();
//
//        LoginPage loginPage = homePage.gotoLoginPage();
//
//        loginPage.getTxtUsername().sendKeys("invaliduser@gmail.com");
//        loginPage.getTxtPassword().sendKeys("invalid password");
//        loginPage.getBtnLogin().click();
//
//        String actualErrorMsg = loginPage.getLblLoginErrorMsg().getText();
//        String expectedErrorMsg = "Invalid username or password. Please try again.";
//
//        Assert.assertEquals(actualErrorMsg, expectedErrorMsg, "Error message is not displayed as expected");
//    }
//
    @Test
    public void TC09() throws InterruptedException {
        System.out.println("TC09 - User can change password");
        HomePage homePage = new HomePage();
        homePage.open();
        LoginPage loginPage = homePage.gotoLoginPage();
        loginPage.getTxtUsername().sendKeys(Constant.USERNAME);
        loginPage.getTxtPassword().sendKeys(Constant.PASSWORD);
        loginPage.getBtnLogin().click();
        ChangePasswordPage changePasswordPage = homePage.gotoChangePasswordPage();
        changePasswordPage.getTxtCurrentPassword().sendKeys(Constant.PASSWORD);
        changePasswordPage.getTxtNewPassword().sendKeys("123456789");
        changePasswordPage.getTxtConfirmPassword().sendKeys("123456789");
        changePasswordPage.getBtnChangePassword().click();
        String actualSuccessMsg = changePasswordPage.getLblChangePasswordErrorMsg().getText();
        String expectedSuccessMsg = "Your password has been updated";
        Assert.assertEquals(actualSuccessMsg, expectedSuccessMsg, "Success message is not displayed as expected");
        Thread.sleep(10000);
    }
}
//
//    @Test
//    public void TC10() {
//        System.out.println("User can't create account with 'Confirm password' is not the same with 'Password'");
//        HomePage homePage = new HomePage();
//        homePage.open();
//        RegisterPage registerPage = homePage.gotoRegister();
//        registerPage.getTxtEmail().sendKeys("samle@gmail.com");
//        registerPage.getTxtPassword().sendKeys("abcd123456");
//        registerPage.getTxtConfirmPassword().sendKeys("samle");
//        registerPage.getTxtPid().sendKeys("123456900876");
//        registerPage.getBtnRegister().submit();
//        String actualMsg = registerPage.getLblRegisterErrorMsg().getText();
//        String expectedMsg = "There're errors in the form. Please correct the errors and try again.";
//        Assert.assertEquals(actualMsg, expectedMsg, "Success message is not displayed as expected");
//
//    }

//    @Test
//    public void TC11() {
//        System.out.println("User can't create account while password and PID fields are empty");
//        HomePage homePage = new HomePage();
//        homePage.open();
//        RegisterPage registerPage = homePage.gotoRegister();
//        registerPage.getTxtEmail().sendKeys("samle@gmail.com");
//        registerPage.getBtnRegister().submit();
//        String actualMsg = registerPage.getLblRegisterErrorMsg().getText();
//        String expectedMsg = "There're errors in the form. Please correct the errors and try again.";
//        Assert.assertEquals(actualMsg, expectedMsg, "");
//    }

//    @Test
//    public void TC12() {
//        System.out.println("TC12 - Errors display when password reset token is blank");
//        HomePage homePage = new HomePage();
//        homePage.open();
//        LoginPage loginPage = homePage.gotoLoginPage();
//        ForgotPasswordPage forgotPasswordPage = homePage.gotoForgotPasswordPage();
//        forgotPasswordPage.forgotPasswordPage("yentran123@gmail.com");
//        String actualMsg = loginPage.getErrorMessage();
//        String expectedMsg = "The password reset token is incorrect or may be expired. Visit the forgot password page to generate a new one.";
//        Assert.assertEquals(actualMsg, expectedMsg, "Message is not displayed as expected");
//    }

//    @Test
//    public void TC13(){
//        System.out.println("TC13 - Errors display if password and confirm password don't match when resetting password");
//        HomePage homePage = new HomePage();
//        homePage.open();
//
//        LoginPage loginPage = homePage.gotoLoginPage();
//        ForgotPasswordPage forgotPasswordPage = homePage.gotoForgotPasswordPage();
//
//        forgotPasswordPage.getTxtEmailAddress().sendKeys("loveinamist861612gmail.com");
//        forgotPasswordPage.getBtnSendInstructors().click();
//
//        ChangePasswordPage changePasswordPage = homePage.gotoChangePasswordPage();
//        boolean managePasswordIsDisplay = true;
//        try {
//            WebElement changePasswordButton = Constant.WEBDRIVER.findElement(By.xpath("//input[@value='Change Password']"));
//            managePasswordIsDisplay = changePasswordButton.isDisplayed();
//        } catch (NoSuchElementException e) {
//            managePasswordIsDisplay = false;
//        }
//
//        Assert.assertTrue(managePasswordIsDisplay, "Could not reset password. Please correct the errors and try again.");
//
//        changePasswordPage.getTxtCurrentPassword().sendKeys("12345678");
//        changePasswordPage.getTxtNewPassword().sendKeys("123456789");
//        changePasswordPage.getTxtConfirmPassword().sendKeys("123456789");
//        changePasswordPage.getBtnChangePassword().click();
//
//        String actualSuccessMsg = changePasswordPage.getLblChangePasswordErrorMsg().getText();
//        String expectedSuccessMsg = "Could not reset password. Please correct the errors and try again.";
//        Assert.assertEquals(actualSuccessMsg, expectedSuccessMsg, "Error message is not displayed as expected");
//    }
//
@Test
public void TC15() throws InterruptedException {
    System.out.println("TC15 - User can open 'Book ticket' page by clicking on 'Book ticket' link in 'Train timetable' page");

    HomePage homePage = new HomePage();
    homePage.open();

    LoginPage loginPage = homePage.gotoLoginPage();
    loginPage.login(Constant.USERNAME, Constant.PASSWORD);
    TimeTablePage trainTimetablePage = homePage.gotoTrainTimetablePage();
    trainTimetablePage.SelectbookTicketClick();
    Thread.sleep(2000);
    Select DepartFrom= new Select(Constant.WEBDRIVER.findElement(By.xpath("//*[@id=\"content\"]/div[1]/form/fieldset/ol/li[2]/select")));
    Select ArriveStation=new Select(Constant.WEBDRIVER.findElement(By.xpath("//*[@id=\"ArriveStation\"]/select")));
    ArrayList<String> compare = new ArrayList<String>();
    compare.add(DepartFrom.getFirstSelectedOption().getText());
    compare.add(ArriveStation.getFirstSelectedOption().getText());
    List<String> expectedInfo = Arrays.asList("Sài Gòn", "Huế");
    Assert.assertEquals(compare, expectedInfo, "Booking information doesn't match");
}
}
//
//
//
//
//
//



