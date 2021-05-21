package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;
import ru.stqa.pft.addressbook.model.Users;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class UserDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.user().all().size() == 0) {
      app.goTo().userPage();
      app.user().create(new UserData().withFirstname("petr").withLastname("petrov").withAddress("spb").withHomePhone("lenina").withEmail("gg@gg.gg").withGroup("test1"), true);
    }
  }

  @Test
  public void testUserDeletion() {
    Users before = app.user().all();
    UserData deletedUser = before.iterator().next();
    app.user().delete(deletedUser);
    app.goTo().homePage();
    assertThat(app.user().count(), equalTo(before.size() - 1));
    Users after = app.user().all();
    assertThat(after, equalTo(before.without(deletedUser)));
  }
}
