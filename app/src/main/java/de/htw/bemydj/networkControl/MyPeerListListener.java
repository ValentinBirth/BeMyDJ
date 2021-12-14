package de.htw.bemydj.networkControl;

import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pDeviceList;
import android.net.wifi.p2p.WifiP2pManager;
import android.util.Log;

import de.htw.bemydj.djData.AvailablePeer;

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
            networkControl.getAvailablePeerList().clear();
            networkControl.getPeerList().addAll(wifiP2pDeviceList.getDeviceList());

            for(WifiP2pDevice device : wifiP2pDeviceList.getDeviceList()){
                networkControl.getAvailablePeerList().add( new AvailablePeer(device.deviceName, device.deviceAddress));
            }

            Log.e(TAG, networkControl.getPeerList().toString());
            Log.e(TAG, networkControl.getAvailablePeerList().toString());
            if (networkControl.getPeerList().size() == 0) {
                Log.e(TAG, "No devices found");
            }
        }
    }
}
