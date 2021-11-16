package de.htw.bemydj.networkControl;

import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pDeviceList;
import android.net.wifi.p2p.WifiP2pManager;

import java.util.Collection;

import de.htw.bemydj.ui.networkInfo.NetworkControlActivity;

public class PeerListListener implements WifiP2pManager.PeerListListener{
    NetworkControlActivity activity;

    public PeerListListener(  NetworkControlActivity activity){
        this.activity=activity;
    }
    @Override
    public void onPeersAvailable(WifiP2pDeviceList wifiP2pDeviceList) {
        Collection<WifiP2pDevice> refreshedPeers = wifiP2pDeviceList.getDeviceList();
        if (!refreshedPeers.equals(activity.getPeers())) {
            activity.getPeers().clear();
            activity.getPeers().addAll(wifiP2pDeviceList.getDeviceList());

            String [] deviceNameArray = new String[wifiP2pDeviceList.getDeviceList().size()];
            WifiP2pDevice[] deviceArray = new WifiP2pDevice[wifiP2pDeviceList.getDeviceList().size()];
            int index = 0;
            for(WifiP2pDevice device : wifiP2pDeviceList.getDeviceList()){
                deviceNameArray[index] = device.deviceName;
                deviceArray[index] = device;
                index++;
            }
            //TODO Logging Founded Peers
        }

        if (activity.getPeers().size() == 0) {
            //TODO Logging No Device Found
        }
    }
}
