package com.small.smyracula.cvapp_5;

import android.os.Handler;

import androidx.annotation.CallSuper;
import androidx.annotation.IdRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Lifecycle;

public abstract class BaseParentFragment extends Fragment implements ParentListener {
    private Fragment fragment;
    @IdRes
    private int childFragmentContainerId;
    private boolean checkOpenFragment;
    private boolean addToBackStack;
    private boolean checkClearBackStack;

    public void openChildFragment(Fragment fragment, int childFragmentContainerId, Boolean addToBackStack) {
        this.childFragmentContainerId = childFragmentContainerId;
        this.openChildFragment(fragment, addToBackStack);
    }

    @Override
    public void openChildFragment(Fragment fragment, Boolean addToBackStack) {
        this.fragment = fragment;
        checkOpenFragment = false;
        Handler uiHandler = new Handler();
        uiHandler.post(() -> {
            if (this.fragment != null) {
                FragmentManager childFragmentManager = getChildFragmentManager();
                String tag = this.fragment.getClass().getName();
                if (!childFragmentManager.popBackStackImmediate(tag, 0)) {
                    FragmentTransaction transaction = childFragmentManager.beginTransaction();
                    Fragment currentFragment = childFragmentManager.findFragmentById(childFragmentContainerId);
                    if (currentFragment != null) {
                        transaction.hide(currentFragment);
                    }
                    /*transaction.setCustomAnimations(R.anim.fragment_slide_right_enter,
                            R.anim.fragment_slide_left_exit,
                            R.anim.fragment_slide_left_enter,
                            R.anim.fragment_slide_right_exit);*/
                    transaction.replace(childFragmentContainerId, this.fragment, tag);
                    if (addToBackStack) {
                        transaction.addToBackStack(tag);
                    }
                    transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit();
                }
                this.fragment = null;
            }
        });

    }

    @CallSuper
    @Override
    public void onStart() {
        super.onStart();
        if (checkOpenFragment) {
            openChildFragment(new ListFragment(), false);
        }
        if (checkClearBackStack) {
            clearBackStack();
        }
    }

    protected void clearBackStack() {
        if (!getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
            checkClearBackStack = true;
            return;
        } else {
            checkClearBackStack = false;
        }
        FragmentManager fragmentManager = getChildFragmentManager();
        int count = fragmentManager.getBackStackEntryCount();
        for (int i = 0; i < count; i++) {
            fragmentManager.popBackStack();
        }
    }
}
