package com.small.smyracula.cvapp_5;

import android.os.Bundle;

import com.small.smyracula.cvapp_5.databinding.ActivityMainBinding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Lifecycle;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private boolean addToBackStack = true;
    private int currentFragmentId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        openFragment(new HomeParentFragment(), R.id.main_container);
    }

    private void openFragment(Fragment currentFragment, int currentFragmentId) {
        this.currentFragmentId = currentFragmentId;
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
    public void onBackPressed() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(currentFragmentId);
        if (fragment instanceof BaseParentFragment) {
            final FragmentManager childFragmentManager = fragment.getChildFragmentManager();
            if (childFragmentManager.getBackStackEntryCount() > 0) {
                childFragmentManager.popBackStack();
            } else {
                super.onBackPressed();
            }
        } else {
            super.onBackPressed();
        }
    }
}
