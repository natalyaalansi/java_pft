package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

import java.util.Comparator;
import java.util.List;

public class UserModificationTests extends TestBase {

  @Test (enabled = false)
  public void testUserModification() {
    if (!app.getUserHelper().isThereAUser()) {
      app.getNavigationHelper().gotoUserPage();
      app.getUserHelper().createUser(new UserData("nata", "ansi", "kush", "spb", "gg@gg.gg", "test1"), true);
    }
    List<UserData> before = app.getUserHelper().getUserList();
    app.getUserHelper().initUserModification(before.size() - 1);
    UserData user = new UserData(before.get(before.size() - 1).getId(), "oota", "anna", "ku", "sp", "ololo@gg.gg", null);
    app.getUserHelper().fillUserForm(user, false);
    app.getUserHelper().submitUserModification();
    app.getUserHelper().returnHomePage();
    List<UserData> after = app.getUserHelper().getUserList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size() - 1);
    before.add(user);
    Comparator<? super UserData> byId = (u1, u2) -> Integer.compare(u1.getId(), u2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
