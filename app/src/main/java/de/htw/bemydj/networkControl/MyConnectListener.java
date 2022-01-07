package de.htw.bemydj.networkControl;

import android.net.wifi.p2p.WifiP2pManager;
import android.util.Log;

public class MyConnectListener implements WifiP2pManager.ActionListener{
    private static final String TAG = MyConnectListener.class.toString();

    @Override
    public void onSuccess() {
        Log.e(TAG,"Connection successful");
    }

    @Override
    public void onFailure(int i) {
        Log.e(TAG,"Connection failed: "+i);
    }
}
