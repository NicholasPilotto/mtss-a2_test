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
import java.util.*; //da cambiare
import java.util.concurrent.ThreadLocalRandom;

public class Revenue implements Bill {

  @Override
  public double getOrderPrice(List<EItem> itemsOrdered, User user) throws BillException {
    if(itemsOrdered == null) {
      throw new BillException("La lista degli EItems non può essere nulla.");
    }

    if(itemsOrdered.size() == 0) {
      throw new BillException("La lista degli EItems non può essere vuota.");
    }

    if(itemsOrdered.size() > 30) {
      throw new BillException("Non è possibile eseguire un ordinazione con più di 30 elementi.");
    }

    double orderPrice = 0.0;
    double totalSale = 0.0;
    double totalFees = 0.0;
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
    totalSale += freeItemIf10Mice(itemsOrdered);
    totalSale += offerDiscountIfTotalOverThreshold(itemsOrdered);
    totalFees += addFee(itemsOrdered);
    return orderPrice - totalSale + totalFees;
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

    for (EItem item : list1) {
      if (item.getPrice() <= min1) {
        min1 = item.getPrice();
      }
    }

    for (EItem item : list2) {
      if (item.getPrice() <= min1) {
        min2 = item.getPrice();
      }
    }

    return Math.min(min1, min2);
  }
  //MTSS-10
  double freeItemIf10Mice(List<EItem> itemsOrdered) {
    ArrayList<EItem> mice = new ArrayList<>();
    for (EItem item : itemsOrdered) {
      if (item.getItem().equals(itemType.Mouse)) {
        mice.add(item);
      }
    }

    if (mice.size() > 10) {
      Collections.sort(mice, new Comparator<EItem>() {
        @Override
        public int compare(EItem z1, EItem z2) {
          if (z1.getPrice() > z2.getPrice()) {
            return 1;
          } else if (z1.getPrice() < z2.getPrice()) {
            return -1;
          }
          return 0;
        }
      });
      EItem cheaper = mice.get(0);
      return cheaper.getPrice();
    }

    return 0;
  }

  //MTSS-12
  double offerDiscountIfTotalOverThreshold(List<EItem> itemsOrdered) {
    double total;
    double threshold;
    double discount;

    total = 0.0;
    threshold = 1000.0;
    discount = 0.0;

    for (EItem item : itemsOrdered) {
      total += item.getPrice();
    }
    if (total > threshold) {
      discount = total * 0.1;
    }

    return discount;
  }

  //MTSS-14
  double addFee(List<EItem> itemsOrdered) {
    double total;
    double threshold;
    double fee;

    total = 0.0;
    threshold = 10.0;
    fee = 2.0;

    for (EItem item : itemsOrdered) {
      total += item.getPrice();
    }
    if (total < threshold) {
      return fee;
    }

    return 0.0;
  }

  public List<Order> giveAway(List<Order> orders) throws BillException {
    ArrayList<Order> freeOrders = new ArrayList<>();

    if(orders == null) {
      throw new BillException("La lista degli ordini da regalare non deve essere nulla.");
    }

    if(orders.size() == 0) {
      throw new BillException("La lista degli ordini da regalare non deve essere vuota.");
    }

    ArrayList<Order> orderPool = new ArrayList<>();
    for(Order order: orders) {
      if(order.getUser().getAge() <= 18) {
        if(order.getTime().isAfter(LocalTime.of(17, 59, 59)) && order.getTime().isBefore(LocalTime.of(19, 0, 1))) {
          orderPool.add(order);
        }
      }
    }

    if(orderPool.size() > 0) {
      int max = orderPool.size() > 10 ? 10 : orderPool.size();
      for(int i = 0; i < max + 1; i++) {
        Random randomGenerator = new Random();
          int index = randomGenerator.nextInt(orderPool.size());
          if(orderPool.get(index).getPrice() != 0) {
            orderPool.get(index).setPrice(0);
            freeOrders.add(orderPool.get(i));
          }
      }
    }

    return freeOrders;
  }
}

