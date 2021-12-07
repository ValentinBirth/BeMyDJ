package de.htw.bemydj.ui.networkControlView;

import android.util.Log;
import android.widget.CompoundButton;
import android.widget.TextView;

import de.htw.bemydj.R;

public class DiscoverPeersSwitchListener implements CompoundButton.OnCheckedChangeListener {
    private static final String TAG = DiscoverPeersSwitchListener.class.toString();
    private NetworkControlActivity networkControlActivity;
    private TextView connectionStatus;

    public DiscoverPeersSwitchListener(NetworkControlActivity networkControlActivity) {
        this.networkControlActivity = networkControlActivity;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        connectionStatus = networkControlActivity.findViewById(R.id.connectionStatus);
        if(isChecked){
            connectionStatus.setText(R.string.discovery_initiated);
            Log.e(TAG,"Discovery initiated");
            networkControlActivity.getNetworkControlImpl().startPeerDiscovery();
        }else{
            connectionStatus.setText(R.string.discovery_stopped);
            Log.e(TAG,"Discovery stopped");
        }
    }
}
