package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;
import ru.stqa.pft.addressbook.model.Users;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.user().all().size() == 0) {
      app.goTo().userPage();
      app.user().create(new UserData().withFirstname("petr").withLastname("petrov").withAddress("spb").withHomePhone("lenina").withEmail("gg@gg.gg").withGroup("test1"), true);
    }
  }

  @Test
  public void testUserModification() {
    Users before = app.user().all();
    UserData modifiedUser = before.iterator().next();
    UserData user = new UserData()
            .withId(modifiedUser.getId()).withFirstname("ivan").withLastname("ivanov")
            .withAddress("msk").withHomePhone("trud").withEmail("yy@gg.gg").withGroup(null);
    app.user().modify(user);
    assertThat(app.user().count(), equalTo(before.size()));
    Users after = app.user().all();
    assertThat(after, equalTo(before.without(modifiedUser).withAdded(user)));
  }
}
