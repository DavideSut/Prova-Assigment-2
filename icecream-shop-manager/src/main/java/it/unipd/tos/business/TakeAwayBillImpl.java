////////////////////////////////////////////////////////////////////
// Davide Sut 1201267
////////////////////////////////////////////////////////////////////


package it.unipd.tos.business;

import it.unipd.tos.business.exception.RestaurantBillException;
import it.unipd.tos.model.MenuItem;
import it.unipd.tos.model.User;

import java.util.List;
import java.util.LinkedList;

public class TakeAwayBillImpl implements TakeAwayBill {

    private LinkedList<User> listUserFree = new LinkedList<>();

    public double getOrderPrice(List<MenuItem> itemsOrdered, User user, 
    long time) throws RestaurantBillException {

        if(itemsOrdered == null) {
            throw new IllegalArgumentException("ItemsOrder is null");
        }
        if(itemsOrdered.size() == 0) {
            throw new IllegalArgumentException("ItemsOrder is empty");
        }
        if(time < 0) {
            throw new IllegalArgumentException("Time must be >= 0");
        }

        // Controllo limite items ----- Issue 4
        if(itemsOrdered.size() > 30) {
            throw new RestaurantBillException("To many items");
        }
        //------------------------------------

        double result = 0;
        int nGelati = 0;
        MenuItem cheapestGelato = null;

        for (MenuItem m : itemsOrdered) {
            result += m.getPrice(); // Issue 1: Calcolo del totale

            if(m.getItemType() == MenuItem.ItemType.GELATO) {
                nGelati++;
                if(cheapestGelato == null || 
                cheapestGelato.getPrice() > m.getPrice()) {
                    cheapestGelato = m;
                }
            }
        }

        // Calcolo sconto 50% se il numero di gelati > 5 gelati --- Issue 2
        if(nGelati > 5) {
            result -= cheapestGelato.getPrice() * 0.5D;
        }

        // Applicazione sconto 10% se il totale > 50 euro ---- Issue 3
        if(result > 50D) {
            result = result * 0.9D;
        }

        // Commissione ---- Issue 5
        if(result < 10D) {
            result += 0.5D;
        }

        // Ordini in regalo  --- Issue 6
        if(user.getAge() < 18 && time >= 64800 && time <= 68400){
            if(!(listUserFree.contains(user)) && listUserFree.size() < 10){
                listUserFree.add(user);
                result = 0D;
            }
        }

        return result;
    }
}