package de.htw.bemydj.djData;

import java.util.LinkedList;

public interface IUser {

    String getName();
    int getID();
    LinkedList<TrackImpl> getWarteschlange();
    void setName(String name);
}
