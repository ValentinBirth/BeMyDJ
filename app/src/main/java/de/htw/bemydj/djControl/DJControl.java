package de.htw.bemydj.djControl;

import java.util.LinkedList;

import de.htw.bemydj.djData.ITrack;
import de.htw.bemydj.djData.IUser;
import de.htw.bemydj.djData.TrackImpl;
import de.htw.bemydj.djData.UserImpl;

public class DJControl implements IDJControl{
    @Override
    public ITrack getFirstTrack(LinkedList<ITrack> Warteschlange) {
        return Warteschlange.get(0);
    }

    @Override
    public ITrack getNextTrack(LinkedList<ITrack> Warteschlange) {
        return Warteschlange.get(1);
    }

    @Override
    public IUser getUser(ITrack Track) {
        return Track.getUser();
    }

    @Override
    public boolean startStreaming(IUser User, ITrack Track) {
        return false;
        //TODO Implementation missing
    }

    @Override
    public boolean stopStreaming(IUser User) {
        return false;
        //TODO Implementation missing
    }

    @Override
    public void addTrack(LinkedList<ITrack> Warteschlange, ITrack Track) {
        Warteschlange.add(Track);
    }

    @Override
    public void removeTrack(LinkedList<ITrack> Warteschlange, ITrack Track) {
        Warteschlange.remove(Track);
    }

    @Override
    public boolean syncWarteschlange(LinkedList<ITrack> Warteschlange) {
        return false; //TODO Implementation missing
    }
}
