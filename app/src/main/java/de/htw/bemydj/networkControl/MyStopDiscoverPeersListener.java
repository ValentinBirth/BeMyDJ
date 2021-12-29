package de.htw.bemydj.networkControl;

import android.net.wifi.p2p.WifiP2pManager;
import android.util.Log;
import android.widget.TextView;

import de.htw.bemydj.R;
import de.htw.bemydj.ui.networkControlView.groupPeersRecyclerView.NetworkControlActivity;

public class MyStopDiscoverPeersListener implements WifiP2pManager.ActionListener {
    private static final String TAG = MyDiscoverPeersListener.class.toString();
    NetworkControlActivity activity;

    public MyStopDiscoverPeersListener(NetworkControlActivity activity){
        this.activity=activity;
    }

    @Override
    public void onSuccess() {
        TextView connectionStatus = activity.findViewById(R.id.connectionStatus);
        if(connectionStatus!=null) {
            connectionStatus.setText(R.string.discovery_stopped);
        }
        Log.e(TAG,"Discovery stopped");
    }

    @Override
    public void onFailure(int reason) {
        TextView connectionStatus = activity.findViewById(R.id.connectionStatus);
        if (connectionStatus!=null) {
            connectionStatus.setText(R.string.discovery_failed);
        }
        Log.e(TAG,"Discovery stopped failed with Code: "+reason);
    }
}
