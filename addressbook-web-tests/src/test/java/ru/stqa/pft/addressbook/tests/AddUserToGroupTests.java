package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.UserData;

public class AddUserToGroupTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1"));
    }

    if (app.db().users().size() == 0 || app.db().usersWithoutGroup().size() == 0) {
      app.goTo().userPage();
      app.user().create(new UserData().withFirstname("petr").withLastname("petrov").withAddress("spb").withHomePhone("lenina").withEmail("gg@gg.gg"), true);
    }
  }

  @Test
  public void testAddUserToGroup() {
    UserData user = app.db().users().iterator().next();
    GroupData group = app.db().groups().iterator().next();
    app.goTo().homePage();
    app.user().addUserToGroup(user, group);
    UserData addedUser = app.db().userById(user.getId());
    Assert.assertTrue(addedUser.getGroups().contains(group));
  }
}
