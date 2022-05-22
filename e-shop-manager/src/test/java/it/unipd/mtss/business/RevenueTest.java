////////////////////////////////////////////////////////////////////
// [NICHOLAS] [PILOTTO] [1230237]
// [GIOVANNI] [GARDIN] [2010003]
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.business;

import it.unipd.mtss.business.exception.BillException;
import it.unipd.mtss.model.EItem;
import it.unipd.mtss.model.User;

import java.util.List;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

class RevenueTest {
    private EItem keyboard;
    private EItem motherboard;
    private EItem mouse;
    private EItem processor;

    @Before
    public void setUpObject() {
        keyboard = new EItem("Logitech x 300", 45.6, itemType.Keyboard);
        motherboard = new EItem("RoG zy 1080", 1099.99, itemType.Motherboard);
        mouse = new EItem("Asus L3", 9.99, itemType.Mouse);
        processor = new EItem("Intel i5", 49.99, itemType.Processor);
        ArrayList<EItem> emptyList = new ArrayList<EItem>();
        ArrayList<EItem> nullList = new ArrayList<EItem>();
        ArrayList<EItem> itemsList = new ArrayList<EItem>();
        nullList.add(keyboard);
        nullList.add(null);
        itemsList.add(keyboard);
        itemsList.add(motherboard);
        itemsList.add(mouse);
        itemsList.add(processor);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getTotalNullTest() {
        getTotal(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getTotalEmptyArrayListTest() {
        getTotal(emptyList);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getTotalNullArrayListTest() {
        getTotal(nullList);
    }

    @Test
    public void getTotalTest() {
        assertEquals(1205.57, getTotal(itemsList), 0.0);
    }
}