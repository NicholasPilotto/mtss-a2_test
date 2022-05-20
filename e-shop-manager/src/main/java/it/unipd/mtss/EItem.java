////////////////////////////////////////////////////////////////////
// [NICHOLAS] [PILOTTO] [1230237]
// [GIOVANNI] [GARDIN] [2010003]
////////////////////////////////////////////////////////////////////

package it.unipd.mtss;

enum itemType {
  Processor,
  Motherboard,
  Mouse,
  Keyboard
}

public class EItem {
  private String name;
  private double price;
  private itemType item;

  EItem(String name, double price, itemType item) {
    if(name == null || name.length() == 0) {
      throw new IllegalArgumentException("Il nome inserito non deve essere nullo oppure vuoto");
    }
    this.name = name;

    if(price <= 0) {
      throw new IllegalArgumentException("Il prezzo non deve essere negativo o nullo");
    }
    this.price = price;

    if(item == null) {
      throw new IllegalArgumentException("Il tipo dell'oggetto non deve essere nullo");
    }
    this.item = item;
  }

  String getName() {
    return this.name;
  }

  double getPrice() {
    return this.price;
  }

  itemType getItem() {
    return this.item;
  }
}
