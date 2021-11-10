package de.htw.bemydj.ui.userInfo;

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

import de.htw.bemydj.databinding.FragmentUserInfoBinding;

public class UserInfoFragment extends Fragment {

    private UserInfoViewModel userInfoViewModel;
    private FragmentUserInfoBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        userInfoViewModel =
                new ViewModelProvider(this).get(UserInfoViewModel.class);

        binding = FragmentUserInfoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textUserInfo;
        userInfoViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
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