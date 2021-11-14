package de.htw.bemydj.networkControl;

import android.net.wifi.p2p.WifiP2pInfo;
import android.net.wifi.p2p.WifiP2pManager;

import de.htw.bemydj.ui.networkInfo.NetworkInfoActivity;

public class ConnectListener implements WifiP2pManager.ActionListener{
    NetworkInfoActivity activity;

    public ConnectListener(NetworkInfoActivity activity){
        this.activity=activity;
    }
    @Override
    public void onSuccess() {

    }

    @Override
    public void onFailure(int i) {

    }
}
