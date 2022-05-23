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

  private ArrayList<EItem> emptyList;
  private ArrayList<EItem> nullList;
  private ArrayList<EItem> itemsList;
  private ArrayList<EItem> miceList;

  private ArrayList<EItem> keyboardMouseList;

  private String[] names;
  private String[] surnames;

  @Before
  public void setUpObject() {
    keyboard = new EItem("Logitech x 300", 45.6, itemType.Keyboard);
    motherboard = new EItem("RoG zy 1080", 1099.99, itemType.Motherboard);
    mouse = new EItem("Asus L3", 9.99, itemType.Mouse);
    anotherMouse = new EItem("Roccat Pure", 19.75, itemType.Mouse);
    processor = new EItem("Intel i5", 49.99, itemType.Processor);


    emptyList = new ArrayList<EItem>();
    itemsList = new ArrayList<EItem>();
    miceList = new ArrayList<EItem>();

    nullList = new ArrayList<>();
    nullList.add(keyboard);
    nullList.add(null);

    itemsList.add(keyboard);
    itemsList.add(motherboard);
    itemsList.add(mouse);
    itemsList.add(processor);

    miceList.add(anotherMouse);
    for (int i = 0; i < 10 - 1; i++) {
      miceList.add(mouse);
    }

    nullList = new ArrayList<EItem>();
    nullList.add(keyboard);
    nullList.add(null);

    keyboardMouseList = new ArrayList<>();
    keyboardMouseList.add(keyboard);
    keyboardMouseList.add(mouse);

    names = new String[] {
            "Mario",
            "Barbara",
            "Luca",
            "Giovanna",
            "Alberto",
            "Martina",
            "Marco",
            "Giulia",
            "Francesco",
            "Lucia",
            "Stefano",
            "Elisa",
            "Valter",
            "Ada",
            "Alessandro",
            "Elisabetta"
    };

    surnames = new String[] {
            "Rossi",
            "Gialli",
            "Verdi",
            "Bianchi",
            "Neri",
            "Jobs",
            "Torvalds",
            "Ritchie",
            "Stroustrup",
            "Gates",
            "Lovelace",
            "Mercury",
            "Ibrahimovic",
            "Prince",
            "Thumberg",
            "Berlusconi"
    };


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

  @Test(expected = BillException.class)
  public void getSaleIf5ProcessorNullTest() {
    revenue.getSaleIf5Processor(null);
  }

  @Test(expected = BillException.class)
  public void freeItemIf10MiceNullTest() {
    revenue.freeItemIf10Mice(null);
  }

  @Test(expected = BillException.class)
  public void offerDiscountIfTotalOverThresholdNullTest() {
    revenue.offerDiscountIfTotalOverThreshold(null);
  }

  @Test(expected = BillException.class)
  public void addFeeNullTest() {
    revenue.addFee(null);
  }

  @Test
  public void getTotalTest() {
    assertEquals(1075.02, revenue.getOrderPrice(itemsList, user), 0.1);
  }

  @Test
  public void getTotalWithProcessorsSaleTest() {
    ArrayList<EItem> processors6List = new ArrayList<>();
    processors6List.add(new EItem("Intel i1", 49.99, itemType.Processor));
    processors6List.add(new EItem("Intel i2", 79.99, itemType.Processor));
    processors6List.add(new EItem("Intel i3", 19.99, itemType.Processor));
    processors6List.add(new EItem("Intel i4", 29.99, itemType.Processor));
    processors6List.add(new EItem("Intel i5", 109.99, itemType.Processor));
    processors6List.add(new EItem("Intel i6", 89.99, itemType.Processor));
    assertEquals(369.95, revenue.getOrderPrice(processors6List, user), 0.1);
  }

  @Test
  public void getTotalWithNoProcessorSaleTest() {
    ArrayList<EItem> processor4List = new ArrayList<>();
    processor4List.add(new EItem("Intel i7", 99.99, itemType.Processor));
    processor4List.add(new EItem("Intel i8", 29.99, itemType.Processor));
    processor4List.add(new EItem("Intel i9", 69.99, itemType.Processor));
    processor4List.add(new EItem("Intel i10", 19.99, itemType.Processor));
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
  public void freeMouseIf10MiceTest() {
      assertEquals(109.66, revenue.getOrderPrice(miceList, user), 0.01);
  }

  //MTSS-10
  @Test
  public void freeMouseIfMoreThan10MiceTest() {
      miceList.add(anotherMouse);
      assertEquals(119.42, revenue.getOrderPrice(miceList, user), 0.1);
  }

  //MTSS-12
  @Test
  public void offerDiscountIfTotalOverThresholdTest() {
      assertEquals(1075.02, revenue.getOrderPrice(itemsList, user), 0.1);
  }

  //MTSS-12
  @Test
  public void doNotOfferDiscountIfTotalBelowThresholdTest() {
      assertEquals(109.66, revenue.getOrderPrice(miceList, user), 0.1);
  }

  //MTSS-14
  @Test
  public void addFeeTest() {
    ArrayList<EItem> belowThresholdList = new ArrayList<EItem>();
    belowThresholdList.add(mouse);
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
  public void giveAwayAgeOlderThan18Test() {
    User user = new User("MargheritaHack", "Margherita", "Hack", 20);
    Order order = new Order(itemsList, user, LocalTime.of(18, 30,0), 200);
    ArrayList<Order> aux = new ArrayList<>();
    aux.add(order);
    assertEquals(0, revenue.giveAway(aux).size());
  }

  @Test
  public void giveAwayOneOrderTest() {
    User user = new User("MargheritaHack", "Margherita", "Hack", 17);
    Order order = new Order(itemsList, user, LocalTime.of(18, 30,0), 200);
    ArrayList<Order> aux = new ArrayList<>();
    aux.add(order);
    assertEquals(1, revenue.giveAway(aux).size());
  }

  @Test
  public void giveAwayMultipleOrdersTest() {
    ArrayList<Order> aux = new ArrayList<>();
    for(int i = 0; i < 16; i++) {
      User user = new User(names[i]+surnames[i], names[i], surnames[i], 17);
      Order order = new Order(itemsList, user, LocalTime.of(18, 30,0), 200);
      aux.add(order);
    }
    assertEquals(10, revenue.giveAway(aux).size());
  }

  @Test
  public void giveAwayMultipleOrdersYoungerAndOlderTest() {
    ArrayList<Order> aux = new ArrayList<>();
    for(int i = 0; i < 5; i++) {
      User user1 = new User(names[i]+surnames[i], names[i], surnames[i], 17);
      Order order1 = new Order(itemsList, user1, LocalTime.of(18, 30,0), 200);
      aux.add(order1);
    }
    for(int i = 0; i < 5; i++) {
      User user2 = new User(names[i]+surnames[i], names[i], surnames[i], 45);
      Order order2 = new Order(itemsList, user2, LocalTime.of(18, 30,0), 200);
      aux.add(order2);
    }
    assertEquals(5, revenue.giveAway(aux).size());
  }
}