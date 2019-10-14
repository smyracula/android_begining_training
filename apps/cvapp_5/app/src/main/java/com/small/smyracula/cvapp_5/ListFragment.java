package com.small.smyracula.cvapp_5;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.small.smyracula.cvapp_5.databinding.FragmentListBinding;
import com.small.smyracula.cvapp_5.model.Cv;

import java.io.IOException;
import java.io.InputStream;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

public class ListFragment extends Fragment implements View.OnClickListener {
    private FragmentListBinding binding;
    private ParentListener parentListener = null;
    private Cv cv;

    public ListFragment() {
        // Required empty public constructor
    }

    public static ListFragment newInstance() {
        return new ListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        parentListener = (ParentListener) getParentFragment();
        cv = new Gson().fromJson(readJSONFromAsset(), Cv.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false);
        binding.name.setText(cv.getName() + " " + cv.getSurname());
        binding.name.setOnClickListener(this);
        return binding.getRoot();
    }

    private String readJSONFromAsset() {
        String json = "";
        try {
            InputStream inputStream = getActivity().getAssets().open("cv.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, "UTF-8");

        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.name: {
                parentListener.openChildFragment(DetailFragment.newInstance(this.cv),true);
            }
        }
    }
}
