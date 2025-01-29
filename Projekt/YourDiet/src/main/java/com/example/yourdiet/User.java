package com.example.yourdiet;

public class User {
    private String name;
    private String email;
    private int kcal;
    private int id;

    private static User currentUser;

    User(String name, String email){
        this.name = name;
        this.email = email;
    }

    public String getName(){
        return name;
    }

    public String getEmail(){
        return email;
    }
    void setKcal(){
        kcal = 2000;
    }

    static void setCurrentUser(User user){
        currentUser = user;
    }
    static User getCurrentUser() {
        return currentUser;
    }
}
