////////////////////////////////////////////////////////////////////
// [NICHOLAS] [PILOTTO] [1230237]
// [GIOVANNI] [GARDIN] [2010003]
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.business;

import it.unipd.mtss.business.exception.BillException;
import it.unipd.mtss.model.EItem;

import it.unipd.mtss.model.Order;
import it.unipd.mtss.model.itemType;
import it.unipd.mtss.model.User;

import java.awt.image.AreaAveragingScaleFilter;
import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class RevenueTest {
  private EItem keyboard;
  private EItem motherboard;
  private EItem mouse;
  private EItem anotherMouse;
  private EItem processor;

  private Revenue revenue;
  private User user;

  private int freeMouseThreshold;

  private ArrayList<EItem> emptyList;
  private ArrayList<EItem> nullList;
  private ArrayList<EItem> itemsList;
  private ArrayList<EItem> miceList;
  private ArrayList<EItem> belowThresholdList;
  private ArrayList<EItem> processors6List;
  private ArrayList<EItem> processor4List;
  private ArrayList<EItem> keyboardMouseList;

  @Before
  public void setUpObject() {
    keyboard = new EItem("Logitech x 300", 45.6, itemType.Keyboard);
    motherboard = new EItem("RoG zy 1080", 1099.99, itemType.Motherboard);
    mouse = new EItem("Asus L3", 9.99, itemType.Mouse);
    anotherMouse = new EItem("Roccat Pure", 19.75, itemType.Mouse);
    processor = new EItem("Intel i5", 49.99, itemType.Processor);
    freeMouseThreshold = 10; //soglia per ricevere gratis il mouse più economico

    emptyList = new ArrayList<EItem>();
    itemsList = new ArrayList<EItem>();
    miceList = new ArrayList<EItem>();
    belowThresholdList = new ArrayList<EItem>();

    nullList = new ArrayList<>();
    nullList.add(keyboard);
    nullList.add(null);

    itemsList.add(keyboard);
    itemsList.add(motherboard);
    itemsList.add(mouse);
    itemsList.add(processor);

    for (int i = 0; i < freeMouseThreshold; i++) {
      miceList.add(mouse);
    }
    miceList.add(anotherMouse);

    belowThresholdList.add(mouse);

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

    user = new User("MarioRossi", "Mario", "Rossi", 45);

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
    assertEquals(1075.02, revenue.getOrderPrice(itemsList, user), 0.01); //alzo il delta a 1cent?
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

  @Test(expected = BillException.class)
  public void orderWithMore30OItemTest() {
    ArrayList<EItem> _items = new ArrayList<>();
    for(int i = 0; i < 36; i++) {
      _items.add(new EItem("Asus Keyboard", 19.99, itemType.Keyboard));
    }

    revenue.getOrderPrice(_items, user);
  }

  @Test(expected = BillException.class)
  public void giveAwayOrdersNullList() {
    revenue.giveAway(null);
  }

  @Test(expected = BillException.class)
  public void giveAwayOrdersEmptyList() {
    revenue.giveAway(new ArrayList<Order>());
  }
  //MTSS-10
  @Test
  public void freeItemIf10MiceTest() {
      assertEquals(99.90, revenue.getOrderPrice(miceList, user), 0.01); //alzo il delta a 1cent?
  }

  //MTSS-10
  @Test
  public void freeItemIfMoreThan10MiceTest() {
      miceList.add(mouse);
      assertEquals(109.66, revenue.getOrderPrice(miceList, user), 0.01); //alzo il delta a 1cent?
  }

  //MTSS-12
  @Test
  public void offerDiscountIfTotalOverThresholdTest() {
      assertEquals(1075.02, revenue.getOrderPrice(itemsList, user), 0.01); //alzo il delta a 1cent?
  }

  //MTSS-12
  //è necessario?
  @Test
  public void donotOfferDiscountIfTotalOverThresholdTest() {
      assertEquals(119.65, revenue.getOrderPrice(miceList, user), 0.01); //alzo il delta a 1cent?
  }

    //MTSS-14
  @Test
  public void addFeeTest() {
      assertEquals(11.99, revenue.getOrderPrice(belowThresholdList, user), 0.0);
  }

  @Test(expected = BillException.class)
  public void giveAwayNullListTest() {
    revenue.giveAway(null);
  }

  @Test(expected = BillException.class)
  public void giveAwayEmptyListTest() {
    revenue.giveAway(new ArrayList<Order>());
  }

  @Test
  public void giveAwayNoFreeOrderTest() {
    Order order = new Order(itemsList, user, LocalTime.of(20, 0,0), 200);
    ArrayList<Order> aux = new ArrayList<>();
    aux.add(order);
    assertEquals(0, revenue.giveAway(aux).size());
  }

  @Test
  public void giveAwayWrongHourRangeTest() {
    User user = new User("MargheritaHack", "Margherita", "Hack", 17);
    Order order = new Order(itemsList, user, LocalTime.of(20, 0,0), 200);
    ArrayList<Order> aux = new ArrayList<>();
    aux.add(order);
    assertEquals(0, revenue.giveAway(aux).size());
  }

  @Test
  public void giveAwayWrongAgeTest() {
    User user = new User("MargheritaHack", "Margherita", "Hack", 20);
    Order order = new Order(itemsList, user, LocalTime.of(18, 30,0), 200);
    ArrayList<Order> aux = new ArrayList<>();
    aux.add(order);
    assertEquals(0, revenue.giveAway(aux).size());
  }

  @Test
  public void giveAwayTest() {
    User user = new User("MargheritaHack", "Margherita", "Hack", 18);
    Order order = new Order(itemsList, user, LocalTime.of(18, 30,0), 200);
    ArrayList<Order> aux = new ArrayList<>();
    aux.add(order);
    assertEquals(1, revenue.giveAway(aux).size());
  }

}