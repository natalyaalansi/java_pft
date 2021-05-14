package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.UserData;

public class UserHelper extends HelperBase {

  public UserHelper(WebDriver wd) {
    super(wd);
  }

  public void returnHomePage() {
    click(By.linkText("home page"));
  }

  public void submitUserCreation() {
    click(By.name("submit"));
  }

  public void fillUserForm(UserData userData, boolean creation) {
    type(By.name("firstname"), userData.getFirstname());
    type(By.name("lastname"), userData.getLastname());
    type(By.name("address"), userData.getAddress());
    type(By.name("home"), userData.getHome());
    type(By.name("email"), userData.getEmail());

    if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(userData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void selectUser() {
    click(By.name("selected[]"));
  }

  public void deleteSelectedUsers() {
    click(By.xpath("//input[@value='Delete']"));
    wd.switchTo().alert().accept();
  }

  public void initUserModification() {
    click(By.xpath("//img[@alt='Edit']"));
  }

  public void submitUserModification() {
    click(By.name("update"));
  }
}
