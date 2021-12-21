package de.htw.bemydj.networkControl;

import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pGroup;
import android.net.wifi.p2p.WifiP2pManager;

import de.htw.bemydj.djData.UserImpl;

public class MyGroupInfoListener implements WifiP2pManager.GroupInfoListener {
    private final NetworkControlImpl networkControl;

    public MyGroupInfoListener(NetworkControlImpl networkControl) {
        this.networkControl = networkControl;
    }

    @Override
    public void onGroupInfoAvailable(WifiP2pGroup wifiP2pGroup) {
        networkControl.getGroupPeerList().clear();
        WifiP2pDevice hostDevice = wifiP2pGroup.getOwner();
        networkControl.getGroupPeerList().add(new UserImpl(hostDevice.deviceName, hostDevice.deviceAddress));
        for(WifiP2pDevice device : wifiP2pGroup.getClientList()){
            networkControl.getGroupPeerList().add( new UserImpl(device.deviceName, device.deviceAddress));
        }
    }
}
