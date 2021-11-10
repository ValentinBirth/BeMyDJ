package de.htw.bemydj.ui.appInfo;

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

import de.htw.bemydj.databinding.FragmentAppInfoBinding;

public class AppInfoFragment extends Fragment {

    private AppInfoViewModel appInfoViewModel;
    private FragmentAppInfoBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        appInfoViewModel =
                new ViewModelProvider(this).get(AppInfoViewModel.class);

        binding = FragmentAppInfoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textAppInfo;
        appInfoViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
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