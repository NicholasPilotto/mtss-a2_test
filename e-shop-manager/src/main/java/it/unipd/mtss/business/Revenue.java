////////////////////////////////////////////////////////////////////
// [NICHOLAS] [PILOTTO] [1230237]
// [GIOVANNI] [GARDIN] [2010003]
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.business;

import it.unipd.mtss.business.exception.BillException;
import it.unipd.mtss.model.EItem;
import it.unipd.mtss.model.User;

import java.util.List;

public class Revenue implements Bill {

  @Override
  public double getOrderPrice(List<EItem> itemsOrdered, User user) throws BillException {
    if(itemsOrdered == null || itemsOrdered.size() == 0) {
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
    return orderPrice;
  }
}
