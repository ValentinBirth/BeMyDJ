package de.htw.bemydj.djData;

import java.util.LinkedList;

public class UserImpl implements IUser{
    private String Name;
    private int id;
    private LinkedList<ITrack> Warteschlange;

    @Override
    public String getName() {
        return Name;
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public LinkedList<ITrack> getQueue() {
        return Warteschlange;
    }

    @Override
    public void setName(String name) {
        Name=name;
    }
}
