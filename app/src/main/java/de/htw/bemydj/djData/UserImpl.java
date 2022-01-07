package de.htw.bemydj.djData;

import androidx.annotation.NonNull;

public class UserImpl implements IUser{
    private String name;
    private final String address;

    public UserImpl(String deviceName, String deviceAddress) {
        this.name = deviceName;
        this.address = deviceAddress;
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
    public void setName(String name) {
        this.name =name;
    }

    @NonNull
    @Override
    public String toString() {
        return "[Name: "+name +" Adress: "+address+"]";
    }
}
