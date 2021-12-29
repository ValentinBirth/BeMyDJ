package de.htw.bemydj.ui.networkControlView.groupPeersRecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import de.htw.bemydj.R;
import de.htw.bemydj.networkControl.NetworkControlImpl;

public class GroupPeersRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolderGroupPeers> {

    private final NetworkControlImpl networkControl;
    private Context mContext;

    public GroupPeersRecyclerViewAdapter(Context mContext, NetworkControlImpl networkControl){
        this.mContext = mContext;
        this.networkControl = networkControl;
    }

    @NonNull
    @Override
    public RecyclerViewHolderGroupPeers onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.item_peer,parent,false);

        RecyclerViewHolderGroupPeers RecyclerViewHolderGroupPeers = new RecyclerViewHolderGroupPeers(v);

        return RecyclerViewHolderGroupPeers;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolderGroupPeers holder, int position) {

        holder.getTv_Name().setText(networkControl.getGroupPeerList().get(position).getName());
        holder.getTv_adress().setText(networkControl.getGroupPeerList().get(position).getAdress());

    }

    @Override
    public int getItemCount() {
        return networkControl.getGroupPeerList().size();
    }

}
