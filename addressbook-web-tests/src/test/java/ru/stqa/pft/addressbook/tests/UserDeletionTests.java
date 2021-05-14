package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

public class UserDeletionTests extends TestBase {

  @Test
  public void testUserDeletion() {
    if (!app.getUserHelper().isThereAUser()) {
      app.getNavigationHelper().gotoUserPage();
      app.getUserHelper().createUser(new UserData("nata", "ansi", "kush", "spb", "gg@gg.gg", "test1"), true);
    }
    app.getUserHelper().selectUser();
    app.getUserHelper().deleteSelectedUsers();
  }
}
