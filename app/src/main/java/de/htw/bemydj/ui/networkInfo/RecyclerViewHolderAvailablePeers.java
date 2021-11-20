package de.htw.bemydj.ui.networkInfo;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import de.htw.bemydj.R;

class RecyclerViewHolderAvailablePeers extends RecyclerView.ViewHolder {

    private TextView tv_Name;
    private TextView tv_adress;

    public RecyclerViewHolderAvailablePeers(@NonNull View itemView) {
        super(itemView);
        tv_Name = (TextView) itemView.findViewById(R.id.peername);
        tv_adress = (TextView) itemView.findViewById(R.id.peeradress);
    }

    public TextView getTv_Name() {
        return tv_Name;
    }

    public TextView getTv_adress() {
        return tv_adress;
    }
}
