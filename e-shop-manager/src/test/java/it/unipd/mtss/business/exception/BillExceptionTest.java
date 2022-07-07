////////////////////////////////////////////////////////////////////
// [NICHOLAS] [PILOTTO] [1230237]
// [GIOVANNI] [GARDIN] [2010003]
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.business.exception;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;


public class BillExceptionTest {
  private BillException exception;

  @Before
  public void setupObject() {
    exception = new BillException("Questo è un messaggio di errore");
  }

  @Test
  public void billExceptionErrorMessageTest() {
    assertEquals("Questo è un messaggio di errore", exception.getMessage());
  }
}
