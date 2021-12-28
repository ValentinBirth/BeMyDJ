package de.htw.bemydj.ui.networkControlView;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;

import de.htw.bemydj.R;

public class NetworkControlFragment extends Fragment {
    private View v;
    private NetworkControlActivity networkControlActivity;
    private TextView textView;
    public NetworkControlFragment(NetworkControlActivity networkControlActivity) {
        this.networkControlActivity = networkControlActivity;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_network_control,container,false);
        textView = v.findViewById(R.id.userInfoStatus);

        String userInfo = "Device Name: "+Settings.Global.getString(networkControlActivity.getContentResolver(), "device_name");
        textView.setText(userInfo);

        Switch discoverPeersSwitch = v.findViewById(R.id.discoverPeersSwitch);
        discoverPeersSwitch.setOnCheckedChangeListener(new DiscoverPeersSwitchListener(networkControlActivity));
        return v;
    }
}
