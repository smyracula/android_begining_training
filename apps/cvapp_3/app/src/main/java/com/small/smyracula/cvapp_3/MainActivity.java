package com.small.smyracula.cvapp_3;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import com.google.gson.Gson;
import com.small.smyracula.cvapp_3.databinding.ActivityMainBinding;
import com.small.smyracula.cvapp_3.model.Cv;

import java.io.IOException;
import java.io.InputStream;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Lifecycle;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityMainBinding binding;
    Cv cv;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        cv = new Gson().fromJson(readJSONFromAsset(), Cv.class);
        binding.name.setText(cv.getName() + " " + cv.getSurname());
        binding.name.setOnClickListener(this);
    }

    private String readJSONFromAsset() {
        String json = "";
        try {
            InputStream inputStream = getAssets().open("cv.json");
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
                binding.detailContainer.setVisibility(View.VISIBLE);
                binding.name.setVisibility(View.GONE);

                if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
                    int fragmentId = R.id.detail_container;
                    Fragment fragment = DetailFragment.newInstance(cv);
                    if (fragment != null && fragmentId != 0) {
                        FragmentManager manager = getSupportFragmentManager();
                        String tag = fragment.getClass().getName();
                        if(!manager.popBackStackImmediate(tag,0)){
                            FragmentTransaction tran = manager.beginTransaction();
                            tran.add(fragmentId,fragment,tag);
                            tran.addToBackStack(tag);
                            tran.commit();
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onBackPressed() {
        FragmentManager manager = getSupportFragmentManager();
        if(manager.findFragmentById(R.id.detail_container) != null){
            binding.name.setVisibility(View.VISIBLE);
            manager.popBackStack();
            binding.detailContainer.setVisibility(View.GONE);
        } else {
            super.onBackPressed();
        }
    }
}
