package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

public class UserModificationTests extends TestBase{

  @Test
  public void testUserModification(){
    app.getUserHelper().initUserModification();
    app.getUserHelper().fillUserForm(new UserData("oota", "anna", "ku", "sp", "ololo@gg.gg", null), false);
    app.getUserHelper().submitUserModification();
    app.getUserHelper().returnHomePage();
  }
}
