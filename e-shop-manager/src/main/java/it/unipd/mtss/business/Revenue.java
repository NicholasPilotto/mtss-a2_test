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

    double orderPrice;
    orderPrice = 0.0;
    for (int i = 0; i < itemsOrdered.size(); i++) {
      if (itemsOrdered.get(i) == null) {
        throw new BillException("La lista contiene un oggetto nullo.");
      }
      orderPrice += itemsOrdered.get(i).getPrice();
    }
    return orderPrice - getSaleIf5Processor(itemsOrdered);
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
}
