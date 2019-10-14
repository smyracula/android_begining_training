package com.small.smyracula.cvapp_4;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.small.smyracula.cvapp_4.databinding.FragmentDetailBinding;
import com.small.smyracula.cvapp_4.model.Cv;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

public class DetailFragment extends Fragment {
    private static final String KEY_CV = "key_cv";
    private FragmentDetailBinding binding;
    private Cv cv;

    public DetailFragment() {
        // Required empty public constructor
    }

    public static DetailFragment newInstance(@NonNull Cv cv) {
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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail,container,false);
        setInfo(cv);
        return binding.getRoot();
    }

    private void setInfo(Cv cv) {

        if (cv != null) {
            binding.name.setText(cv.getName());
            binding.surname.setText(cv.getSurname());
            binding.born.setText(String.valueOf(cv.getBorn()));
            binding.from.setText(cv.getFrom());
            binding.university.setText(cv.getUniversity());
            binding.universityGraduated.setText(String.valueOf(cv.getUniversity_graduated()));
            binding.highSchool.setText(cv.getHighSchool());
            binding.highSchoolGraduated.setText(String.valueOf(cv.getHighSchool_graduated()));
            binding.corporate.setText(cv.getCorporate());
        }
    }
}
