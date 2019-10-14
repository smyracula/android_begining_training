package com.small.smyracula.cvapp_2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.small.smyracula.cvapp_2.model.Cv;
import com.small.smyracula.cvapp_2.databinding.ActivityDetailBinding;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

public class DetailActivity extends AppCompatActivity {

    private static final String KEY_CV = "key_cv";
    private ActivityDetailBinding binding;
    private Cv cv;

    public static Intent newIntent(Context context, @NonNull Cv cv) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(DetailActivity.KEY_CV, cv);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail);
        final Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            cv = (Cv) bundle.get(KEY_CV);
        }
        setInfo(cv);

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
