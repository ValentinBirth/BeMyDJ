package de.htw.bemydj.djData;

import java.io.File;

public class TrackImpl implements ITrack{
    private File file;
    private UserImpl User;
    private boolean isPlaying;

    @Override
    public boolean isPlaying() {
        return isPlaying;
    }

    @Override
    public void setPlaying(boolean isplaying) {
        isPlaying=isplaying;
    }

    @Override
    public UserImpl getUser() {
        return User;
    }

    @Override
    public String getFilePath() {
        return file.getPath();
    }
}
