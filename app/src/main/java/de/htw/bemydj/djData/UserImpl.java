package de.htw.bemydj.djData;

import java.util.LinkedList;

public class UserImpl implements IUser{
    private String Name;
    private int id;
    private LinkedList<TrackImpl> Warteschlange;

    @Override
    public String getName() {
        return Name;
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public LinkedList<TrackImpl> getQueue() {
        return Warteschlange;
    }

    @Override
    public void setName(String name) {
        Name=name;
    }
}
