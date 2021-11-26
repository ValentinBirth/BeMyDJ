package de.htw.bemydj.ui.mainView;


import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


import java.io.IOException;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import de.htw.bemydj.R;
import de.htw.bemydj.databinding.FragmentHomeBinding;
import de.htw.bemydj.musicControl.ChooseMusicFileListener;
import de.htw.bemydj.musicControl.MediaPlayerFfListener;
import de.htw.bemydj.musicControl.MediaPlayerOnCompletionListener;
import de.htw.bemydj.musicControl.MediaPlayerOnErrorListener;
import de.htw.bemydj.musicControl.MediaPlayerPauseListener;
import de.htw.bemydj.musicControl.MediaPlayerPlayListener;
import de.htw.bemydj.musicControl.MediaPlayerRewindListener;
import de.htw.bemydj.musicControl.MediaPlayerSeekBarChangeListener;
import de.htw.bemydj.musicControl.MyLifecycleObserver;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private Button btmusic;
    private View v;
    private TextView playerPosition,playerDuration;
    private SeekBar seekBar;
    private ImageView btRew,btPlay,btPause,btFor;
    private MediaPlayer mediaPlayer;
    private Handler handler = new Handler();
    private Runnable runnable;
    private Uri musicFileUriTest;

    private MyLifecycleObserver lifecycleObserver;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_home,container,false);
        btmusic = v.findViewById(R.id.musicButton);
        playerPosition = v.findViewById(R.id.playerPosition);
        playerDuration = v.findViewById(R.id.playerDuration);
        seekBar = v.findViewById(R.id.seekBar);
        btRew = v.findViewById(R.id.btnRew);
        btPlay = v.findViewById(R.id.btnPlay);
        btPause = v.findViewById(R.id.btnPause);
        btFor = v.findViewById(R.id.btwFor);

        btmusic.setOnClickListener(new ChooseMusicFileListener(this));
        lifecycleObserver = new MyLifecycleObserver(requireActivity().getActivityResultRegistry(), this);
        getLifecycle().addObserver(lifecycleObserver);

        mediaPlayer =  new MediaPlayer();
        mediaPlayer.setAudioAttributes(new AudioAttributes.Builder()
        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
        .setUsage(AudioAttributes.USAGE_MEDIA)
        .build());
        resetMediaPlayer();
        mediaPlayer.setOnErrorListener(new MediaPlayerOnErrorListener());
        Runnable musicPlayerThread = new Runnable() {

            @Override
            public void run() {
                seekBar.setProgress(mediaPlayer.getCurrentPosition());
                handler.postDelayed(this,500);
            }
        };

        int duration = mediaPlayer.getDuration();
        String sduration = convertFormat(duration);
        playerDuration.setText(sduration);
        btPlay.setOnClickListener(new MediaPlayerPlayListener(this));
        btPause.setOnClickListener(new MediaPlayerPauseListener(this));
        btFor.setOnClickListener(new MediaPlayerFfListener(this));
        btRew.setOnClickListener(new MediaPlayerRewindListener(this));
        seekBar.setOnSeekBarChangeListener(new MediaPlayerSeekBarChangeListener(this));
        //mediaPlayer.setOnCompletionListener(new MediaPlayerOnCompletionListener(this)); causes invalid state error on seekTo method

        return v;
    }

    public String convertFormat(int duration) {
        return  String.format(Locale.GERMANY,"%02d:%02d", TimeUnit.MICROSECONDS.toMinutes(duration), TimeUnit.MICROSECONDS.toSeconds(duration)-TimeUnit.MINUTES.toSeconds(TimeUnit.MICROSECONDS.toMinutes(duration)));
    }

    public boolean resetMediaPlayer(){
        try {
            mediaPlayer.reset();
            mediaPlayer.setDataSource(musicFileUriTest.getPath());
            mediaPlayer.prepareAsync();
            return true;
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        mediaPlayer.release();
        mediaPlayer = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
        mediaPlayer.release();
        mediaPlayer = null;
    }

    public void setMusicFileUriTest(Uri musicFileUriTest) {
        this.musicFileUriTest = musicFileUriTest;
    }

    public MyLifecycleObserver getLifecycleObserver() {
        return lifecycleObserver;
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public SeekBar getSeekBar() {
        return seekBar;
    }

    public Handler getHandler() {
        return handler;
    }

    public Runnable getRunnable() {
        return runnable;
    }
}