package de.htw.bemydj.networkControl;

import android.net.wifi.p2p.WifiP2pManager;
import android.widget.TextView;

import de.htw.bemydj.R;
import de.htw.bemydj.ui.networkControlView.NetworkControlActivity;

public class DiscoverPeersListener implements WifiP2pManager.ActionListener{
    NetworkControlActivity activity;

    public DiscoverPeersListener(  NetworkControlActivity activity){
        this.activity=activity;
    }

    @Override
    public void onSuccess() {
        TextView connectionStatus = activity.findViewById(R.id.connectionStatus);
        connectionStatus.setText(R.string.discovery_started);
    }

    @Override
    public void onFailure(int reason) {
        TextView connectionStatus = activity.findViewById(R.id.connectionStatus);
        connectionStatus.setText(R.string.discovery_failed);
    }
}
