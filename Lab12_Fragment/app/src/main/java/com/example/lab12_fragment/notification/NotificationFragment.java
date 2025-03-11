package com.example.lab12_fragment.notification;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lab12_fragment.R;
import com.example.lab12_fragment.databinding.FragmentNotificationBinding;
import com.example.lab12_fragment.viewmodel.SharedViewModel;

public class NotificationFragment extends Fragment {


    private FragmentNotificationBinding binding;
    private SharedViewModel sharedViewModel;
    public View onCreateView (@NonNull LayoutInflater inflater,
                              ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentNotificationBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Khai báo viewModel
        sharedViewModel = new ViewModelProvider (requireActivity()).get(SharedViewModel.class);

        // Nhận dữ liệu và hien thi no
        sharedViewModel.getSharedData().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged (Integer data) {
                binding.textNotification.setText(String.valueOf(data));
            }
        });

        return root;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
//    private NotificationViewModel mViewModel;
//
//    public static NotificationFragment newInstance() {
//        return new NotificationFragment();
//    }
//
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
//                             @Nullable Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.fragment_notification, container, false);
//    }
//
//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        mViewModel = new ViewModelProvider(this).get(NotificationViewModel.class);
//        // TODO: Use the ViewModel
//    }

}