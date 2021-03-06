package de.htw.bemydj.ui.networkControlView.groupPeersRecyclerView;

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

public class GroupFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{
    private final NetworkControlActivity networkControlActivity;
    private RecyclerView groupPeersRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    public GroupFragment(NetworkControlActivity networkControlActivity) {
        this.networkControlActivity = networkControlActivity;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_group, container, false);
        groupPeersRecyclerView = v.findViewById(R.id.groupPeersRecyclerView);
        groupPeersRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        mSwipeRefreshLayout = v.findViewById(R.id.fragment_group);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        mSwipeRefreshLayout.post(() -> {

            mSwipeRefreshLayout.setRefreshing(true);

            loadRecyclerViewData();
        });

        return v;
    }

    @Override
    public void onRefresh() {
        loadRecyclerViewData();
    }

    private void loadRecyclerViewData() {
        mSwipeRefreshLayout.setRefreshing(true);
        GroupPeersRecyclerViewAdapter groupPeersRecyclerViewAdapter = new GroupPeersRecyclerViewAdapter(getContext(),networkControlActivity.getNetworkControlImpl());
        groupPeersRecyclerView.setAdapter(groupPeersRecyclerViewAdapter);
        mSwipeRefreshLayout.setRefreshing(false);
    }
}
