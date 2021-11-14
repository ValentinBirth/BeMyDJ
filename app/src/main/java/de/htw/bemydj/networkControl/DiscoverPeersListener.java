package de.htw.bemydj.networkControl;

import android.net.wifi.p2p.WifiP2pManager;
import android.widget.Toast;

import de.htw.bemydj.ui.networkInfo.NetworkInfoActivity;

public class DiscoverPeersListener implements WifiP2pManager.ActionListener{
    NetworkInfoActivity activity;

    public DiscoverPeersListener(NetworkInfoActivity activity){
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
