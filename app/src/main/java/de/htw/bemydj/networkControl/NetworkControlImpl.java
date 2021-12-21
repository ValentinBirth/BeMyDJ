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
import android.net.wifi.p2p.WifiP2pManager;

import androidx.core.app.ActivityCompat;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import de.htw.bemydj.djData.UserImpl;
import de.htw.bemydj.ui.networkControlView.NetworkControlActivity;
import de.htw.bemydj.ui.networkControlView.AvailablePeersRecyclerViewAdapter;

public class NetworkControlImpl implements INetworkControl {
    private WifiP2pManager wifiP2pManager;
    private WifiP2pManager.Channel channel;
    private IntentFilter intentFilter;
    private BroadcastReceiver broadcastReceiver;
    private MyDiscoverPeersListener myDiscoverPeersListener;
    private MyStopDiscoverPeersListener myStopDiscoverPeersListener;
    private MyConnectListener myConnectListener;
    private List<WifiP2pDevice> peers;
    private NetworkControlActivity ncActivity;
    private MyChannelListener myChannelListener;
    private MyPeerListListener myPeerListListener;
    private List<UserImpl> availablePeerList;
    private List<UserImpl> groupPeerList;
    private MyGroupInfoListener myGroupInfoListener;

    public NetworkControlImpl(NetworkControlActivity ncActivity) {
        this.peers = new ArrayList<>();
        this.availablePeerList = new ArrayList<>();
        this.groupPeerList = new ArrayList<>();
        this.ncActivity = ncActivity;

        this.myDiscoverPeersListener = new MyDiscoverPeersListener(ncActivity);
        this.myStopDiscoverPeersListener = new MyStopDiscoverPeersListener(ncActivity);
        this.myConnectListener = new MyConnectListener(ncActivity);
        this.myChannelListener = new MyChannelListener();
        this.myPeerListListener = new MyPeerListListener(this);
        this.myGroupInfoListener = new MyGroupInfoListener(this);

        this.wifiP2pManager = (WifiP2pManager) ncActivity.getSystemService(Context.WIFI_P2P_SERVICE);
        this.channel = wifiP2pManager.initialize(ncActivity, getMainLooper(), null);
        this.intentFilter = new IntentFilter();

        intentFilter.addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION);
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION);
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);

        this.broadcastReceiver = new WifiP2pBroadcastReceiver(wifiP2pManager, channel, ncActivity, myPeerListListener, myGroupInfoListener);
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
    public WifiP2pManager.Channel getChannel() {
        return channel;
    }

    @Override
    public WifiP2pManager getWifiP2pManager() {
        return wifiP2pManager;
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
    public MyDiscoverPeersListener getDiscoverPeersListener() {
        return myDiscoverPeersListener;
    }

    @Override
    public MyConnectListener getConnectListener() {
        return myConnectListener;
    }

    @Override
    public MyPeerListListener getPeerListListener() {
        return myPeerListListener;
    }

    @Override
    public MyChannelListener getChannelListener() {
        return myChannelListener;
    }

    @Override
    public MyGroupInfoListener getGroupInfoListener() {
        return myGroupInfoListener;
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
    public Socket getClientSocket() {
        return null;
    }

    @Override
    public Socket getServerSocket() {
        return null;
    }
}
