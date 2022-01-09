package de.htw.bemydj.networkControl;

import android.net.wifi.p2p.WifiP2pInfo;
import android.net.wifi.p2p.WifiP2pManager;
import android.util.Log;

import java.net.InetAddress;

public class MyConnectionInfoListener implements WifiP2pManager.ConnectionInfoListener {
    private static final String TAG = MyConnectListener.class.toString();
    private final NetworkControlImpl networkControlImpl;

    public MyConnectionInfoListener(NetworkControlImpl networkControlImpl) {
        this.networkControlImpl = networkControlImpl;
    }

    @Override
    public void onConnectionInfoAvailable(WifiP2pInfo wifiP2pInfo) {
        //TODO implement InfoListener
        InetAddress groupOwnerAdress = wifiP2pInfo.groupOwnerAddress;
        if (wifiP2pInfo.groupFormed && wifiP2pInfo.isGroupOwner) {
            //networkControlImpl.createServerSocket();
            //networkControlImpl.getServerSocket().start();
            Log.e(TAG,"Device is Host");
        } else if (wifiP2pInfo.groupFormed) {
            Log.e(TAG,"Device is Client");
            //networkControlImpl.createClientSocket(groupOwnerAdress);
            //networkControlImpl.getClientSocket().start();
        }
    }
}