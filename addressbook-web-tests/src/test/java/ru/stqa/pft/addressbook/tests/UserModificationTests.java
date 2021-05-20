package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;
import ru.stqa.pft.addressbook.model.Users;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class UserModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.user().all().size() == 0) {
      app.goTo().userPage();
      app.user().create(new UserData().withFirstname("petr").withLastname("petrov").withAddress("spb").withHome("lenina").withEmail("gg@gg.gg").withGroup("test1"), true);
    }
  }

  @Test
  public void testUserModification() {
    Users before = app.user().all();
    UserData modifiedUser = before.iterator().next();
    UserData user = new UserData()
            .withId(modifiedUser.getId()).withFirstname("ivan").withLastname("ivanov")
            .withAddress("msk").withHome("trud").withEmail("yy@gg.gg").withGroup(null);
    app.user().modify(user);
    Users after = app.user().all();
    assertEquals(after.size(), before.size());
    assertThat(after, equalTo(before.without(modifiedUser).withAdded(user)));
  }
}
