package com.stadiumfooddelivery.adapter;

import com.stadiumfooddelivery.menu.MenuItem;

public interface OnCartItemClickListener {

    void onMinusClick(MenuItem menuItem, int position);
    void onPlusClick(MenuItem menuItem, int position);
}
