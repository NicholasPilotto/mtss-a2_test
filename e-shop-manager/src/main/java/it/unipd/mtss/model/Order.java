////////////////////////////////////////////////////////////////////
// [NICHOLAS] [PILOTTO] [1230237]
// [GIOVANNI] [GARDIN] [2010003]
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.model;

import java.time.LocalTime;
import java.util.ArrayList;

public class Order {
  ArrayList<EItem> items;
  User user;
  LocalTime time;
  double price;

  public Order(ArrayList<EItem> items, User user, LocalTime time, double price) {
    if(items == null) {
      throw new IllegalArgumentException("La lista degli EItems non può essere nulla.");
    }
    if(items.size() == 0) {
      throw new IllegalArgumentException("La lista degli EItems non può essere vuota.");
    }
    this.items = items;

    if(user == null) {
      throw new IllegalArgumentException("L'utente non può essere nullo.");
    }
    this.user = user;

    if(time == null) {
      throw new IllegalArgumentException("L'orario non può essere nullo.");
    }
    this.time = time;

    if(price < 0) {
      throw new IllegalArgumentException("Il prezzo non può essere negativo.");
    }
    this.price = price;
  }

  public ArrayList<EItem> getItemsList() {
    return this.items;
  }

  public User getUser() {
    return this.user;
  }

  public LocalTime getTime() {
    return this.time;
  }

  public double getPrice() {
    return this.price;
  }

  public void setPrice(double price) {
    if(price < 0) {
      throw new IllegalArgumentException("Il prezzo non può essere negativo");
    }
    this.price = price;
  }
}