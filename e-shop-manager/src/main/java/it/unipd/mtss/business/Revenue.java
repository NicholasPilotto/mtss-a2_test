////////////////////////////////////////////////////////////////////
// [NICHOLAS] [PILOTTO] [1230237]
// [GIOVANNI] [GARDIN] [2010003]
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.business;

import it.unipd.mtss.business.exception.BillException;
import it.unipd.mtss.model.EItem;
import it.unipd.mtss.model.itemType;
import it.unipd.mtss.model.User;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;

public class Revenue implements Bill {

  @Override
  public double getOrderPrice(List<EItem> itemsOrdered, User user) throws BillException {
    if(itemsOrdered == null || itemsOrdered.size() == 0) {
      throw new IllegalArgumentException("La lista degli EItems non può essere nulla o vuota");
    }
    if(itemsOrdered == null) {
      throw new IllegalArgumentException("La lista degli EItems non può essere nulla.");
    }

    if(itemsOrdered.size() == 0) {
      throw new IllegalArgumentException("La lista degli EItems non può essere vuota.");
    }

    double orderPrice;
    orderPrice = 0.0;
    for (int i = 0; i < itemsOrdered.size(); i++) {
      if (itemsOrdered.get(i) == null) {
        throw new IllegalArgumentException("La lista contiene un oggetto nullo.");
      }
      orderPrice += itemsOrdered.get(i).getPrice();
    }
    return orderPrice - getSaleIf5Processor(itemsOrdered) - freeItemIf10Mice(itemsOrdered) - offerDiscountIfTotalOverThreshold(itemsOrdered) + addFee(itemsOrdered);
  }

  double getSaleIf5Processor(List<EItem> itemsOrdered) {
    ArrayList<EItem> processors = new ArrayList<>();
    for (EItem item : itemsOrdered) {
      if (item.getItem().equals(itemType.Processor)) {
        processors.add(item);
      }
    }

    if (processors.size() > 5) {
      Collections.sort(processors, new Comparator<EItem>() {
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
      EItem cheaper = processors.get(0);
      return cheaper.getPrice() * 0.5;
    }

    return 0;
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

}
