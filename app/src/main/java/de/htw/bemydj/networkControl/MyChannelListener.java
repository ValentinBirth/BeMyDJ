package de.htw.bemydj.networkControl;

import android.net.wifi.p2p.WifiP2pManager;
import android.util.Log;

public class MyChannelListener implements WifiP2pManager.ChannelListener {
    private static final String TAG = MyChannelListener.class.getName();
    @Override
    public void onChannelDisconnected() {
        Log.e(TAG,"Channel disconnected");
    }
}
