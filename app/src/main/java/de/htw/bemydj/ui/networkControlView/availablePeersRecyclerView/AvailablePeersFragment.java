package de.htw.bemydj.ui.networkControlView.availablePeersRecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import de.htw.bemydj.R;
import de.htw.bemydj.ui.networkControlView.NetworkControlActivity;

public class AvailablePeersFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private final NetworkControlActivity networkControlActivity;
    private RecyclerView availablePeersRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    public AvailablePeersFragment(NetworkControlActivity networkControlActivity) {
        this.networkControlActivity = networkControlActivity;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_available_peers, container, false);
        availablePeersRecyclerView = v.findViewById(R.id.availablePeersRecyclerView);
        availablePeersRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        mSwipeRefreshLayout = v.findViewById(R.id.fragment_available_peers);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        mSwipeRefreshLayout.post(() -> {

            mSwipeRefreshLayout.setRefreshing(true);

            loadRecyclerViewData();
        });

        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onRefresh() {
        loadRecyclerViewData();
    }

    private void loadRecyclerViewData() {
        mSwipeRefreshLayout.setRefreshing(true);
        AvailablePeersRecyclerViewAdapter availablePeersRecyclerViewAdapter = new AvailablePeersRecyclerViewAdapter(getContext(),networkControlActivity.getNetworkControlImpl());
        availablePeersRecyclerView.setAdapter(availablePeersRecyclerViewAdapter);
        mSwipeRefreshLayout.setRefreshing(false);
    }
}
