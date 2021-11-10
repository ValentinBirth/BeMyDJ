package de.htw.bemydj.djControl;

import java.util.LinkedList;

import de.htw.bemydj.djData.TrackImpl;
import de.htw.bemydj.djData.UserImpl;

public interface IDJControl {

    TrackImpl getFirstTrack(LinkedList<TrackImpl> Warteschlange);
    TrackImpl getNextTrack(LinkedList<TrackImpl> Warteschlange);
    UserImpl getUser(TrackImpl Track);
    boolean startStreaming (UserImpl User, TrackImpl Track);
    boolean stopStreaming(UserImpl User);
    void addTrack(LinkedList<TrackImpl> Warteschlange, TrackImpl Track);
    void removeTrack(LinkedList<TrackImpl> Warteschlange, TrackImpl Track);
    boolean syncWarteschlange (LinkedList<TrackImpl> Warteschlange);
}
