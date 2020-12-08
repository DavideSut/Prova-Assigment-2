////////////////////////////////////////////////////////////////////
// Davide Sut 1201267
////////////////////////////////////////////////////////////////////


package it.unipd.tos.business;

import it.unipd.tos.business.exception.RestaurantBillException;
import it.unipd.tos.model.MenuItem;
import it.unipd.tos.model.User;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.assertEquals;

public class TakeAwayBillTest {

    private TakeAwayBill takeAwayBill;
    private MenuItem bananaSplit;
    private MenuItem coppaNafta;
    private MenuItem biancaneve;
    private MenuItem pinguino;
    private MenuItem chinotto;
    
    private User user1;
    private User user2;
    private User user3;
    private User user4;
    private User user5;
    private User user6;
    private User user7;
    private User user8;
    private User user9;
    private User user10;
    private User user11;

    LinkedList<MenuItem> list = new LinkedList<>();
    User user = new User("user",21);
    LinkedList<User> listUser = new LinkedList<>();

    @Before
    public void setup() {

        bananaSplit = new MenuItem(MenuItem.ItemType.GELATO, "Banana Split", 3D);
        coppaNafta = new MenuItem(MenuItem.ItemType.GELATO, "Coppa Nafta", 3.5D);
        biancaneve = new MenuItem(MenuItem.ItemType.BUDINO, "Biancaneve", 6D);
        pinguino = new MenuItem(MenuItem.ItemType.BUDINO, "Pinguino", 7D);
        chinotto = new MenuItem(MenuItem.ItemType.BEVANDA, "Chinotto", 2.5D);

        user1 = new User("user1",17L);
        user2 = new User("user2",17L);
        user3 = new User("user3",16L);
        user4 = new User("user4",17L);
        user5 = new User("user5",17L);
        user6 = new User("user6",17L);
        user7 = new User("user7",17L);
        user8 = new User("user8",17L);
        user9 = new User("user9",17L);
        user10 = new User("user10",17L);
        

        int nUser = 0;

        takeAwayBill = new TakeAwayBillImpl();
        list = new LinkedList<>();
        user = new User("user",21);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_getOrderPriceNullItems() throws RestaurantBillException {
        takeAwayBill.getOrderPrice(null,user,0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_getOrderPriceZeroItems() throws RestaurantBillException {
        takeAwayBill.getOrderPrice(list,user,0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_getOrderPriceNegativeTimeParam() throws RestaurantBillException {
        list.add(bananaSplit);
        takeAwayBill.getOrderPrice(list,user,-1);
    }

    @Test(expected = RestaurantBillException.class)
    public void test_getOrderPrice40Items() throws RestaurantBillException {
        for (int i = 0; i < 33; i++) {
            list.add(bananaSplit);
        }
        takeAwayBill.getOrderPrice(list,user,0);
    }

    @Test
    public void test_getOrderPriceApplied() throws RestaurantBillException {
        list.add(bananaSplit);
        assertEquals(3.5D, takeAwayBill.getOrderPrice(list,user,0), 0.0001D);
    }

    @Test
    public void test_getOrderPrice50DiscountApplied() throws RestaurantBillException {
        list.add(bananaSplit);
        list.add(bananaSplit);
        list.add(coppaNafta);
        list.add(coppaNafta);
        list.add(coppaNafta);
        list.add(coppaNafta);
        assertEquals(18.5D, takeAwayBill.getOrderPrice(list,user,0), 0.0001D);
    }

    @Test
    public void test_getOrderPrice10DiscountApplied() throws RestaurantBillException {
        list.add(bananaSplit);
        list.add(bananaSplit);
        list.add(bananaSplit);
        list.add(pinguino);
        list.add(pinguino);
        list.add(pinguino);
        list.add(pinguino);
        list.add(pinguino);
        list.add(pinguino);
        assertEquals(45.9D, takeAwayBill.getOrderPrice(list,user,0), 0.0001D);
    }


    @Test
    public void test_getOrderPrice50plus10DiscountApplied() throws RestaurantBillException {
        list.add(bananaSplit);
        list.add(bananaSplit);
        list.add(coppaNafta);
        list.add(coppaNafta);
        list.add(coppaNafta);
        list.add(coppaNafta);
        list.add(pinguino);
        list.add(pinguino);
        list.add(pinguino);
        list.add(pinguino);
        list.add(pinguino);
        assertEquals(48.15D, takeAwayBill.getOrderPrice(list,user,0), 0.0001D);
    }

    @Test
    public void test_getOrderPriceFreeOrdersApplied() throws RestaurantBillException {
        listUser.add(user1);
        listUser.add(user2);
        listUser.add(user3);
        listUser.add(user4);
        listUser.add(user5);
        listUser.add(user6);
        listUser.add(user7);
        listUser.add(user8);
        listUser.add(user9);
        listUser.add(user10);

        list.add(bananaSplit);

        for(User u : listUser){
            assertEquals(0D, takeAwayBill.getOrderPrice(list,u,65000), 0.0001D);
        }
        
    }
}