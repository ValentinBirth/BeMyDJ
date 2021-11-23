package de.htw.bemydj.djControl;

import java.util.LinkedList;

import de.htw.bemydj.djData.ITrack;
import de.htw.bemydj.djData.IUser;

/**
 *  Interface for the controlls of the app
 */
public interface IDJControl {

    /** getter for the first track in the queue
     * @param queue queue
     * @return first track in queue
     */
    ITrack getFirstTrack(LinkedList<ITrack> queue);

    /** getter for second track in the queue
     * @param queue queue
     * @return second track in queue
     */
    ITrack getNextTrack(LinkedList<ITrack> queue);

    /** getter for user
     * @param Track track of the user
     * @return user of the track
     */
    IUser getUser(ITrack Track);

    /** signals to start streaming
     * @param User user wich has to stream
     * @param Track track to stream
     * @return true if successful, else false
     */
    boolean startStreaming (IUser User, ITrack Track);

    /** signals to stop streaming
     * @param User user wich has to stop streaming
     * @return true if successful, else false
     */
    boolean stopStreaming(IUser User);

    /** add a track to the queue
     * @param queue queue
     * @param Track track to be added
     */
    void addTrack(LinkedList<ITrack> queue, ITrack Track);

    /** remove a track from the queue
     * @param queue queue
     * @param Track track to be removed
     */
    void removeTrack(LinkedList<ITrack> queue, ITrack Track);

    /** synconise the queue between devices
     * @param queue queue
     * @return true if successful, else false
     */
    boolean syncQueue (LinkedList<ITrack> queue);
}
