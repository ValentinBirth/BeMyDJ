package de.htw.bemydj.networkControl;

import android.net.wifi.p2p.WifiP2pManager;
import android.util.Log;
import android.widget.TextView;

import de.htw.bemydj.R;
import de.htw.bemydj.ui.networkControlView.NetworkControlActivity;

public class MyDiscoverPeersListener implements WifiP2pManager.ActionListener{
    private static final String TAG = MyDiscoverPeersListener.class.toString();
    NetworkControlActivity activity;

    public MyDiscoverPeersListener(NetworkControlActivity activity){
        this.activity=activity;
    }

    @Override
    public void onSuccess() {
        TextView connectionStatus = activity.findViewById(R.id.connectionStatus);
        connectionStatus.setText(R.string.discovery_started);
        Log.e(TAG,"Discovery started");
    }

    @Override
    public void onFailure(int reason) {
        TextView connectionStatus = activity.findViewById(R.id.connectionStatus);
        connectionStatus.setText(R.string.discovery_failed);
        Log.e(TAG,"Discovery failed with Code: "+reason);
    }
}
