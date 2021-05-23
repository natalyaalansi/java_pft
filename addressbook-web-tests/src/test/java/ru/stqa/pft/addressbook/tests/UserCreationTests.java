package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;
import ru.stqa.pft.addressbook.model.Users;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserCreationTests extends TestBase {

  @Test
  public void testUserCreation() {
    app.goTo().homePage();
    Users before = app.user().all();
    app.goTo().userPage();
    File photo = new File("src/test/resources/cat.png");
    UserData user = new UserData().withFirstname("nata2").withLastname("ansi3").withPhoto(photo).withAddress("spb").withHomePhone("kush")
            .withEmail("gg@gg.gg").withGroup("test1");
    app.user().create(user, true);
    assertThat(app.user().count(), equalTo(before.size() + 1));
    Users after = app.user().all();
    assertThat(after, equalTo(
            before.withAdded(user.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }
}
