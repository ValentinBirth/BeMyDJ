package de.htw.bemydj.ui.networkInfo;

import android.widget.CompoundButton;

public class DiscoverPeersSwitchListener implements CompoundButton.OnCheckedChangeListener {
    private NetworkControlActivity networkControlActivity;

    public DiscoverPeersSwitchListener(NetworkControlActivity networkControlActivity) {
        this.networkControlActivity = networkControlActivity;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked){
            networkControlActivity.startPeerDiscovery();
        }else{
            networkControlActivity.stopPeerDiscovery();
        }
    }
}
