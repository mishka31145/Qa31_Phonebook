package tests;

import models.Contact;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AddNewContactTests extends TestBase{

    @BeforeClass
    public void preCondition() {
        if(!app.getHelperUser().isLogged())
            app.getHelperUser().login("mikh.panfilovv@gmail.com","Mixan31145$");
    }

    @Test
    public void addContactSuccess() {
        Contact contact = Contact.builder()
                        .name("Sam")
                        .lastName("Norm")
                        .phone("0536532785")
                        .email("uytb@gmail.com")
                        .address("Haifa,Israel")
                        .description("Friend")
                        .build();

        app.getHelperContact().openAddForm();
        app.getHelperContact().fillAddForm(contact);
        app.getHelperContact().submitSave();
    }

}
