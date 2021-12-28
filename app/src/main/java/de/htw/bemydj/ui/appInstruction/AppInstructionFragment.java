package de.htw.bemydj.ui.appInstruction;

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

import de.htw.bemydj.databinding.FragmentAppInstructionBinding;

public class AppInstructionFragment extends Fragment {

    private AppInstructionViewModel appInstructionViewModel;
    private FragmentAppInstructionBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        appInstructionViewModel =
                new ViewModelProvider(this).get(AppInstructionViewModel.class);

        binding = FragmentAppInstructionBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textAppInstruction;
        appInstructionViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
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