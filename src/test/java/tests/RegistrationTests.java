package tests;

import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegistrationTests extends TestBase {


    @BeforeMethod(alwaysRun = true)
    public void preCondition() {
        //if SignOut button present --->logout
        if (app.getHelperUser().isLogged()) {
            app.getHelperUser().logout();
        }
    }

    @Test
    public void registrationSuccess() {
        int i = (int) ((System.currentTimeMillis() / 1000) % 3600);
        User user = new User().withEmail("don" + i + "@gmail.com").withPassword("Ddon14568$");

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitRegistration();

        Assert.assertTrue(app.getHelperUser().isLogged());
        Assert.assertTrue(app.getHelperUser().isNoContactsHereDisplayed());
    }

    @Test(description = "Bug report #23456 Fixed",groups = {"smoke"})
    public void registrationWrongEmail() {
        User user = new User().withEmail("dongmail.com").withPassword("Ddon14568$");

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitRegistration();

        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password format"));
    }


    @Test
    public void registrationWrongPassword() {
        User user = new User().withEmail("don@gmail.com").withPassword("Ddon145");

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitRegistration();

        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password format"));
    }

    @Test
    public void registrationExistsUser() {
        User user = new User().withEmail("margo@gmail.com").withPassword("Mmar123456$");

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitRegistration();

        Assert.assertTrue(app.getHelperUser().isAlertPresent("User already exist"));
    }




}
