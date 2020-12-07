////////////////////////////////////////////////////////////////////
// Davide Sut 1201267
////////////////////////////////////////////////////////////////////


////////////////////////////////////////////////////////////////////
// Nicholas Miazzo 1161392
////////////////////////////////////////////////////////////////////

package it.unipd.tos.business;

import it.unipd.tos.business.exception.RestaurantBillException;
import it.unipd.tos.model.MenuItem;
import it.unipd.tos.model.User;

import java.util.List;

public class TakeAwayBillImpl implements TakeAwayBill {
    public double getOrderPrice(List<MenuItem> itemsOrdered, User user)
            throws RestaurantBillException {

        if(itemsOrdered == null) {
            throw new IllegalArgumentException("ItemsOrder is null");
        }
        if(itemsOrdered.size() == 0) {
            throw new IllegalArgumentException("ItemsOrder is empty");
        }

        // Controllo limite items ----- Issue 4
        if(itemsOrdered.size() > 30) {
            throw new RestaurantBillException();
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

        // Calcolo sconto > 5 gelati --- Issue 2
        if(nGelati > 5) {
            result -= cheapestGelato.getPrice() * 0.5D;
        }

        // Applicazione sconto totale > 50 euro ---- Issue 3
        if(result > 50D) {
            result -= result * 0.1D;
        }

        // Commissione ---- Issue 5
        if(result < 10D) {
            result += 0.5D;
        }

        return result;
    }
}