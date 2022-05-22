////////////////////////////////////////////////////////////////////
// [NICHOLAS] [PILOTTO] [1230237]
// [GIOVANNI] [GARDIN] [2010003]
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.business;

import it.unipd.mtss.business.exception.BillException;
import it.unipd.mtss.model.EItem;

import it.unipd.mtss.model.itemType;
import it.unipd.mtss.model.User;

import java.util.ArrayList;


import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class RevenueTest {
  private EItem keyboard;
  private EItem motherboard;
  private EItem mouse;
  private EItem processor;

  private Revenue revenue;
  private User user;

  private ArrayList<EItem> emptyList;
  private ArrayList<EItem> nullList;
  private ArrayList<EItem> itemsList;
  private ArrayList<EItem> processors6List;
  private ArrayList<EItem> processor4List;
  private ArrayList<EItem> keyboardMouseList;

  @Before
  public void setUpObject() {
    keyboard = new EItem("Logitech x 300", 45.6, itemType.Keyboard);
    motherboard = new EItem("RoG zy 1080", 1099.99, itemType.Motherboard);
    mouse = new EItem("Asus L3", 9.99, itemType.Mouse);
    processor = new EItem("Intel i5", 49.99, itemType.Processor);

    emptyList = new ArrayList<EItem>();

    itemsList = new ArrayList<EItem>();
    itemsList.add(keyboard);
    itemsList.add(motherboard);
    itemsList.add(mouse);
    itemsList.add(processor);

    nullList = new ArrayList<EItem>();
    nullList.add(keyboard);
    nullList.add(null);

    keyboardMouseList = new ArrayList<>();
    keyboardMouseList.add(keyboard);
    keyboardMouseList.add(mouse);

    processors6List = new ArrayList<>();
    processors6List.add(new EItem("Intel i1", 49.99, itemType.Processor));
    processors6List.add(new EItem("Intel i2", 79.99, itemType.Processor));
    processors6List.add(new EItem("Intel i3", 19.99, itemType.Processor));
    processors6List.add(new EItem("Intel i4", 29.99, itemType.Processor));
    processors6List.add(new EItem("Intel i5", 109.99, itemType.Processor));
    processors6List.add(new EItem("Intel i6", 89.99, itemType.Processor));

    processor4List = new ArrayList<>();
    processor4List.add(new EItem("Intel i7", 99.99, itemType.Processor));
    processor4List.add(new EItem("Intel i8", 29.99, itemType.Processor));
    processor4List.add(new EItem("Intel i9", 69.99, itemType.Processor));
    processor4List.add(new EItem("Intel i10", 19.99, itemType.Processor));

    user = new User("MarioRossi", "Mario", "Rossi");

    revenue = new Revenue();
  }

  @Test(expected = BillException.class)
  public void getTotalNullTest() {
    revenue.getOrderPrice(null, user);
  }

  @Test(expected = BillException.class)
  public void getTotalEmptyArrayListTest() {
    revenue.getOrderPrice(emptyList, user);
  }

  @Test(expected = BillException.class)
  public void getTotalNullArrayListTest() {
    revenue.getOrderPrice(nullList, user);
  }

  @Test
  public void getTotalTest() {
    assertEquals(1205.57, revenue.getOrderPrice(itemsList, user), 0.0);
  }

  @Test
  public void getTotalWithProcessorsSaleTest() {
    assertEquals(369.95, revenue.getOrderPrice(processors6List, user), 0.1);
  }

  @Test
  public void getTotalWithNoProcessorSaleTest() {
    assertEquals(219.96, revenue.getOrderPrice(processor4List, user), 0.1);
  }

  @Test
  public void getTotalWithSameKeyboardAndMouseTest() {
    assertEquals(45.6, revenue.getOrderPrice(keyboardMouseList, user), 0.0);
  }

  @Test
  public void getTotalWithDifferenteKeyboardAndMouseTest() {
    keyboardMouseList.add(new EItem("Asus Keyboard", 19.99, itemType.Keyboard));
    assertEquals(75.58, revenue.getOrderPrice(keyboardMouseList, user), 0.1);
  }
}