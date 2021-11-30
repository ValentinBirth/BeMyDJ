package de.htw.bemydj.ui.homeScreen;



import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import de.htw.bemydj.R;
import de.htw.bemydj.musicControl.ChooseMusicFileListener;

import de.htw.bemydj.musicControl.MusicControlImpl;
import de.htw.bemydj.musicControl.MyLifecycleObserver;

public class HomeFragment extends Fragment {

    private MusicControlImpl mediaPlayerController;
    private MyLifecycleObserver lifecycleObserver;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        Button btmusic = v.findViewById(R.id.musicButton);

        btmusic.setOnClickListener(new ChooseMusicFileListener(this));
        lifecycleObserver = new MyLifecycleObserver(requireActivity().getActivityResultRegistry(), this);
        getLifecycle().addObserver(lifecycleObserver);
        mediaPlayerController = new MusicControlImpl(this);

        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mediaPlayerController.releasePlayer();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mediaPlayerController.releasePlayer();
    }

    public MyLifecycleObserver getLifecycleObserver() {
        return lifecycleObserver;
    }

}