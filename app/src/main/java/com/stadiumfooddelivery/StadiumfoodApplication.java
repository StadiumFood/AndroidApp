package com.stadiumfooddelivery;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.stadiumfooddelivery.di.ApplicationComponent;
import com.stadiumfooddelivery.di.DaggerApplicationComponent;

public class StadiumfoodApplication extends Application {

    @NonNull
    private ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerApplicationComponent.builder().build();
    }

    public ApplicationComponent getApplicationComponent() {
        return component;
    }

    public static StadiumfoodApplication getApplication(Context context) {
        return (StadiumfoodApplication) context.getApplicationContext();
    }
}
