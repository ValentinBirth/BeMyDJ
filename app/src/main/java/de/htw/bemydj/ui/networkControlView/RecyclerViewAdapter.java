package de.htw.bemydj.ui.networkControlView;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import de.htw.bemydj.R;
import de.htw.bemydj.djData.AvailablePeer;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolderAvailablePeers> {

    private Context mContext;
    private List<AvailablePeer> availablePeerList;
    private Dialog connectDialog;

    public RecyclerViewAdapter(Context mContext, List<AvailablePeer> availablePeerList){
        this.mContext = mContext;
        this.availablePeerList = availablePeerList;
    }

    @NonNull
    @Override
    public RecyclerViewHolderAvailablePeers onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.item_availablepeer,parent,false);

        RecyclerViewHolderAvailablePeers recyclerViewHolderAvailablePeers = new RecyclerViewHolderAvailablePeers(v);

        connectDialog = new Dialog(mContext);
        connectDialog.setContentView(R.layout.dialog_connect);

        recyclerViewHolderAvailablePeers.getItem_availablePeer().setOnClickListener(new OnClickAvailablePeer(connectDialog, availablePeerList, recyclerViewHolderAvailablePeers));

        return recyclerViewHolderAvailablePeers;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolderAvailablePeers holder, int position) {

        holder.getTv_Name().setText(availablePeerList.get(position).getName());
        holder.getTv_adress().setText(availablePeerList.get(position).getAdress());

    }

    @Override
    public int getItemCount() {
        return availablePeerList.size();
    }

}
