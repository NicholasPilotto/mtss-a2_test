 
////////////////////////////////////////////////////////////////////
// [NICHOLAS] [PILOTTO] [1230237]
// [GIOVANNI] [GARDIN] [2010003]
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class UserTest {

  private User mock;

  @Before
  public void setUpObject() {
    mock = new User("jdoe", "John", "Doe");
  }

  @Test(expected = IllegalArgumentException.class)
  public void usernameUserNullTest() {
    new User(null, "John", "Doe");
  }

  @Test(expected = IllegalArgumentException.class)
  public void usernameUserEmptyTest() {
    new User("", "John", "Doe");
  }

  @Test(expected = IllegalArgumentException.class)
  public void firstnameUserNullTest() {
    new User("jdoe", null, "Doe");
  }

  @Test(expected = IllegalArgumentException.class)
  public void firstnameUserEmptyTest() {
    new User("jdoe", "", "Doe");
  }

  @Test(expected = IllegalArgumentException.class)
  public void lastnameUserNullTest() {
    new User("jdoe", "John", null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void lastnameUserEmptyTest() {
    new User("jdoe", "John", "");
  }

  @Test
  public void userGetUsernameTest() {
    assertEquals("jdoe", mock.getUsername());
  }

  @Test
  public void userGetFirstnameTest() {
    assertEquals("John", mock.getFirstname());
  }

  @Test
  public void userGetLastnameTest() {
    assertEquals("Doe", mock.getLastname());
  }
}