package de.htw.bemydj;

import static org.junit.jupiter.api.Assertions.*;


import android.media.MediaPlayer;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import de.htw.bemydj.musicControl.IMusicControl;
import de.htw.bemydj.musicControl.MusicControlImpl;

public class IMusicControlTest {
    private  static IMusicControl musicControl;
    private  static MediaPlayer mediaPlayer;

    @BeforeEach
    static void SetUp(){
        musicControl = new MusicControlImpl(mediaPlayer);
        mediaPlayer = Mockito.mock(MediaPlayer.class);
    }

    @AfterEach
    static void tearDown(){
        musicControl = null;
        mediaPlayer = null;
    }


    /**
     * prüft ob das Zurückspulen unter korrekten Vorraussetzungen klappt
     */
    @Test
    void rewindNormTest() {
        Mockito.when(mediaPlayer.isPlaying()).thenReturn(true);
        Mockito.when(mediaPlayer.getCurrentPosition()).thenReturn(3);
        String timestamp = musicControl.rewind(2);
        assertEquals(timestamp,"00:01");

    }

    /**
     * prüft das wenn der Media Player nichts abspielt man auch nicht zurückspulen kann
     */
    @Test
    void rewindErrNotPlayingTest() {
        Mockito.when(mediaPlayer.isPlaying()).thenReturn(false);
        String timestamp = musicControl.rewind(2);
        assertEquals(timestamp,"");

    }

    /**
     * prüft das man nicht länger Zurückspulen kann, wenn das Lied am Anfang ist
     */
    @Test
    void rewindErrTimeTest() {
        Mockito.when(mediaPlayer.isPlaying()).thenReturn(true);
        Mockito.when(mediaPlayer.getCurrentPosition()).thenReturn(1);
        String timestamp = musicControl.rewind(2);
        assertEquals(timestamp,"");

    }

    /**
     * prüft ob das Vorspulen unter korrekten Vorraussetzungen klappt
     */
    @Test
    void fastForwardNormTest() {
        Mockito.when(mediaPlayer.isPlaying()).thenReturn(true);
        Mockito.when(mediaPlayer.getCurrentPosition()).thenReturn(0);
        Mockito.when(mediaPlayer.getDuration()).thenReturn(10);
        String timestamp = musicControl.fastForward(2);
        assertEquals(timestamp,"00:02");
    }

    /**
     * prüft das wenn der Media Player nichts abspielt man auch nicht Vorspulen kann
     */
    @Test
    void fastForwardErrNotPlayingTest() {
        Mockito.when(mediaPlayer.isPlaying()).thenReturn(false);
        String timestamp = musicControl.fastForward(2);
        assertEquals(timestamp,"");
    }

    /**
     * prüft das man nicht Vorspulen kann wenn das Lied schon am Ende ist
     */
    @Test
    void fastForwardErrTimeTest() {
        Mockito.when(mediaPlayer.isPlaying()).thenReturn(true);
        Mockito.when(mediaPlayer.getCurrentPosition()).thenReturn(10);
        Mockito.when(mediaPlayer.getDuration()).thenReturn(10);
        String timestamp = musicControl.fastForward(2);
        assertEquals(timestamp,"");
    }
}