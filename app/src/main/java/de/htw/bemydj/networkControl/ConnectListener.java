package de.htw.bemydj.networkControl;

import android.net.wifi.p2p.WifiP2pManager;

import de.htw.bemydj.ui.networkInfo.NetworkControlActivity;

public class ConnectListener implements WifiP2pManager.ActionListener{
    NetworkControlActivity activity;

    public ConnectListener(NetworkControlActivity activity){
        this.activity=activity;
    }
    @Override
    public void onSuccess() {

    }

    @Override
    public void onFailure(int i) {

    }
}
