package de.htw.bemydj.djControl;

import java.util.LinkedList;

import de.htw.bemydj.djData.TrackImpl;
import de.htw.bemydj.djData.UserImpl;

public class DJControl implements IDJControl{
    @Override
    public TrackImpl getFirstTrack(LinkedList<TrackImpl> Warteschlange) {
        return Warteschlange.get(0);
    }

    @Override
    public TrackImpl getNextTrack(LinkedList<TrackImpl> Warteschlange) {
        return Warteschlange.get(1);
    }

    @Override
    public UserImpl getUser(TrackImpl Track) {
        return Track.getUser();
    }

    @Override
    public boolean startStreaming(UserImpl User, TrackImpl Track) {
        return false;
    }

    @Override
    public boolean stopStreaming(UserImpl User) {
        return false;
    }

    @Override
    public void addTrack(LinkedList<TrackImpl> Warteschlange, TrackImpl Track) {
        Warteschlange.add(Track);
    }

    @Override
    public void removeTrack(LinkedList<TrackImpl> Warteschlange, TrackImpl Track) {
        Warteschlange.remove(Track);
    }

    @Override
    public boolean syncWarteschlange(LinkedList<TrackImpl> Warteschlange) {
        return false;
    }
}
