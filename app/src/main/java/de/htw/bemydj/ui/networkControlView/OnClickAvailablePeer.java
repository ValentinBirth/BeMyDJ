package de.htw.bemydj.ui.networkControlView;

import android.app.Dialog;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import de.htw.bemydj.R;
import de.htw.bemydj.networkControl.NetworkControlImpl;

public class OnClickAvailablePeer implements View.OnClickListener {
    private final NetworkControlImpl networkControl;
    private RecyclerViewHolderPeers recyclerViewHolderPeers;
    private Dialog connectDialog;

    public OnClickAvailablePeer(Dialog connectDialog, NetworkControlImpl networkControl, RecyclerViewHolderPeers recyclerViewHolderPeers) {
        this.connectDialog = connectDialog;
        this.networkControl = networkControl;
        this.recyclerViewHolderPeers = recyclerViewHolderPeers;
    }

    @Override
    public void onClick(View v) {
        TextView peerName = connectDialog.findViewById(R.id.dialog_name);
        TextView peerAdress = connectDialog.findViewById(R.id.dialog_adress);
        Button connect_btn = connectDialog.findViewById(R.id.dialog_connect_btn);
        peerName.setText(networkControl.getAvailablePeerList().get(recyclerViewHolderPeers.getBindingAdapterPosition()).getName());
        peerAdress.setText(networkControl.getAvailablePeerList().get(recyclerViewHolderPeers.getBindingAdapterPosition()).getAdress());
        connect_btn.setOnClickListener(new OnClickConnectWithPeerListener(networkControl,networkControl.getAvailablePeerList().get(recyclerViewHolderPeers.getBindingAdapterPosition()).getAdress()));
        connectDialog.show();
    }
}
