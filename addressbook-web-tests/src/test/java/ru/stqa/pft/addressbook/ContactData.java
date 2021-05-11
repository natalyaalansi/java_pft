package ru.stqa.pft.addressbook;

public class ContactData {
  private final String firstname;
  private final String lastname;
  private final String address;
  private final String email;
  private final String home;

  public ContactData(String firstname, String lastname, String address, String email, String home) {
    this.firstname = firstname;
    this.lastname = lastname;
    this.address = address;
    this.email = email;
    this.home = home;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public String getAddress() {
    return address;
  }

  public String getEmail() {
    return email;
  }

  public String getHome() {
    return home;
  }
}
