package de.htw.bemydj.networkControl;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.wifi.p2p.WifiP2pManager;
import android.util.Log;

import androidx.core.app.ActivityCompat;

import de.htw.bemydj.ui.networkControlView.NetworkControlActivity;

public class WifiP2pBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = WifiP2pBroadcastReceiver.class.getName();
    private WifiP2pManager manager;
    private WifiP2pManager.Channel channel;
    private NetworkControlActivity ncActivity;
    private MyPeerListListener myPeerListListener;

    public WifiP2pBroadcastReceiver(WifiP2pManager manager, WifiP2pManager.Channel channel, NetworkControlActivity ncActivity, MyPeerListListener myPeerListListener) {
        super();
        this.manager = manager;
        this.channel = channel;
        this.ncActivity = ncActivity;
        this.myPeerListListener = myPeerListListener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION.equals(action)) {
            int state = intent.getIntExtra(WifiP2pManager.EXTRA_WIFI_STATE, -1);
            if (state == WifiP2pManager.WIFI_P2P_STATE_ENABLED) {
                Log.e(TAG, "WIFI_P2P_STATE_ENABLED");
            } else {
                Log.e(TAG, "WIFI_P2P_STATE_NOT_ENABLED");
            }
        } else if (WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION.equals(action)) {
            if (manager != null) {
                if (ActivityCompat.checkSelfPermission(ncActivity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(ncActivity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
                }
                manager.requestPeers(channel, myPeerListListener);
                Log.e(TAG,"WIFI_P2P_PEERS_CHANGED_ACTION");
            }
        } else if (WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION.equals(action)) {
            //TODO Respond to new connection or disconnections
        }
    }
}
