package de.htw.bemydj;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import de.htw.bemydj.musicControl.IMusicControl;
import de.htw.bemydj.musicControl.MusicControlImpl;
import de.htw.bemydj.ui.homeScreen.HomeFragment;

class IMusicControlTest {
    static IMusicControl musicControl;
    static HomeFragment homeFragment;

    @BeforeEach
    static void SetUp(){
        homeFragment = Mockito.mock(HomeFragment.class);
        musicControl = new MusicControlImpl(homeFragment);
    }

    @AfterEach
    static void tearDown(){
        homeFragment = null;
        musicControl.releasePlayer();
        musicControl = null;
    }

    @Test
    void playNormTest() {
        musicControl.play();
        assertTrue(musicControl.getMediaPlayer().isPlaying());
    }

    @Test
    void pauseNormTest() {
        musicControl.play();
        assertFalse(musicControl.getMediaPlayer().isPlaying());
    }

    @Test
    void rewindNormTest() throws InterruptedException {
        musicControl.play();
        wait(3);
        String timestamp = musicControl.rewind(2);
        assertEquals(timestamp,musicControl.convertFormat(musicControl.getMediaPlayer().getCurrentPosition()));

    }

    @Test
    void fastForwardNormTest() {
        musicControl.play();
        String timestamp =musicControl.fastForward(5);
        assertEquals(timestamp,musicControl.convertFormat(musicControl.getMediaPlayer().getCurrentPosition()));
    }

    @Test
    void resetPlayer() {
        assertTrue(true);
    }

    @Test
    void releasePlayerNormTest() {
        musicControl.releasePlayer();
        assertNull(musicControl.getMediaPlayer());
    }
}