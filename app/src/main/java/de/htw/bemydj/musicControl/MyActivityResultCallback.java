package de.htw.bemydj.musicControl;


import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import android.widget.TextView;

import java.io.FileDescriptor;
import java.io.IOException;
import java.util.Objects;

import de.htw.bemydj.R;
import de.htw.bemydj.ui.homeScreen.HomeFragment;

public class MyActivityResultCallback implements androidx.activity.result.ActivityResultCallback<Uri> {
    private static final String TAG = MyActivityResultCallback.class.getName();
    HomeFragment homeFragment;

    public MyActivityResultCallback(HomeFragment homeFragment) {
        this.homeFragment=homeFragment;
    }

    @Override
    public void onActivityResult(Uri result) {
        TextView test = homeFragment.requireView().findViewById(R.id.textViewTest);
        if(result == null){
            Log.e(TAG,"Data is null");
        }else {
            test.setText(result.getPath());
            try {
                ParcelFileDescriptor pdf = homeFragment.requireContext().getContentResolver().openFileDescriptor(result, "r");
                FileDescriptor fd = pdf.getFileDescriptor();
                homeFragment.getMusicPlayerController().resetPlayer(fd);
            } catch (IOException | NullPointerException e) {
                Log.e(TAG, String.valueOf(e));
            }
        }
    }
}
