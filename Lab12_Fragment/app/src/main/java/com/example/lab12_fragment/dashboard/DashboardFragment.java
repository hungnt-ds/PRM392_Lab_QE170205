package com.example.lab12_fragment.dashboard;

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
import com.example.lab12_fragment.databinding.FragmentDashboardBinding;
import com.example.lab12_fragment.viewmodel.SharedViewModel;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;
    // Sử dụng sharedViewModel để có thể sử dụng data ngoài vòng doi của Fragment
    private SharedViewModel sharedViewModel;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDashboardBinding.inflate(inflater, container,false);
        View root = binding.getRoot();
        sharedViewModel = new ViewModelProvider (requireActivity()).get(SharedViewModel.class);
        // Gửi dữ liệu vào SharedViewModel
        binding.btnUppNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedViewModel.incrementData();
                }
        });

        // Set Observer để hien thị giá tri trực tiếp trên toàn bộ các Fragment giá trị của sharedViewMode
        sharedViewModel.getSharedData().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged (Integer data) {
                binding.textDashboard.setText(String.valueOf(data));
                }
            });
        return root;
    }


//    private DashboardViewModel mViewModel;
//
//    public static DashboardFragment newInstance() {
//        return new DashboardFragment();
//    }
//
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
//                             @Nullable Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.fragment_dashboard, container, false);
//    }
//
//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        mViewModel = new ViewModelProvider(this).get(DashboardViewModel.class);
//        // TODO: Use the ViewModel
//    }

}