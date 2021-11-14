package de.htw.bemydj.ui.networkInfo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.wifi.WpsInfo;
import android.net.wifi.p2p.WifiP2pConfig;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import de.htw.bemydj.R;
import de.htw.bemydj.networkControl.ConnectListener;
import de.htw.bemydj.networkControl.DiscoverPeersListener;
import de.htw.bemydj.networkControl.WifiDirectBoradcastReciever;

public class NetworkInfoActivity extends AppCompatActivity {
    private WifiP2pManager manager;
    private WifiP2pManager.Channel chanel;
    private BroadcastReceiver receiver;
    private IntentFilter intentFilter;
    private List<WifiP2pDevice> peers;
    private DiscoverPeersListener discoverPeersListener;
    private ConnectListener connectListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network_info);
        peers = new ArrayList<WifiP2pDevice>();
        manager = (WifiP2pManager) getSystemService(Context.WIFI_P2P_SERVICE);
        chanel = manager.initialize(this, getMainLooper(), null);
        receiver = new WifiDirectBoradcastReciever(manager, chanel, this);
        discoverPeersListener = new DiscoverPeersListener(this);
        connectListener = new ConnectListener(this);

        intentFilter = new IntentFilter();
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION);
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION);
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(receiver, intentFilter);
        startPeerDiscovery();
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                Toast.makeText(NetworkInfoActivity.this, "Permission Granted", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(NetworkInfoActivity.this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void startPeerDiscovery() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(NetworkInfoActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            manager.discoverPeers(chanel, discoverPeersListener);
        }
    }

    public List<WifiP2pDevice> getPeers() {
        return peers;
    }

    public void connect(final WifiP2pDevice device) {
        WifiP2pConfig config = new WifiP2pConfig();
        config.deviceAddress = device.deviceAddress;
        config.wps.setup = WpsInfo.PBC;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(NetworkInfoActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            manager.connect(chanel, config, connectListener);
        }
    }
}