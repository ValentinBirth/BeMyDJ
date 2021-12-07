package de.htw.bemydj.networkControl;

import android.net.wifi.p2p.WifiP2pManager;

import de.htw.bemydj.ui.networkControlView.NetworkControlActivity;

public class MyConnectListener implements WifiP2pManager.ActionListener{
    NetworkControlActivity activity;

    public MyConnectListener(NetworkControlActivity activity){
        this.activity=activity;
    }
    @Override
    public void onSuccess() {

    }

    @Override
    public void onFailure(int i) {

    }
}
