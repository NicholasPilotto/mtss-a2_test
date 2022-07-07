////////////////////////////////////////////////////////////////////
// [NICHOLAS] [PILOTTO] [1230237]
// [GIOVANNI] [GARDIN] [2010003]
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import java.time.LocalTime;
import java.util.ArrayList;

public class OrderTest {
  private Order order;
  private User user;
  private ArrayList<EItem> items;
  private LocalTime time;

  @Before
  public void setupObject() {
    items = new ArrayList<EItem>();
    items.add(new EItem("Logitech", 9.99, itemType.Mouse));

    user = new User("RossiMario", "Mario", "Rossi", 45);

    time = LocalTime.of(10, 0, 0);

    order = new Order(items, user, time, 9.99);
  }

  @Test(expected = IllegalArgumentException.class)
  public void itemsListNullTest() {
    new Order(null, user, time, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void itemsListIsEmptyTest() {
    new Order(new ArrayList<EItem>(), user, time, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public  void userNullTest() {
    new Order(items, null, time, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void localTimeNullTest() {
    new Order(items, user, null, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void priceNegativeTest() {
    new Order(items, user, time, -5);
  }

  @Test
  public void orderGetElementListTest() {
    assertEquals(items, order.getItemsList());
  }

  @Test
  public void orderGetUserTest() {
    assertEquals(user, order.getUser());
  }

  @Test
  public void orderGetTimeTest() {
    assertEquals(time, order.getTime());
  }

  @Test
  public void orderGetPriceTest() {
    assertEquals(9.99, order.getPrice(), 0.0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void orderSetNegativePriceTest() {
    order.setPrice(-1);
  }

  @Test
  public void orderSetPriceTest() {
    order.setPrice(15.99);
    assertEquals(15.99, order.getPrice(), 0.0);
  }
}