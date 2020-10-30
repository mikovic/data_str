package ru.mikovic.model;

import java.util.ArrayList;
import java.util.Comparator;

public  class Entity {
    private String name;
    private String title;
    private ArrayList<UnluckyVassal> vassals;
    int value = 0;

    public Entity() {
    }

    public Entity(String name, String title) {
        this.name = name;
        this.title = title;
    }
    public void display(){
        System.out.println(getTitle() + " " + getName() + " " + getValue() + " чел." );

    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void add(UnluckyVassal vassal ){
        if (this.vassals == null){
            this.vassals = new ArrayList<>();
        }
        this.vassals.add(vassal);
        this.value++;
    }

    public int getValue() {
        return value;
    }

    public ArrayList<UnluckyVassal> getVassals() {
        return vassals;
    }
}
