package com.small.smyracula.cvapp_5;

import androidx.fragment.app.Fragment;

public interface ParentListener {
    void openChildFragment(Fragment fragment, Boolean addToBackStack);
}
