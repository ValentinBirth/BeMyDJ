package de.htw.bemydj.djData;

import androidx.annotation.NonNull;

import java.util.LinkedList;

public class UserImpl implements IUser{
    private String name;
    private String address;
    private LinkedList<ITrack> warteschlange;

    public UserImpl(String deviceName, String deviceAddress) {
        this.name = deviceName;
        this.address = deviceAddress;
        this.warteschlange = new LinkedList<>();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getAdress() {
        return address;
    }

    @Override
    public LinkedList<ITrack> getQueue() {
        return warteschlange;
    }

    @Override
    public void setName(String name) {
        this.name =name;
    }

    @NonNull
    @Override
    public String toString() {
        return "[Name: "+name +" Adress: "+address+"]";
    }
}
