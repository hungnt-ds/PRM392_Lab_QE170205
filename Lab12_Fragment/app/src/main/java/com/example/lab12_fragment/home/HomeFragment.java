package com.example.lab12_fragment.home;

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
import com.example.lab12_fragment.databinding.FragmentHomeBinding;
import com.example.lab12_fragment.viewmodel.SharedViewModel;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private SharedViewModel sharedViewModel;
    public View onCreateView (@NonNull LayoutInflater inflater,
              ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Khai báo viewModel
        sharedViewModel = new ViewModelProvider (requireActivity()).get(SharedViewModel.class);

        // Nhận dữ liệu và hien thi no
        sharedViewModel.getSharedData().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged (Integer data) {
                binding.textHome.setText(String.valueOf(data));
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

//    private HomeViewModel mViewModel;
//
//    public static HomeFragment newInstance() {
//        return new HomeFragment();
//    }
//
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
//                             @Nullable Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.fragment_home, container, false);
//    }
//
//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        mViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
//        // TODO: Use the ViewModel
//    }

//}