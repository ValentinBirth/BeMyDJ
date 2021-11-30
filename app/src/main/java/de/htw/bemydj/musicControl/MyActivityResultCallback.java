package de.htw.bemydj.musicControl;


import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.widget.TextView;

import java.io.FileDescriptor;
import java.io.IOException;

import de.htw.bemydj.R;
import de.htw.bemydj.ui.homeScreen.HomeFragment;

public class MyActivityResultCallback implements androidx.activity.result.ActivityResultCallback<Uri> {
    HomeFragment homeFragment;

    public MyActivityResultCallback(HomeFragment homeFragment) {
        this.homeFragment=homeFragment;
    }

    @Override
    public void onActivityResult(Uri result) {
        TextView test = homeFragment.requireView().findViewById(R.id.textViewTest);
        test.setText(result.getPath());
        try {
            ParcelFileDescriptor pdf = homeFragment.getContext().getContentResolver().openFileDescriptor(result, "r");
            FileDescriptor fd = pdf.getFileDescriptor();
            homeFragment.getMusicPlayerController().getMediaPlayer().reset();
            homeFragment.getMusicPlayerController().getMediaPlayer().setDataSource(fd);
            homeFragment.getMusicPlayerController().getMediaPlayer().prepareAsync();
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
    }
}
