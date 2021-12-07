package de.htw.bemydj.networkControl;

import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pDeviceList;
import android.net.wifi.p2p.WifiP2pManager;
import android.util.Log;

public class MyPeerListListener implements WifiP2pManager.PeerListListener{
    private static final String TAG = MyPeerListListener.class.getName();
    private NetworkControlImpl networkControl;

    public MyPeerListListener(NetworkControlImpl networkControl){
        this.networkControl = networkControl;
    }
    @Override
    public void onPeersAvailable(WifiP2pDeviceList wifiP2pDeviceList) {
        if (!wifiP2pDeviceList.getDeviceList().equals(networkControl.getPeerList())) {
            networkControl.getPeerList().clear();
            networkControl.getPeerList().addAll(wifiP2pDeviceList.getDeviceList());

            for(WifiP2pDevice device : wifiP2pDeviceList.getDeviceList()){
                networkControl.getPeerNameList().add(device.deviceName);
            }
            // TODO Peers cant find others
            Log.e(TAG, networkControl.getPeerList().toString());
            if (networkControl.getPeerList().size() == 0) {
                Log.e(TAG, "No devices found");
            }
        }
    }
}
