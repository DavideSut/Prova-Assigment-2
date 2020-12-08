////////////////////////////////////////////////////////////////////
// Davide Sut 1201267
////////////////////////////////////////////////////////////////////


package it.unipd.tos.model;

public class User {

    private String username;
    private long age;
    //private boolean free = false;

    public User(String username, long age) {
        if(username == null) {
            throw new IllegalArgumentException("Username cannot be null");
        }
        if(age <= 0) {
            throw new IllegalArgumentException("Age must be > 0");
        }
        this.username = username;
        this.age = age;
        //this.free = free;
    }

    public String getUsername(){
        return username;
    }

    public long getAge(){
        return age;
    }

    /*public boolean getFree(){
        return free;
    }*/
}