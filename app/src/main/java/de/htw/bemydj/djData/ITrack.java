package de.htw.bemydj.djData;

/**
 * interface for datastructure track
 */
public interface ITrack {

    /** getter if a track is playing
     * @return boolean
     */
    boolean isPlaying();

    /**setter if track is playing
     * @param isplaying boolean
     */
    void setPlaying(boolean isplaying);

    /** getter for user of a track
     * @return user
     */
    UserImpl getUser();

    /**getter for filepath of track
     * @return String of filepath
     */
    String getFilePath();

}
