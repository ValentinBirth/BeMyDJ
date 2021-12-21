package de.htw.bemydj.ui.networkControlView;

import android.util.Log;
import android.view.View;

import de.htw.bemydj.networkControl.NetworkControlImpl;

public class OnClickConnectWithPeerListener implements View.OnClickListener {
    private static final String TAG = OnClickConnectWithPeerListener.class.toString();
    private final NetworkControlImpl networkControl;
    private final String deviceAddress;

    public OnClickConnectWithPeerListener(NetworkControlImpl networkControl, String deviceAddress) {
        this.networkControl = networkControl;
        this.deviceAddress = deviceAddress;
    }

    @Override
    public void onClick(View v) {
        networkControl.connect(deviceAddress);
        Log.e(TAG,"Started connecting to "+deviceAddress);

    }
}
