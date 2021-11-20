package de.htw.bemydj.ui.networkInfo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import de.htw.bemydj.R;

public class AvailablePeersFragment extends Fragment {
    NetworkControlActivity networkControlActivity;
    View v;
    private RecyclerView availablePeersRecyclerView;
    private List<AvailablePeer> availablePeerList;

    public AvailablePeersFragment(NetworkControlActivity networkControlActivity) {
        this.networkControlActivity = networkControlActivity;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_available_peers,container,false);
        availablePeersRecyclerView = (RecyclerView) v.findViewById(R.id.availablePeersRecyclerView);
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(getContext(),availablePeerList);
        availablePeersRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        availablePeersRecyclerView.setAdapter(recyclerViewAdapter);
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        availablePeerList= new ArrayList<>();
        //TODO Peers cant find others
        if(networkControlActivity.getDeviceNames().isEmpty()){
            availablePeerList.add(new AvailablePeer("test","test"));
        }
        for (String name:networkControlActivity.getDeviceNames()) {
            availablePeerList.add( new AvailablePeer(name,"test"));

        }
        availablePeerList.add(new AvailablePeer("test1","test1"));
        availablePeerList.add(new AvailablePeer("test2","test2"));
        availablePeerList.add(new AvailablePeer("test3","test3"));

    }
}
