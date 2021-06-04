package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import ru.stqa.pft.addressbook.model.UserData;
import ru.stqa.pft.addressbook.model.Users;

public class AddUserToGroupTests extends TestBase {

  private int maxId;

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1"));
    }

    if (app.db().users().size() == 0) {
      app.goTo().userPage();
      app.user().create(new UserData().withFirstname("petr").withLastname("petrov").withAddress("spb").withHomePhone("lenina").withEmail("gg@gg.gg"), true);
    }
  }

  @Test
  public void testAddUserToGroup() {
    GroupData modifyGroup = app.db().groups().iterator().next();
    UserData modifyUser = new UserData();
    Users usersBefore = app.db().users();
    boolean createUser = false;

    for (UserData user : usersBefore) {
      if (user.getGroups().size() == 0) {
        modifyUser = user;
        app.user().addUserToGroup(modifyUser, modifyGroup);
        break;
      } else {
        createUser = true;
      }
    }
    if (createUser) {
      app.goTo().userPage();
      app.user().create(new UserData().withFirstname("petr").withLastname("petrov")
              .withAddress("spb").withHomePhone("lenina").withEmail("gg@gg.gg"), true);
      Users getUsersAfterCreation = app.db().users();
      for (UserData eachUser : getUsersAfterCreation) {
        if (eachUser.getId() > maxId) {
          modifyUser = eachUser;
        }
        app.user().addUserToGroup(modifyUser, modifyGroup);
      }
    }
    UserData addedUser = app.db().userById(modifyUser.getId());
    Assert.assertTrue(addedUser.getGroups().contains(modifyGroup));
  }
}
