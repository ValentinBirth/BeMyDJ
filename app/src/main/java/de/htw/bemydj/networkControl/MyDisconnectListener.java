package de.htw.bemydj.networkControl;

import android.net.wifi.p2p.WifiP2pGroup;
import android.net.wifi.p2p.WifiP2pManager;
import android.util.Log;

public class MyDisconnectListener implements WifiP2pManager.GroupInfoListener {

    private final WifiP2pManager wifiP2pManager;
    private final WifiP2pManager.Channel channel;
    private static final String TAG = MyDisconnectListener.class.toString();

    public MyDisconnectListener(WifiP2pManager wifiP2pManager, WifiP2pManager.Channel channel) {
        this.wifiP2pManager = wifiP2pManager;
        this.channel = channel;
    }

    @Override
    public void onGroupInfoAvailable(WifiP2pGroup wifiP2pGroup) {
        if (wifiP2pGroup != null && wifiP2pManager != null && channel != null) {
            wifiP2pManager.removeGroup(channel, new WifiP2pManager.ActionListener() {

                @Override
                public void onSuccess() {
                    Log.d(TAG, "disconnect successful");
                }

                @Override
                public void onFailure(int reason) {
                    Log.d(TAG, "disconnect failed with code: " + reason);
                }
            });
        }
    }
}
