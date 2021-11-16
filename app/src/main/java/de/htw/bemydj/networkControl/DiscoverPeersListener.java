package de.htw.bemydj.networkControl;

import android.net.wifi.p2p.WifiP2pManager;
import android.widget.Toast;

import de.htw.bemydj.ui.networkInfo.NetworkControlActivity;

public class DiscoverPeersListener implements WifiP2pManager.ActionListener{
    NetworkControlActivity activity;

    public DiscoverPeersListener(  NetworkControlActivity activity){
        this.activity=activity;
    }

    @Override
    public void onSuccess() {
        Toast.makeText(activity, "Discovery started", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailure(int reason) {
        Toast.makeText(activity, "Discovery failed", Toast.LENGTH_SHORT).show();
    }
}
