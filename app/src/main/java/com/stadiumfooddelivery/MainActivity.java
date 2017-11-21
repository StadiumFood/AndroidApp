package com.stadiumfooddelivery;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.stadiumfooddelivery.util.FragmentUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, ToolbarTitleSetter {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        initNavigation();

        if (savedInstanceState == null) {
            FragmentUtils.replaceFragment(getSupportFragmentManager(), R.id.container, MenuFragment.newInstance());
        }
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        switch (item.getItemId()) {
            case R.id.menu_navigation_item_main:
                fragment = MenuFragment.newInstance();
                break;
            case R.id.menu_navigation_item_cart:
                fragment = ShoppingCartFragment.newInstance();
                break;
            case R.id.menu_navigation_item_settings:
                fragment = SettingsFragment.newInstance();
                break;
            case R.id.menu_navigation_item_orders:
                fragment = OrdersFragment.newInstance();
                break;
            case R.id.menu_navigation_item_about:
                fragment = AboutFragment.newInstance();
                break;
            default:
                startActivity(new Intent(this, TestActivity.class));
                return true;
//                Snackbar.make(drawerLayout, item.getTitle(), Snackbar.LENGTH_SHORT).show();
//                return true;
//                throw new IllegalArgumentException();
        }
        FragmentUtils.replaceFragment(getSupportFragmentManager(), R.id.container, fragment);
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_shopping_cart:
                FragmentUtils.replaceFragment(getSupportFragmentManager(),
                        R.id.container, ShoppingCartFragment.newInstance());
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initNavigation() {
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.open, R.string.close
        );
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        NavigationView navigationView = findViewById(R.id.navigation);

        navigationView.setNavigationItemSelectedListener(this);

        View navigationHeader = navigationView.getHeaderView(0);
        navigationHeader.findViewById(R.id.layout_navigation_header).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentUtils.replaceFragment(getSupportFragmentManager(), R.id.container, LoginFragment.newInstance());
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });
//        TextView userInfoText = navigationHeader.findViewById(R.id.text_user_info);
//        userInfoText.setText("User");
//        TextView userAdditionalInfoText = navigationHeader.findViewById(R.id.text_user_additional_info);
//        userAdditionalInfoText.setText("Addtional info");
    }

    @Override
    public void setToolbarTitle(CharSequence text) {
        if (toolbar != null) {
            toolbar.setTitle(text);
        }
    }
}
