package com.small.smyracula.cvapp_3;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.small.smyracula.cvapp_3.model.Cv;


public class DetailFragment extends Fragment {

    private static final String KEY_CV = "key_cv";

    private Cv cv;

    public DetailFragment() {
        // Required empty public constructor
    }

    public static DetailFragment newInstance(Cv cv) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putParcelable(KEY_CV, cv);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            cv = getArguments().getParcelable(KEY_CV);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

}
