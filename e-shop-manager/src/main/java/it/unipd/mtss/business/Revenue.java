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
    if(itemsOrdered == null) {
      throw new BillException("La lista degli EItems non può essere nulla.");
    }

    if(itemsOrdered.size() == 0) {
      throw new BillException("La lista degli EItems non può essere vuota");
    }

    double orderPrice = 0.0;
    double totalSale = 0.0;
    ArrayList<EItem> processorList = new ArrayList<>();
    ArrayList<EItem> mouseList = new ArrayList<>();
    ArrayList<EItem> keyboardList = new ArrayList<>();

    for (EItem item : itemsOrdered) {
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
    for (EItem item : itemsOrdered) {
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

    for(EItem item : list1) {
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
}
