package tests;

import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegistrationTests extends TestBase {

    @BeforeMethod
    public void preCondition() {
        if(app.getHelperUser().isLogged()) {
            app.getHelperUser().logout();
        }
    }

    @Test
    public void registrationSuccess() {
        int z = (int)((System.currentTimeMillis() / 1000) % 3600);

        User user = new User()
                .setEmail("mikh"+z+"@gmail.com")
                .setPassword("Mikh12345$");

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().submitRegistration();

        Assert.assertTrue(app.getHelperUser().isLogged());
    }

    @Test
    public void registrationWrongEmail() {
        int z = (int)((System.currentTimeMillis() / 1000) % 3600);

        User user = new User()
                .setEmail("mikh"+z+"gmail.com")
                .setPassword("Mikh12345$");

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().submitRegistration();

        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password format"));

    }

    @Test
    public void registrationWrongPassword() {
        int z = (int)((System.currentTimeMillis() / 1000) % 3600);

        User user = new User()
                .setEmail("mikh"+z+"@gmail.com")
                .setPassword("Mikh12");

        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillRegistrationForm(user);
        app.getHelperUser().submitRegistration();

        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password format"));
    }

}
