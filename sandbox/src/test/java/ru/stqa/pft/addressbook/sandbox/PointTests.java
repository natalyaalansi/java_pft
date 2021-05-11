package ru.stqa.pft.addressbook.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

  @Test
  public void testDistanceCase1() {
    Point p1 = new Point(3, 7);
    Point p2 = new Point(-2, -6);
    Assert.assertEquals(p1.distance(p2), 13.92838827718412);
  }

  @Test
  public void testDistanceCase2() {
    Point p1 = new Point(2.0, 3.0);
    Point p2 = new Point(4.0, 8.0);
    Assert.assertEquals(p1.distance(p2), 5.385164807134504);
  }
}
