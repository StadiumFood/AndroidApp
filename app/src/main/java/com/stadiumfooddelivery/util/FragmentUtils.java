package com.stadiumfooddelivery.util;

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

public class FragmentUtils {

    private FragmentUtils() {}

    public static void replaceFragment(@NonNull FragmentManager fragmentManager,
                                       @IdRes int container,
                                       @NonNull Fragment fragment) {
        fragmentManager.beginTransaction()
                .replace(container, fragment)
                .commit();
    }

}
