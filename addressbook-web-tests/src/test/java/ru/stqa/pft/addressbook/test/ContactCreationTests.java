package ru.stqa.pft.addressbook.test;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    app.getNavigationHelper().gotoContactCreation();
    app.getContactHelper().fillContactForm(new ContactData("nata", "ansi", "lenina", "patusa", "4242"));
    app.getContactHelper().submitContactCreation();
    app.getContactHelper().returnToContactPage();
  }
}
