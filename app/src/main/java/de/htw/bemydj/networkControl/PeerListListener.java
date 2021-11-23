package de.htw.bemydj.networkControl;

import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pDeviceList;
import android.net.wifi.p2p.WifiP2pManager;

import java.util.List;

import de.htw.bemydj.ui.networkControlView.NetworkControlActivity;

public class PeerListListener implements WifiP2pManager.PeerListListener{
    NetworkControlActivity activity;

    public PeerListListener(NetworkControlActivity activity){
        this.activity=activity;
    }
    @Override
    public void onPeersAvailable(WifiP2pDeviceList wifiP2pDeviceList) {
        if (!wifiP2pDeviceList.getDeviceList().equals(activity.getPeers())) {
            activity.getPeers().clear();
            activity.getPeers().addAll(wifiP2pDeviceList.getDeviceList());

            List<String> deviceNames = activity.getDeviceNames();
            List<WifiP2pDevice> devices = activity.getDevices();

            for(WifiP2pDevice device : wifiP2pDeviceList.getDeviceList()){
                deviceNames.add(device.deviceName);
                devices.add(device);
            }
            activity.setDevices(devices);
            activity.setDeviceNames(deviceNames);
            // TODO Peers cant find others
            //TODO Logging Founded Peers

            if (activity.getPeers().size() == 0) {
                //TODO Logging No Device Found
            }
        }
    }
}
