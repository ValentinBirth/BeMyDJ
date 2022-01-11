package de.htw.bemydj.networkControl;

import static android.os.Looper.getMainLooper;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.wifi.WpsInfo;
import android.net.wifi.p2p.WifiP2pConfig;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pGroup;
import android.net.wifi.p2p.WifiP2pManager;
import android.util.Log;

import androidx.core.app.ActivityCompat;

import java.io.FileDescriptor;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

import de.htw.bemydj.djData.UserImpl;
import de.htw.bemydj.ui.networkControlView.NetworkControlActivity;

public class NetworkControlImpl implements INetworkControl {
    private final WifiP2pManager wifiP2pManager;
    private final WifiP2pManager.Channel channel;
    private final IntentFilter intentFilter;
    private final BroadcastReceiver broadcastReceiver;
    private final MyDiscoverPeersListener myDiscoverPeersListener;
    private final MyStopDiscoverPeersListener myStopDiscoverPeersListener;
    private final MyConnectListener myConnectListener;
    private final List<WifiP2pDevice> peers;
    private final NetworkControlActivity ncActivity;
    private final MyChannelListener myChannelListener;
    private final List<UserImpl> availablePeerList;
    private final List<UserImpl> groupPeerList;
    private StreamingClient clientSocket;
    private StreamingServer serverSocket;
    private WifiP2pManager.GroupInfoListener myDisconnectListener;

    public NetworkControlImpl(NetworkControlActivity ncActivity) {
        this.peers = new ArrayList<>();
        this.availablePeerList = new ArrayList<>();
        this.groupPeerList = new ArrayList<>();
        this.ncActivity = ncActivity;

        this.myDiscoverPeersListener = new MyDiscoverPeersListener(ncActivity);
        this.myStopDiscoverPeersListener = new MyStopDiscoverPeersListener(ncActivity);
        this.myConnectListener = new MyConnectListener();
        this.myChannelListener = new MyChannelListener();
        MyPeerListListener myPeerListListener = new MyPeerListListener(this);
        MyGroupInfoListener myGroupInfoListener = new MyGroupInfoListener(this);

        this.wifiP2pManager = (WifiP2pManager) ncActivity.getSystemService(Context.WIFI_P2P_SERVICE);
        this.channel = wifiP2pManager.initialize(ncActivity, getMainLooper(), null);
        this.intentFilter = new IntentFilter();

        intentFilter.addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION);
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION);
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);

        this.broadcastReceiver = new WifiP2pBroadcastReceiver(wifiP2pManager, channel, ncActivity, myPeerListListener, myGroupInfoListener);
        this.myDisconnectListener = new MyDisconnectListener(wifiP2pManager,channel);
    }

    @Override
    public void startPeerDiscovery() {
        if (ActivityCompat.checkSelfPermission(ncActivity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(ncActivity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
        wifiP2pManager.discoverPeers(channel, myDiscoverPeersListener);
    }

    @Override
    public void stopPeerDiscovery() {
        if (ActivityCompat.checkSelfPermission(ncActivity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(ncActivity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
        wifiP2pManager.stopPeerDiscovery(channel,myStopDiscoverPeersListener);
    }

    @Override
    public void connect(String deviceAddress) {
        WifiP2pConfig config = new WifiP2pConfig();
        config.deviceAddress = deviceAddress;
        config.wps.setup = WpsInfo.PBC;
        config.groupOwnerIntent = 15;
        if (ActivityCompat.checkSelfPermission(ncActivity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(ncActivity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
        wifiP2pManager.connect(channel, config, myConnectListener);
    }

    @Override
    public void disconnect() {
        if (wifiP2pManager != null && channel != null) {
            if (ActivityCompat.checkSelfPermission(ncActivity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(ncActivity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }
            wifiP2pManager.requestGroupInfo(channel, myDisconnectListener);
        }
    }

    @Override
    public IntentFilter getIntentFilter() {
        return intentFilter;
    }

    @Override
    public BroadcastReceiver getBroadCastReceiver() {
        return broadcastReceiver;
    }

    @Override
    public List<WifiP2pDevice> getPeerList() {
        return peers;
    }

    @Override
    public List<UserImpl> getAvailablePeerList() {
        return availablePeerList;
    }

    @Override
    public List<UserImpl> getGroupPeerList() {
        return groupPeerList;
    }

    @Override
    public StreamingClient getClientSocket() {
        return clientSocket;
    }

    @Override
    public StreamingServer getServerSocket() {
        return serverSocket;
    }

    @Override
    public void createServerSocket(FileDescriptor fileDescriptor) {
        serverSocket = new StreamingServer(fileDescriptor);
    }

    @Override
    public void createClientSocket(InetAddress groupOwnerAdress) {
        clientSocket = new StreamingClient(groupOwnerAdress);
    }
}
