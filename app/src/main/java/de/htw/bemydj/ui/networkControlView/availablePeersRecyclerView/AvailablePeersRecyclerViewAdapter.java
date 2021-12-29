package de.htw.bemydj.ui.networkControlView.availablePeersRecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import de.htw.bemydj.R;
import de.htw.bemydj.networkControl.NetworkControlImpl;
import de.htw.bemydj.ui.networkControlView.OnClickAvailablePeer;

public class AvailablePeersRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolderAvailablePeers> {

    private final NetworkControlImpl networkControl;
    private Context mContext;
    private Dialog connectDialog;

    public AvailablePeersRecyclerViewAdapter(Context mContext, NetworkControlImpl networkControl){
        this.mContext = mContext;
        this.networkControl = networkControl;
    }

    @NonNull
    @Override
    public RecyclerViewHolderAvailablePeers onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.item_peer,parent,false);

        RecyclerViewHolderAvailablePeers recyclerViewHolderAvailablePeers = new RecyclerViewHolderAvailablePeers(v);

        connectDialog = new Dialog(mContext);
        connectDialog.setContentView(R.layout.dialog_connect);

        recyclerViewHolderAvailablePeers.getItem_Peer().setOnClickListener(new OnClickAvailablePeer(connectDialog, networkControl, recyclerViewHolderAvailablePeers));

        return recyclerViewHolderAvailablePeers;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolderAvailablePeers holder, int position) {

        holder.getTv_Name().setText(networkControl.getAvailablePeerList().get(position).getName());
        holder.getTv_adress().setText(networkControl.getAvailablePeerList().get(position).getAdress());

    }

    @Override
    public int getItemCount() {
        return networkControl.getAvailablePeerList().size();
    }

}
