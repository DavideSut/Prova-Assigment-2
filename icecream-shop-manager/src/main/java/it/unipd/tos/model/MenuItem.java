////////////////////////////////////////////////////////////////////
// Davide Sut 1201267
////////////////////////////////////////////////////////////////////


package it.unipd.tos.model;

public class MenuItem {

    private ItemType itemType;
    private String name;
    private double price;
    //private long time;

    public enum ItemType {
        GELATO,BUDINO,BEVANDA;
    }

    public MenuItem(ItemType itemType, String name, double price) { 
        // time è espresso in secondi dopo la mezzanotte

        if(itemType == null) {
            throw new IllegalArgumentException("ItemType cannot be null");
        }
        if(name == null || name.length() == 0) {
            throw new IllegalArgumentException("Name is not valid");
        }
        if(price <= 0) {
            throw new IllegalArgumentException("Price must be > 0");
        }
        /*if(time < 0) {
            throw new IllegalArgumentException("Time must be >= 0");
        }*/
        this.itemType = itemType;
        this.name = name;
        this.price = price;
        //this.time = time;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public ItemType getItemType() {
        return itemType;
    }

    /*public long getTime() {
        return time;
    }*/

}