package de.htw.bemydj.ui.networkInfo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import de.htw.bemydj.databinding.FragmentNetworkInfoBinding;

public class NetworkInfoFragment extends Fragment {

    private NetworkInfoViewModel networkInfoViewModel;
    private FragmentNetworkInfoBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        networkInfoViewModel =
                new ViewModelProvider(this).get(NetworkInfoViewModel.class);

        binding = FragmentNetworkInfoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textNetworkInfo;
        networkInfoViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}