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
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Revenue implements Bill {

  @Override
  public double getOrderPrice(List<EItem> itemsOrdered, User user) throws BillException {
    if(itemsOrdered == null) {
      throw new BillException("La lista degli EItems non può essere nulla.");
    }

    if(itemsOrdered.size() == 0) {
      throw new BillException("La lista degli EItems non può essere vuota");
    }

    if(itemsOrdered.size() > 30) {
      throw new BillException("Non è possibile eseguire un ordinazione con più di 30 elementi");
    }

    double orderPrice = 0.0;
    double totalSale = 0.0;
    ArrayList<EItem> processorList = new ArrayList<>();
    ArrayList<EItem> mouseList = new ArrayList<>();
    ArrayList<EItem> keyboardList = new ArrayList<>();

    for (EItem item: itemsOrdered) {
      if (item == null) {
        throw new BillException("La lista contiene un oggetto nullo.");
      }
      switch (item.getItem()) {
        case Processor:
          processorList.add(item);
          break;
        case Keyboard:
          keyboardList.add(item);
          break;
        case Mouse:
          mouseList.add(item);
          break;
      }


      orderPrice += item.getPrice();
    }
    if(keyboardList.size() != 0 && keyboardList.size() == mouseList.size()) {
      totalSale += freeObject(keyboardList, mouseList);
    }
    totalSale += getSaleIf5Processor(itemsOrdered);
    return orderPrice  - totalSale;
  }

  double getSaleIf5Processor(List<EItem> itemsOrdered) {
    double min = Double.MAX_VALUE;
    ArrayList<EItem> processors = new ArrayList<>();
    for (EItem item: itemsOrdered) {
      if (item.getItem().equals(itemType.Processor)) {
        if(item.getPrice() <= min) {
          min = item.getPrice();
        }
        processors.add(item);
      }
    }

    if (processors.size() > 5) {
      return min * 0.5;
    }

    return 0;
  }

  double freeObject(List<EItem> list1, List<EItem> list2) {
    double min1 = Double.MAX_VALUE;
    double min2 = Double.MAX_VALUE;

    for(EItem item: list1) {
      if(item.getPrice() <= min1) {
        min1 = item.getPrice();
      }
    }

    for(EItem item : list2) {
      if(item.getPrice() <= min1) {
        min2 = item.getPrice();
      }
    }

    return Math.min(min1, min2);
  }

  public List<Order> giveAway(List<Order> orders) throws BillException {
    ArrayList<Order> freeOrders = new ArrayList<>();

    if(orders == null) {
      throw new BillException("La lista degli ordini da regalare non deve essere nulla");
    }

    if(orders.size() == 0) {
      throw new BillException("La lista degli ordini da regalare non deve essere vuota");
    }

    ArrayList<Order> orderPool = new ArrayList<>();
    for(Order order: orders) {
      if(order.getUser().getAge() >= 18) {
        if(order.getTime().isAfter(LocalTime.of(18, 0, 0)) && order.getTime().isBefore(LocalTime.of(19, 0, 0))) {
          orderPool.add(order);
        }
      }
    }

    if(orderPool.size() > 0) {
      int max = orderPool.size() > 10 ? 10 : orderPool.size();
      for(int i = 0; i < 11; i++) {

        int randomNum = ThreadLocalRandom.current().nextInt(1,  max + 1);
        if(orderPool.get(i).getPrice() == 0) {
          i--;
        } else {
          orderPool.get(randomNum).setPrice(0);
        }
      }
    }

    return freeOrders;
  }
}