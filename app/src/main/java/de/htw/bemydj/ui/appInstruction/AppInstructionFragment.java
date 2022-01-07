package de.htw.bemydj.ui.appInstruction;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import de.htw.bemydj.databinding.FragmentAppInstructionBinding;

public class AppInstructionFragment extends Fragment {

    private FragmentAppInstructionBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AppInstructionViewModel appInstructionViewModel = new ViewModelProvider(this).get(AppInstructionViewModel.class);

        binding = FragmentAppInstructionBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textAppInstruction;
        appInstructionViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}