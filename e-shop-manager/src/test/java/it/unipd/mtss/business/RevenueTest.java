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

  private int freeMouseThreshold;

  private ArrayList<EItem> emptyList;
  private ArrayList<EItem> nullList;
  private ArrayList<EItem> itemsList;
  private ArrayList<EItem> miceList;
  private ArrayList<EItem> belowThresholdList;

  @Before
  public void setUpObject() {
    keyboard = new EItem("Logitech x 300", 45.6, itemType.Keyboard);
    motherboard = new EItem("RoG zy 1080", 1099.99, itemType.Motherboard);
    mouse = new EItem("Asus L3", 9.99, itemType.Mouse);
    processor = new EItem("Intel i5", 49.99, itemType.Processor);
    freeMouseThreshold = 10; //soglia per ricevere gratis il mouse più economico

    emptyList = new ArrayList<EItem>();
    nullList = new ArrayList<EItem>();
    itemsList = new ArrayList<EItem>();
    miceList = new ArrayList<EItem>();
    belowThresholdList = new ArrayList<EItem>();

    nullList.add(keyboard);
    nullList.add(null);
    itemsList.add(keyboard);
    itemsList.add(motherboard);
    itemsList.add(mouse);
    itemsList.add(processor);
    for (int i = 0; i < freeMouseThreshold; i++) {
      miceList.add(mouse);
    }
    belowThresholdList.add(mouse);

    user = new User("MarioRossi", "Mario", "Rossi");

    revenue = new Revenue();
  }

  @Test(expected = IllegalArgumentException.class)
  public void getTotalNullTest() {
    revenue.getOrderPrice(null, user);
  }

  @Test(expected = IllegalArgumentException.class)
  public void getTotalEmptyArrayListTest() {
    revenue.getOrderPrice(emptyList, user);
  }

  @Test(expected = IllegalArgumentException.class)
  public void getTotalNullArrayListTest() {
    revenue.getOrderPrice(nullList, user);
  }

  @Test
  public void getTotalTest() {
    assertEquals(1205.57, revenue.getOrderPrice(itemsList, user), 0.0);
  }

  //MTSS-10
  @Test
  public void freeItemIf10MiceTest() {
      assertEquals(99.90, revenue.getOrderPrice(miceList, user), 0.0);
  }

  //MTSS-10
  @Test
  public void freeItemIfMoreThan10MiceTest() {
      miceList.add(mouse);
      assertEquals(99.90, revenue.getOrderPrice(miceList, user), 0.0);
  }

  //MTSS-10
  @Test
  public void freeItemIfMoreThan10MiceTest() {
      miceList.add(mouse);
      assertEquals(99.90, revenue.getOrderPrice(miceList, user), 0.0);
  }

  //MTSS-12
  @Test
  public void offerDiscountIfTotalOverThresholdTest() {
      assertEquals(1085.01, revenue.getOrderPrice(itemsList, user), 0.0); //alzo il delta a 1cent?
  }

  //MTSS-12
  //è necessario?
  @Test
  public void donotOfferDiscountIfTotalOverThresholdTest() {
      assertEquals(99.90, revenue.getOrderPrice(miceList, user), 0.0);
  }

    //MTSS-14
  @Test
  public void addFeeTest() {
      assertEquals(11.99, revenue.getOrderPrice(belowThresholdList, user), 0.0);
  }

  //MTSS-14
  //è necessario?
  @Test
  public void donotOfferDiscountIfTotalOverThresholdTest() {
      assertEquals(99.90, revenue.getOrderPrice(miceList, user), 0.0);
  }

}