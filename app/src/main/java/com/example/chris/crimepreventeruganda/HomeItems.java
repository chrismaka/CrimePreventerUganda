package com.example.chris.crimepreventeruganda;

/**
 * Created by chris on 9/12/2017.
 */
public class HomeItems {
    private String name;
    private int imageResourceId;

    public static final HomeItems[]homeitem = {
            new HomeItems("Lost and Found Property", R.drawable.lostfound),
            new HomeItems("My Way", R.drawable.myway),
            new HomeItems("Wanted Persons", R.drawable.wanted),
            new HomeItems("Press Releases", R.drawable.pressrelease)
    };

    private HomeItems(String name, int imageResourceId){
        this.name = name;
        this.imageResourceId=imageResourceId;
    }
    public String getName(){
        return name;

    }
    public int getImageResourceId(){
        return imageResourceId;
    }
}
