package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;
import ru.stqa.pft.addressbook.model.Users;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserCreationTests extends TestBase {

  @Test
  public void testUserCreation() {
    app.goTo().homePage();
    Users before = app.user().all();
    app.goTo().userPage();
    UserData user = new UserData().withFirstname("nata2").withLastname("ansi3").withAddress("spb").withHome("kush").withEmail("gg@gg.gg").withGroup("test1");
    app.user().create(user, true);
    Users after = app.user().all();
    assertThat(after.size(), equalTo(before.size() + 1));
    assertThat(after, equalTo(
            before.withAdded(user.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }
}
