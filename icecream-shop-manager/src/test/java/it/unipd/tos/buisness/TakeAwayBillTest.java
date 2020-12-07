////////////////////////////////////////////////////////////////////
// Davide Sut 1201267
////////////////////////////////////////////////////////////////////


////////////////////////////////////////////////////////////////////
// Nicholas Miazzo 1161392
////////////////////////////////////////////////////////////////////


package it.unipd.tos.business;

import it.unipd.tos.business.exception.RestaurantBillException;
import it.unipd.tos.model.MenuItem;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.assertEquals;

public class TakeAwayBillTest {

    private TakeAwayBill GelateriaBill;
    private MenuItem bananaSplit;
    private MenuItem coppaNafta;
    private MenuItem biancaneve;
    private MenuItem pinguino;

    LinkedList<MenuItem> list = new LinkedList<>();


    @Before
    public void setup() {

        bananaSplit = new MenuItem(MenuItem.ItemType.GELATO, "Banana Split", 3D);
        coppaNafta = new MenuItem(MenuItem.ItemType.GELATO, "Coppa Nafta", 3.5D);
        biancaneve = new MenuItem(MenuItem.ItemType.BUDINO, "Biancaneve", 6D);
        pinguino = new MenuItem(MenuItem.ItemType.BUDINO, "Pinguino", 7D);

        takeAwayBill = new TakeAwayBillImpl();
        list = new LinkedList<>();
    }

    @Test
    public void test_getOrderPriceFeeApplied() throws TakeAwayBillException {
        list.add(bananaSplit);
        assertEquals(3D, takeAwayBill.getOrderPrice(list), 0.0001D);
    }

    @Test
    public void test_getOrderPrice50DiscountApplied() throws TakeAwayBillException {
        list.add(sandwich);
        list.add(sandwich);
        list.add(sandwich);
        list.add(bigSandwich);
        list.add(bigSandwich);
        list.add(bigSandwich);
        assertEquals(42.5D, takeAwayBill.getOrderPrice(list), 0.0001D);
    }

    @Test
    public void test_getOrderPrice10DiscountApplied() throws TakeAwayBillException {
        for (int i = 0; i < 20; i++) {
            list.add(fries);
        }
        assertEquals(63D, takeAwayBill.getOrderPrice(list), 0.0001D);
    }


    @Test
    public void test_getOrderPrice50plus10DiscountApplied() throws TakeAwayBillException {
        list.add(sandwich);
        list.add(sandwich);
        list.add(drink);
        list.add(fries);
        list.add(bigSandwich);
        list.add(bigSandwich);
        list.add(bigSandwich);
        list.add(bigSandwich);
        assertEquals(48.15D, takeAwayBill.getOrderPrice(list), 0.0001D);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_getOrderPriceNullItems() throws TakeAwayBillException {
        takeAwayBill.getOrderPrice(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_getOrderPrice0Items() throws TakeAwayBillException {
        takeAwayBill.getOrderPrice(list);
    }

    @Test(expected = RestaurantBillException.class)
    public void test_getOrderPrice40Items() throws RestaurantBillException {
        for (int i = 0; i < 40; i++) {
            list.add(bananaSplit);
        }
        takeAwayBill.getOrderPrice(list);
    }
}