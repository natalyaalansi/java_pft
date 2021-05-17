package ru.stqa.pft.addressbook.model;

public class UserData {
  private int id;
  private final String firstname;
  private final String lastname;
  private final String address;
  private final String home;
  private final String email;
  private final String group;

  public UserData(String firstname, String lastname, String address, String home, String email, String group) {
    this.id = Integer.MAX_VALUE;
    this.firstname = firstname;
    this.lastname = lastname;
    this.address = address;
    this.home = home;
    this.email = email;
    this.group = group;
  }

  public UserData(int id, String firstname, String lastname, String address, String home, String email, String group) {
    this.id = id;
    this.firstname = firstname;
    this.lastname = lastname;
    this.address = address;
    this.home = home;
    this.email = email;
    this.group = group;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
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

  public String getHome() {
    return home;
  }

  public String getEmail() {
    return email;
  }

  public String getGroup() {
    return group;
  }

  @Override
  public String toString() {
    return "UserData{" +
            "id='" + id + '\'' +
            ", firstname='" + firstname + '\'' +
            ", lastname='" + lastname + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    UserData userData = (UserData) o;

    if (firstname != null ? !firstname.equals(userData.firstname) : userData.firstname != null) return false;
    return lastname != null ? lastname.equals(userData.lastname) : userData.lastname == null;
  }

  @Override
  public int hashCode() {
    int result = firstname != null ? firstname.hashCode() : 0;
    result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
    return result;
  }

}
