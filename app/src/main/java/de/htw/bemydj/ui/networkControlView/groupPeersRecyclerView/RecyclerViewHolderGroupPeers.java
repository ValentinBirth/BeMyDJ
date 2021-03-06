package de.htw.bemydj.ui.networkControlView.groupPeersRecyclerView;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import de.htw.bemydj.R;

public class RecyclerViewHolderGroupPeers extends RecyclerView.ViewHolder {

    private final TextView tv_Name;
    private final TextView tv_adress;
    private final LinearLayout item_Peer;

    public RecyclerViewHolderGroupPeers(@NonNull View itemView) {
        super(itemView);
        item_Peer = itemView.findViewById(R.id.Peer_item);
        tv_Name = itemView.findViewById(R.id.peername);
        tv_adress = itemView.findViewById(R.id.peeradress);
    }

    public TextView getTv_Name() {
        return tv_Name;
    }

    public TextView getTv_adress() {
        return tv_adress;
    }

    public LinearLayout getItem_Peer() {
        return item_Peer;
    }
}
