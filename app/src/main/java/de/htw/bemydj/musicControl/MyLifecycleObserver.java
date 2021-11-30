package de.htw.bemydj.musicControl;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.ActivityResultRegistry;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;

import de.htw.bemydj.ui.homeScreen.HomeFragment;

public class MyLifecycleObserver implements DefaultLifecycleObserver {
    private final ActivityResultRegistry musicFileResultRegistry;
    private ActivityResultLauncher<String> musicFileResultLauncher;
    private final HomeFragment homeFragment;


    public MyLifecycleObserver(@NonNull ActivityResultRegistry musicFileResultRegistry, HomeFragment homeFragment) {
        this.musicFileResultRegistry = musicFileResultRegistry;
        this.homeFragment = homeFragment;
    }

    @Override
    public void onCreate(@NonNull LifecycleOwner owner) {
        musicFileResultLauncher = musicFileResultRegistry.register("key",owner, new ActivityResultContracts.GetContent(), new MyActivityResultCallback(homeFragment));
    }

    public void selectMusicFile(){
        musicFileResultLauncher.launch("audio/*");
    }
}
