package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import ru.stqa.pft.addressbook.model.UserData;
import ru.stqa.pft.addressbook.model.Users;

public class DeleteUserFromGroupTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1"));
    }

    if (app.db().usersInGroup().size() == 0) {
      if (app.db().users().size() == 0) {
        app.goTo().userPage();
        app.user().create(new UserData().withFirstname("petr").withLastname("petrov").withAddress("spb").withHomePhone("lenina").withEmail("gg@gg.gg"), true);
      }
      UserData user = app.db().users().iterator().next();
      GroupData group = app.db().groups().iterator().next();
      app.goTo().homePage();
      app.user().addUserToGroup(user, group);
    }
  }

  @Test
  public void testDeleteUserFromGroup() {
    Users usersInGroups = app.db().usersInGroup();
    Groups groups = app.db().groups();
    UserData userInGroup = usersInGroups.iterator().next();
    GroupData group = groups.iterator().next();
    app.goTo().homePage();
    app.user().deleteUserFromGroup(userInGroup, group);
    UserData deletedUserFromGroup = app.db().userById(userInGroup.getId());
    Assert.assertFalse(deletedUserFromGroup.getGroups().contains(group));
  }
}

