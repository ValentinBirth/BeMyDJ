package de.htw.bemydj.ui.networkControlView;

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

import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import de.htw.bemydj.databinding.ActivityNetworkControlBinding;
import de.htw.bemydj.networkControl.ConnectListener;
import de.htw.bemydj.networkControl.DiscoverPeersListener;
import de.htw.bemydj.networkControl.WifiP2pBoradcastReciever;

public class NetworkControlActivity extends AppCompatActivity {

    private ActivityNetworkControlBinding binding;
    private WifiP2pManager manager;
    private WifiP2pManager.Channel chanel;
    private BroadcastReceiver receiver;
    private IntentFilter intentFilter;

    private List<WifiP2pDevice> peers;
    private List<String> deviceNames;
    private List<WifiP2pDevice> devices;

    private DiscoverPeersListener discoverPeersListener;
    private ConnectListener connectListener;

    private SectionsPagerAdapter sectionsPagerAdapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityNetworkControlBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        sectionsPagerAdapter.addFragment(new NetworkControlFragment(this), "Network Control");
        sectionsPagerAdapter.addFragment(new AvailablePeersFragment(this), "Available Listeners");
        sectionsPagerAdapter.addFragment(new GroupFragment(), "Group");
        viewPager = binding.viewPager;
        viewPager.setAdapter(sectionsPagerAdapter);
        tabLayout = binding.tabs;
        tabLayout.setupWithViewPager(viewPager);

        peers = new ArrayList<>();
        manager = (WifiP2pManager) getSystemService(Context.WIFI_P2P_SERVICE);
        chanel = manager.initialize(this, getMainLooper(), null);
        receiver = new WifiP2pBoradcastReciever(manager, chanel, this);
        discoverPeersListener = new DiscoverPeersListener(this);
        connectListener = new ConnectListener(this);

        intentFilter = new IntentFilter();
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION);
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION);
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);

        deviceNames = new ArrayList<>();
        devices = new ArrayList<>();
        //TODO Peers cant find others
        //TODO Add Toolbar like in MainActivity to open the Nav Drawer
        //TODO all Network related into own class in networkControl
    }
    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(receiver, intentFilter);
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

                Toast.makeText(NetworkControlActivity.this, "Permission Granted", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(NetworkControlActivity.this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void startPeerDiscovery() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(NetworkControlActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            manager.discoverPeers(chanel, discoverPeersListener);
        }
    }

    public void stopPeerDiscovery(){
        //TODO implement stopPeerDiscovery
    }

    public List<WifiP2pDevice> getPeers() {
        return peers;
    }

    public void connect(final WifiP2pDevice device) {
        WifiP2pConfig config = new WifiP2pConfig();
        config.deviceAddress = device.deviceAddress;
        config.wps.setup = WpsInfo.PBC;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(NetworkControlActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            manager.connect(chanel, config, connectListener);
        }
    }

    public void setDeviceNames(List<String> deviceNames) {
        this.deviceNames = deviceNames;
    }

    public void setDevices(List<WifiP2pDevice> devices) {
        this.devices = devices;
    }

    public List<String> getDeviceNames() {
        return deviceNames;
    }

    public List<WifiP2pDevice> getDevices() {
        return devices;
    }
}