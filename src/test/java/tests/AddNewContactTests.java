package tests;

import models.Contact;
import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;

public class AddNewContactTests extends TestBase{

    @BeforeClass
    public void preCondition() {
        if(!app.getHelperUser().isLogged())
            app.getHelperUser().login(new User().setEmail("mikh.panfilovv@gmail.com").setPassword("Mixan31145$"));
    }

    @Test
    public void addContactSuccessAllFields() {
        int i = new Random().nextInt(1000)+1000;

        Contact contact = Contact.builder()
                        .name("Sam"+i)
                        .lastName("Norm")
                        .phone("05365327"+i)
                        .email("uytb"+i+"@gmail.com")
                        .address("Haifa,Israel")
                        .description("all fields")
                        .build();

        app.getHelperContact().openAddForm();
        app.getHelperContact().fillAddContactForm(contact);
       // app.getHelperContact().pause(15000);
        app.getHelperContact().getScreen("src/test/screenshots/screen-"+i+".png");
        app.getHelperContact().submitSave();

        Assert.assertTrue(app.getHelperContact().isContactAddedByName(contact.getName()));
        Assert.assertTrue(app.getHelperContact().isContactAddedByPhone(contact.getPhone()));
    }

    @Test
    public void addContactSuccessRequiredFields() {
        int i = new Random().nextInt(1000)+1000;

        Contact contact = Contact.builder()
                .name("Sam")
                .lastName("Norm")
                .phone("05365327"+i)
                .email("uytb"+i+"@gmail.com")
                .address("Haifa,Israel")
                .build();

        app.getHelperContact().openAddForm();
        app.getHelperContact().fillAddContactForm(contact);
       // app.getHelperContact().pause(15000);
        app.getHelperContact().submitSave();

        Assert.assertTrue(app.getHelperContact().isContactAddedByName(contact.getName()));
        Assert.assertTrue(app.getHelperContact().isContactAddedByPhone(contact.getPhone()));
    }

    @Test
    public void addNewContactWrongName() {

        Contact contact = Contact.builder()
                .name("")
                .lastName("Norm")
                .phone("053653274321")
                .email("uytb@gmail.com")
                .address("Haifa,Israel")
                .description("wrong name")
                .build();

        app.getHelperContact().openAddForm();
        app.getHelperContact().fillAddContactForm(contact);
        //app.getHelperContact().pause(15000);
        app.getHelperContact().submitSave();

        Assert.assertTrue(app.getHelperContact().isAddNewContactPageStillDisplayed());
    }

    @Test
    public void addNewContactWrongLastName() {

        Contact contact = Contact.builder()
                .name("Sam")
                .lastName("")
                .phone("053653274321")
                .email("uytb@gmail.com")
                .address("Haifa,Israel")
                .description("wrong last name")
                .build();

        app.getHelperContact().openAddForm();
        app.getHelperContact().fillAddContactForm(contact);
       // app.getHelperContact().pause(15000);
        app.getHelperContact().submitSave();

        Assert.assertTrue(app.getHelperContact().isAddNewContactPageStillDisplayed());

    }

    @Test
    public void addNewContactWrongEmail() {

        Contact contact = Contact.builder()
                .name("Sam")
                .lastName("Norm")
                .phone("053653274321")
                .email("uytbgmail.com")
                .address("Haifa,Israel")
                .description("wrong email")
                .build();

        app.getHelperContact().openAddForm();
        app.getHelperContact().fillAddContactForm(contact);
       // app.getHelperContact().pause(15000);
        app.getHelperContact().submitSave();

        Assert.assertTrue(app.getHelperContact().isAddNewContactPageStillDisplayed());
        Assert.assertTrue(app.getHelperUser().isAlertPresent("Email not valid:"));

    }

    @Test
    public void addNewContactWrongPhone() {

        Contact contact = Contact.builder()
                .name("Sam")
                .lastName("Norm")
                .phone("")
                .email("uytb@gmail.com")
                .address("Haifa,Israel")
                .description("wrong phone")
                .build();

        app.getHelperContact().openAddForm();
        app.getHelperContact().fillAddContactForm(contact);
        //app.getHelperContact().pause(15000);
        app.getHelperContact().submitSave();

        Assert.assertTrue(app.getHelperContact().isAddNewContactPageStillDisplayed());
        Assert.assertTrue(app.getHelperUser().isAlertPresent(" Phone not valid: Phone number must contain only digits! And length min 10, max 15!"));

    }

    @Test
    public void addNewContactWrongAddress() {

        Contact contact = Contact.builder()
                .name("Sam")
                .lastName("Norm")
                .phone("053653274321")
                .email("uytb@gmail.com")
                .address("")
                .description("wrong address")
                .build();

        app.getHelperContact().openAddForm();
        app.getHelperContact().fillAddContactForm(contact);
       // app.getHelperContact().pause(15000);
        app.getHelperContact().submitSave();

        Assert.assertTrue(app.getHelperContact().isAddNewContactPageStillDisplayed());


    }

}
