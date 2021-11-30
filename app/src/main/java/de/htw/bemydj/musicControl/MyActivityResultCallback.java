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
        test.setText(result.getPath());
        //TODO handle returned Uri
    }
}
