package de.htw.bemydj.ui.networkInfo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

import de.htw.bemydj.R;

public class NetworkControlFragment extends Fragment {
    View v;
    NetworkControlActivity networkControlActivity;
    public NetworkControlFragment(NetworkControlActivity networkControlActivity) {
        this.networkControlActivity = networkControlActivity;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_network_control,container,false);

        Switch discoverPeersSwitch = v.findViewById(R.id.discoverPeersSwitch);
        discoverPeersSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    networkControlActivity.startPeerDiscovery();
                }else{
                    networkControlActivity.stopPeerDiscovery();
                }
            }
        });
        return v;
    }
}
