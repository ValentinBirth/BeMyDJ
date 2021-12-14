package de.htw.bemydj.ui.networkControlView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.net.wifi.WpsInfo;
import android.net.wifi.p2p.WifiP2pConfig;
import android.net.wifi.p2p.WifiP2pDevice;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.Toast;

import de.htw.bemydj.databinding.ActivityNetworkControlBinding;
import de.htw.bemydj.networkControl.NetworkControlImpl;

public class NetworkControlActivity extends AppCompatActivity {

    private ActivityNetworkControlBinding binding;

    private NetworkControlImpl networkControlImpl;

    private SectionsPagerAdapter sectionsPagerAdapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        networkControlImpl = new NetworkControlImpl(this);

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
        //TODO Peers cant find others
        //TODO Add Toolbar like in MainActivity to open the Nav Drawer
    }
    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(networkControlImpl.getBroadCastReceiver(), networkControlImpl.getIntentFilter());
        networkControlImpl.startPeerDiscovery();
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(networkControlImpl.getBroadCastReceiver());
        networkControlImpl.stopPeerDiscovery();
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

    public void connect(final WifiP2pDevice device) {
        WifiP2pConfig config = new WifiP2pConfig();
        config.deviceAddress = device.deviceAddress;
        config.wps.setup = WpsInfo.PBC;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(NetworkControlActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            networkControlImpl.getWifiP2pManager().connect(networkControlImpl.getChannel(), config, networkControlImpl.getConnectListener());
        }
    }

    /** getter for networkcontrol
     * @return networkcontrol
     */
    public NetworkControlImpl getNetworkControlImpl() {
        return networkControlImpl;
    }
}