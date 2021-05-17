package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

import java.util.Comparator;
import java.util.List;

public class UserCreationTests extends TestBase {

  @Test
  public void testUserCreation() {
    List<UserData> before = app.getUserHelper().getUserList();
    app.getNavigationHelper().gotoUserPage();
    UserData user = new UserData("nata2", "ansi3", "kush", "spb", "gg@gg.gg", "test1");
    app.getUserHelper().fillUserForm(user, true);
    app.getUserHelper().submitUserCreation();
    app.getUserHelper().returnHomePage();
    List<UserData> after = app.getUserHelper().getUserList();
    Assert.assertEquals(after.size(), before.size() + 1);

    before.add(user);
    Comparator<? super UserData> byId = (u1, u2) -> Integer.compare(u1.getId(), u2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
