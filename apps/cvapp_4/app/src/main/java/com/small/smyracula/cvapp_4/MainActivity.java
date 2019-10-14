package com.small.smyracula.cvapp_4;

import android.annotation.SuppressLint;

import android.os.Bundle;

import android.view.View;

import com.google.gson.Gson;
import com.small.smyracula.cvapp_4.databinding.ActivityMainBinding;
import com.small.smyracula.cvapp_4.model.Cv;

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
    private Cv cv;
    private boolean addToBackStack = true;

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

    private void openFragment(Fragment currentFragment,int currentFragmentId) {
        if (!getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
            return;
        }
        if ((currentFragment != null) && (currentFragmentId != 0)) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            String currentTag = currentFragment.getClass().getName();
            if (!fragmentManager.popBackStackImmediate(currentTag, 0)) {
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                /*if (animated) {
                    transaction.setCustomAnimations(R.anim.fragment_slide_right_enter,
                            R.anim.fragment_slide_left_exit,
                            R.anim.fragment_slide_left_enter,
                            R.anim.fragment_slide_right_exit);
                }*/
                transaction.replace(currentFragmentId, currentFragment, currentTag);
                if (addToBackStack) {
                    transaction.addToBackStack(currentTag);
                }
                transaction.commit();
            }
        }
    }

    @Override
    public void onBackPressed(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        if(fragmentManager.findFragmentById(R.id.detail_container) != null) {
            binding.detailContainer.setVisibility(View.GONE);
            binding.mainFrame.setVisibility(View.VISIBLE);
            fragmentManager.popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.name: {
                binding.detailContainer.setVisibility(View.VISIBLE);
                binding.mainFrame.setVisibility(View.GONE);
                openFragment(DetailFragment.newInstance(this.cv),R.id.detail_container);
            }
        }
    }


}

