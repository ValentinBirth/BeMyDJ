package de.htw.bemydj.ui.networkControlView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import de.htw.bemydj.R;
import de.htw.bemydj.djData.AvailablePeer;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolderAvailablePeers> {

    Context mContext;
    List<AvailablePeer> availablePeerList;

    public RecyclerViewAdapter(Context mContext, List<AvailablePeer> availablePeerList){
        this.mContext=mContext;
        this.availablePeerList=availablePeerList;
    }

    @NonNull
    @Override
    public RecyclerViewHolderAvailablePeers onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.item_availablepeer,parent,false);
        RecyclerViewHolderAvailablePeers viewHolder = new RecyclerViewHolderAvailablePeers(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolderAvailablePeers holder, int position) {

        holder.getTv_Name().setText(availablePeerList.get(position).getName());
        holder.getTv_adress().setText(availablePeerList.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return availablePeerList.size();
    }

}
