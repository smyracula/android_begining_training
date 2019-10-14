package com.small.smyracula.cvapp_5;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.small.smyracula.cvapp_5.databinding.FragmentHomeParentBinding;

import androidx.databinding.DataBindingUtil;

public class HomeParentFragment extends BaseParentFragment {
    private FragmentHomeParentBinding binding;


    public HomeParentFragment() {
        // Required empty public constructor
    }

    public static HomeParentFragment newInstance() {
        return new HomeParentFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (savedInstanceState == null) {
            openChildFragment(ListFragment.newInstance(),R.id.home_container , false);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_parent, container, false);
        return binding.getRoot();
    }
}
