package de.htw.bemydj.ui.networkControlView;

import android.app.Dialog;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import de.htw.bemydj.R;
import de.htw.bemydj.djData.AvailablePeer;

public class OnClickAvailablePeer implements View.OnClickListener {
    private List<AvailablePeer> availablePeerList;
    private RecyclerViewHolderAvailablePeers recyclerViewHolderAvailablePeers;
    private Dialog connectDialog;

    public OnClickAvailablePeer(Dialog connectDialog, List<AvailablePeer> availablePeerList, RecyclerViewHolderAvailablePeers recyclerViewHolderAvailablePeers) {
        this.connectDialog = connectDialog;
        this.availablePeerList = availablePeerList;
        this.recyclerViewHolderAvailablePeers = recyclerViewHolderAvailablePeers;
    }

    @Override
    public void onClick(View v) {
        TextView peerName = (TextView) connectDialog.findViewById(R.id.dialog_name);
        TextView peerAdress = (TextView) connectDialog.findViewById(R.id.dialog_adress);
        Button connect_btn = (Button) connectDialog.findViewById(R.id.dialog_connect_btn);
        connect_btn.setOnClickListener(new OnClickConnectWithPeerListener());
        peerName.setText(availablePeerList.get(recyclerViewHolderAvailablePeers.getBindingAdapterPosition()).getName());
        peerAdress.setText(availablePeerList.get(recyclerViewHolderAvailablePeers.getBindingAdapterPosition()).getAdress());
        connectDialog.show();
    }
}
