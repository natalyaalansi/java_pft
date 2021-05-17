package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

import java.util.List;

public class UserDeletionTests extends TestBase {

  @Test
  public void testUserDeletion() {
    if (!app.getUserHelper().isThereAUser()) {
      app.getNavigationHelper().gotoUserPage();
      app.getUserHelper().createUser(new UserData("nata", "ansi", "kush", "spb", "gg@gg.gg", "test1"), true);
    }
    List<UserData> before = app.getUserHelper().getUserList();
    app.getUserHelper().selectUser(before.size() - 1);
    app.getUserHelper().deleteSelectedUsers();
    app.getNavigationHelper().gotoHomePage();
    List<UserData> after = app.getUserHelper().getUserList();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(before.size() - 1);
    Assert.assertEquals(before, after);
  }
}
