package de.htw.bemydj.musicControl;


import android.net.Uri;
import android.widget.TextView;
import android.widget.Toast;

import de.htw.bemydj.R;
import de.htw.bemydj.ui.homeScreen.HomeFragment;

public class MyActivityResultCallback implements androidx.activity.result.ActivityResultCallback<Uri> {
    HomeFragment homeFragment;

    public MyActivityResultCallback(HomeFragment homeFragment) {
        this.homeFragment=homeFragment;
    }

    @Override
    public void onActivityResult(Uri result) {
        //TODO Testing Code
        TextView test = homeFragment.requireView().findViewById(R.id.textViewTest);
        test.setText("");
        Toast.makeText(homeFragment.requireContext(), result.getPath(),Toast.LENGTH_SHORT).show();
        homeFragment.setMusicFileUriTest(result);
        homeFragment.resetMediaPlayer();
        //TODO handle returned Uri
    }
}
