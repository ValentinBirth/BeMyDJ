package de.htw.bemydj.networkControl;

import android.net.wifi.p2p.WifiP2pInfo;
import android.net.wifi.p2p.WifiP2pManager;
import android.util.Log;

public class MyConnectionInfoListener implements WifiP2pManager.ConnectionInfoListener {
    private static final String TAG = MyConnectListener.class.toString();
    @Override
    public void onConnectionInfoAvailable(WifiP2pInfo wifiP2pInfo) {
        //TODO implement InfoListener
        if (wifiP2pInfo.groupFormed && wifiP2pInfo.isGroupOwner) {
            Log.e(TAG,"Device is Host");
        } else if (wifiP2pInfo.groupFormed) {
            Log.e(TAG,"Device is Client");
        }
    }
}
