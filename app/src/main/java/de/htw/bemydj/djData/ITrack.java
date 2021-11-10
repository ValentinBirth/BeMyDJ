package de.htw.bemydj.djData;

public interface ITrack {

    boolean isPlaying();
    void setPlaying(boolean isplaying);
    UserImpl getUser();
    String getFilePath();

}
