package de.htw.bemydj.djControl;

import java.util.LinkedList;

import de.htw.bemydj.djData.ITrack;
import de.htw.bemydj.djData.IUser;

public interface IDJControl {

    ITrack getFirstTrack(LinkedList<ITrack> Warteschlange);
    ITrack getNextTrack(LinkedList<ITrack> Warteschlange);
    IUser getUser(ITrack Track);
    boolean startStreaming (IUser User, ITrack Track);
    boolean stopStreaming(IUser User);
    void addTrack(LinkedList<ITrack> Warteschlange, ITrack Track);
    void removeTrack(LinkedList<ITrack> Warteschlange, ITrack Track);
    boolean syncWarteschlange (LinkedList<ITrack> Warteschlange);
}
