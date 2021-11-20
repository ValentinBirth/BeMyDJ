package de.htw.bemydj.networkControl;

import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pDeviceList;
import android.net.wifi.p2p.WifiP2pManager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import de.htw.bemydj.ui.networkInfo.NetworkControlActivity;

public class PeerListListener implements WifiP2pManager.PeerListListener{
    NetworkControlActivity activity;
    private List<String> deviceNames;
    private List<WifiP2pDevice> devices;

    public PeerListListener(  NetworkControlActivity activity){
        this.activity=activity;
    }
    @Override
    public void onPeersAvailable(WifiP2pDeviceList wifiP2pDeviceList) {
        Collection<WifiP2pDevice> refreshedPeers = wifiP2pDeviceList.getDeviceList();
        if (!refreshedPeers.equals(activity.getPeers())) {
            activity.getPeers().clear();
            activity.getPeers().addAll(wifiP2pDeviceList.getDeviceList());

            deviceNames = activity.getDeviceNames();
            devices = activity.getDevices();

            for(WifiP2pDevice device : wifiP2pDeviceList.getDeviceList()){
                deviceNames.add(device.deviceName);
                devices.add(device);
            }
            // TODO Peers cant find others
            //TODO Logging Founded Peers

            if (activity.getPeers().size() == 0) {
                //TODO Logging No Device Found
            }
        }
    }
}
