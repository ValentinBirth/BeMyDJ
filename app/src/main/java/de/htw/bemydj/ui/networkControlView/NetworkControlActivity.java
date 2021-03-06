package de.htw.bemydj.ui.networkControlView;

import android.content.pm.PackageManager;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.Toast;

import de.htw.bemydj.databinding.ActivityNetworkControlBinding;
import de.htw.bemydj.networkControl.NetworkControlImpl;
import de.htw.bemydj.ui.networkControlView.availablePeersRecyclerView.AvailablePeersFragment;
import de.htw.bemydj.ui.networkControlView.groupPeersRecyclerView.GroupFragment;

public class NetworkControlActivity extends AppCompatActivity {

    private NetworkControlImpl networkControlImpl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        networkControlImpl = new NetworkControlImpl(this);

        de.htw.bemydj.databinding.ActivityNetworkControlBinding binding = ActivityNetworkControlBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        sectionsPagerAdapter.addFragment(new NetworkControlFragment(this), "Network Control");
        sectionsPagerAdapter.addFragment(new AvailablePeersFragment(this), "Available Listeners");
        sectionsPagerAdapter.addFragment(new GroupFragment(this), "Group");
        ViewPager viewPager = binding.viewPager;
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabLayout = binding.tabs;
        tabLayout.setupWithViewPager(viewPager);
        //TODO Add Toolbar like in MainActivity to open the Nav Drawer
    }
    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(networkControlImpl.getBroadCastReceiver(), networkControlImpl.getIntentFilter());
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

    /** getter for networkcontrol
     * @return networkcontrol
     */
    public NetworkControlImpl getNetworkControlImpl() {
        return networkControlImpl;
    }
}