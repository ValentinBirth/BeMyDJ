package de.htw.bemydj.ui.networkControlView;

import android.view.View;

public class DisconnectButtonListener implements View.OnClickListener {
    private final NetworkControlActivity networkControlActivity;

    public DisconnectButtonListener(NetworkControlActivity networkControlActivity) {
        this.networkControlActivity = networkControlActivity;
    }

    @Override
    public void onClick(View v) {
        networkControlActivity.getNetworkControlImpl().disconnect();
        networkControlActivity.getNetworkControlImpl().getGroupPeerList().clear();
    }
}
