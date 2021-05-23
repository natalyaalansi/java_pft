package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.model.UserData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class UserDataGenerator {

  @Parameter(names = "-c", description = "Users count")
  public int count;

  @Parameter(names = "-f", description = "Target file")
  public String file;

  @Parameter(names = "-d", description = "Data format")
  public String format;

  public static void main(String[] args) throws IOException {
    UserDataGenerator generator = new UserDataGenerator();
    JCommander jCommander = new JCommander(generator);
    try {
      jCommander.parse(args);
    } catch (ParameterException ex) {
      jCommander.usage();
      return;
    }
    generator.run();
  }

  private void run() throws IOException {
    List<UserData> users = generateUsers(count);
    if (format.equals("csv")) {
      saveAsCsv(users, new File(file));
    } else if (format.equals("xml")) {
      saveAsXml(users, new File(file));
    } else if (format.equals("json")) {
      saveAsJson(users, new File(file));
    } else {
      System.out.println("Unrecognized format" + format);
    }
  }

  private List<UserData> generateUsers(int count) {
    List<UserData> users = new ArrayList<UserData>();
    for (int i = 0; i < count; i++) {
      users.add(new UserData().withFirstname(String.format("test %s", i)).withLastname(String.format("testov %s", i))
              .withAddress(String.format("spb, lenina %s", i)).withHomePhone(String.format("+7931968000%s", i))
              .withEmail(String.format("olala@ola.ru%s", i)).withGroup(String.format("test1")));
    }
    return users;
  }

  private void saveAsJson(List<UserData> users, File file) throws IOException {
    Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
    String json = gson.toJson(users);
    Writer writer = new FileWriter(file);
    writer.write(json);
    writer.close();
  }

  private void saveAsXml(List<UserData> users, File file) throws IOException {
    XStream xStream = new XStream();
    xStream.processAnnotations(UserData.class);
    String xml = xStream.toXML(users);
    Writer writer = new FileWriter(file);
    writer.write(xml);
    writer.close();
  }

  private void saveAsCsv(List<UserData> users, File file) throws IOException {
    System.out.println(new File(".").getAbsolutePath());
    Writer writer = new FileWriter(file);
    for (UserData user : users) {
      writer.write(String.format("%s;%s;%s\n", user.getFirstname(), user.getLastname(), user.getAddress(), user.getHomePhone(), user.getEmail(), user.getGroup()));
    }
    writer.close();
  }

}
