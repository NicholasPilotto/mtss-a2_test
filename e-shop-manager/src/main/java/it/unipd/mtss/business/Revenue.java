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
    if (itemsOrdered == null || itemsOrdered.size() == 0) {
      throw new IllegalArgumentException("La lista degli EItems non può essere nulla o vuota");
    }

    double orderPrice;
    orderPrice = 0.0;
    for (int i = 0; i < itemsOrdered.size(); i++) {
      if (itemsOrdered.get(i) == null) {
        throw new IllegalArgumentException("La lista contiene un oggetto nullo.");
      }
      orderPrice += itemsOrdered.get(i).getPrice();
    }
    return orderPrice - getSaleIf5Processor(itemsOrdered);
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
          if (z1.getPrice() > z2.getPrice())
            return 1;
          if (z1.getPrice() < z2.getPrice())
            return -1;
          return 0;
        }
      });
      EItem cheaper = processors.get(0);
      return cheaper.getPrice() * 0.5;
    }
    return 0;
  }
}
