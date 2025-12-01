package tests;

import models.User;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RemoveContactTests extends TestBase{

    @BeforeMethod
    public void preCondition() {
        if(!app.getHelperUser().isLogged()) {
            app.getHelperUser().login(new User().setEmail("mikh.panfilovv@gmail.com").setPassword("Mixan31145$"));
        }

       // app.getHelperContact().provideContact(); //if contact list size<3 ==> add 3 contacts
    }

    @Test
    public void removeFirstContact() {
        //Assert --> size contact list-1
        app.getHelperContact().openContactsForm();
        app.getHelperContact().openFirstContactCard();
        app.getHelperContact().clickRemoveButton();
    }

    @Test
    public void removeAllContacts() {
        //Assert --> "No contacts here!"
        app.getHelperContact().openContactsForm();
        while (app.getHelperContact().isElementPresent(By.cssSelector(".contact-item_card__2SOIM:first-of-type"))) {
            app.getHelperContact().openFirstContactCard();
            app.getHelperContact().clickRemoveButton();
        }

    }

}
