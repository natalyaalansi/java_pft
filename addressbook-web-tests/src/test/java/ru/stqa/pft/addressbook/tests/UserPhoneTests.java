package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserPhoneTests extends TestBase {

  @Test
  public void testUserPhones() {
    app.goTo().homePage();
    UserData user = app.user().all().iterator().next();
    UserData userInfoFromEditForm = app.user().infoFromEditForm(user);

    assertThat(user.getAllPhones(), equalTo(mergePhones(userInfoFromEditForm)));
  }

  private String mergePhones(UserData user) {
    return Arrays.asList(user.getHomePhone(), user.getWorkPhone(), user.getMobilePhone())
            .stream().filter((s) -> !s.equals(""))
            .map(UserPhoneTests::cleaned)
            .collect(Collectors.joining("\n"));
  }

  public static String cleaned(String phone) {
    return phone.replaceAll("\\s", "").replaceAll("[-()]", "");

  }
}
