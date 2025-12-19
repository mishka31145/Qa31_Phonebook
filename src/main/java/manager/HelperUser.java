package manager;

import models.User;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HelperUser extends HelperBase {

    public HelperUser(WebDriver wd) {
        super(wd);
    }


    public void openLoginRegistrationForm() {
        // WebElement loginTab = wd.findElement(By.cssSelector("a[href='/login']"));
//        WebElement loginTab = wd.findElement(By.xpath("//a[text()='LOGIN']"));
//        loginTab.click();
        click(By.cssSelector("a[href='/login']"));
        logger.info("open form by click on button with locator By.cssSelector(\"a[href='/login']\") ");
    }


    public void fillLoginRegistrationForm(String email, String password) {
//        WebElement emailInput = wd.findElement(By.name("email"));
//        emailInput.click();
//        emailInput.clear();
//        emailInput.sendKeys(email);

        type(By.name("email"), email);
        logger.info("type in input with locator By.name(\"email\")");
//
//        WebElement passwordInput = wd.findElement(By.xpath("//input[@placeholder='Password']"));
//        passwordInput.click();
//        passwordInput.clear();
//        passwordInput.sendKeys(password);
       type(By.xpath("//input[@placeholder='Password']"), password);
        //type(By.xpath("//input[@placeholder='Pass']"), password);
        logger.info("type in input with locator By.xpath(\"//input[@placeholder='Password']\") ");
    }

    public void fillLoginRegistrationForm(User user) {
        type(By.name("email"), user.getEmail());
        type(By.xpath("//input[@placeholder='Password']"), user.getPassword());

    }

    public void submitLogin() {
        click(By.xpath("//button[text()='Login']"));
    }

    public boolean isLogged() {
        return isElementPresent(By.xpath("//button[text()='Sign Out']"));
    }

    public void logout() {
        click(By.xpath("//button[text()='Sign Out']"));
    }

    public boolean isAlertPresent(String message) {
        Alert alert = new WebDriverWait(wd, Duration.ofSeconds(10))
                .until(ExpectedConditions.alertIsPresent());
        System.out.println(alert.getText());
        if (alert != null && alert.getText().contains(message)) {
            alert.accept(); //--click ok
//            alert.dismiss(); //--click cancel
//            alert.sendKeys("hello");// type into alert
            return true;
        }
        return false;
    }

    //*********************Registration**********************************

    public void submitRegistration() {
        click(By.xpath("//button[text()='Registration']"));
    }



    public void login(User user) {
        openLoginRegistrationForm();
        fillLoginRegistrationForm(user);
        submitLogin();
    }
}
