package de.htw.bemydj.ui.networkControlView;

import android.app.Dialog;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import de.htw.bemydj.R;
import de.htw.bemydj.networkControl.NetworkControlImpl;
import de.htw.bemydj.ui.networkControlView.availablePeersRecyclerView.RecyclerViewHolderAvailablePeers;

public class OnClickAvailablePeer implements View.OnClickListener {
    private final NetworkControlImpl networkControl;
    private final RecyclerViewHolderAvailablePeers recyclerViewHolderAvailablePeers;
    private final Dialog connectDialog;

    public OnClickAvailablePeer(Dialog connectDialog, NetworkControlImpl networkControl, RecyclerViewHolderAvailablePeers recyclerViewHolderAvailablePeers) {
        this.connectDialog = connectDialog;
        this.networkControl = networkControl;
        this.recyclerViewHolderAvailablePeers = recyclerViewHolderAvailablePeers;
    }

    @Override
    public void onClick(View v) {
        TextView peerName = connectDialog.findViewById(R.id.dialog_name);
        TextView peerAdress = connectDialog.findViewById(R.id.dialog_adress);
        Button connect_btn = connectDialog.findViewById(R.id.dialog_connect_btn);
        peerName.setText(networkControl.getAvailablePeerList().get(recyclerViewHolderAvailablePeers.getBindingAdapterPosition()).getName());
        peerAdress.setText(networkControl.getAvailablePeerList().get(recyclerViewHolderAvailablePeers.getBindingAdapterPosition()).getAdress());
        connect_btn.setOnClickListener(new OnClickConnectWithPeerListener(networkControl,networkControl.getAvailablePeerList().get(recyclerViewHolderAvailablePeers.getBindingAdapterPosition()).getAdress(),connectDialog));
        connectDialog.show();
    }
}
