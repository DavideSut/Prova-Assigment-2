////////////////////////////////////////////////////////////////////
// Davide Sut 1201267
////////////////////////////////////////////////////////////////////


package it.unipd.tos.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MenuItemTest {

    private MenuItem item;

    @Before
    public void setup() {
        item = new MenuItem(MenuItem.ItemType.BEVANDA, "Chinotto", 3D);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_constructorNullItemTypeParam() {
        new MenuItem(null, "Name", 8D);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_constructorNullNameParam() {
        new MenuItem(MenuItem.ItemType.BEVANDA, null, 3D);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_constructorZeroLengthNameParam() {
        new MenuItem(MenuItem.ItemType.BEVANDA, "", 3D);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_constructorNegativePriceParam() {
        new MenuItem(MenuItem.ItemType.BEVANDA, "Fanta", -3D);
    }

    /*@Test(expected = IllegalArgumentException.class)
    public void test_constructorNegativeTimeParam() {
        new MenuItem(MenuItem.ItemType.BEVANDA, "Fanta", 3D);
    }*/

    @Test
    public void testNameGetter() {
        assertEquals("Chinotto", item.getName());
    }

    @Test
    public void testPriceGetter() {
        assertEquals(3D, item.getPrice(), 0.0001D);
    }

    @Test
    public void testTypeGetter() {
        assertEquals(MenuItem.ItemType.BEVANDA, item.getItemType());
    }

    /*@Test
    public void testTimeGetter() {
        assertEquals(3, item.getTime());
    }*/
}