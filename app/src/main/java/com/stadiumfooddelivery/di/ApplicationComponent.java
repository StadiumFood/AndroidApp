package com.stadiumfooddelivery.di;

import com.stadiumfooddelivery.MenuFragment;
import com.stadiumfooddelivery.ShoppingCartFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    void inject(ShoppingCartFragment obj);
    void inject(MenuFragment obj);
}
