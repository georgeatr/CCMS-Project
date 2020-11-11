package com.cosc3506.ccms.data.model;

import java.lang.reflect.Array;

public class Club {
        int ID;
        String name;
        Array upcoming;
        String description;
        ClubManager[] managers;


    public Club(int ID, String name, String description, ClubManager[] managers) {
        this.description = description;
        this.ID = ID;
        this.name = name;
        this.managers = managers;
    }


}
