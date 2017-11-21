package com.stadiumfooddelivery;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stadiumfooddelivery.adapter.MainAdapter;
import com.stadiumfooddelivery.adapter.OnMenuItemClickListener;
import com.stadiumfooddelivery.menu.MenuItem;
import com.stadiumfooddelivery.menu.ShoppingCart;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MenuFragment extends Fragment implements OnMenuItemClickListener {

    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    @Inject
    ShoppingCart shoppingCart;

    @Override
    public void onResume() {
        super.onResume();
        Activity activity = getActivity();
        if (activity instanceof ToolbarTitleSetter) {
            ((ToolbarTitleSetter) activity).setToolbarTitle(getString(R.string.menu));
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StadiumfoodApplication.getApplication(getContext()).getApplicationComponent().inject(this);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recycler, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new MainAdapter(generateData(), this));
    }

    public static MenuFragment newInstance() {
        return new MenuFragment();
    }

    @Override
    public void onMenuItemClick(MenuItem menuItem) {
        shoppingCart.addToCart(menuItem);
        Snackbar.make(getView(), "Добавлено", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        return super.onOptionsItemSelected(item);
//        switch(item.getItemId()) {
//            case R.id.menu_shopping_cart:
//        }
    }

    private List<MenuItem> generateData() {
        List<MenuItem> items = new ArrayList<>(Collections.nCopies(
                5,
                new MenuItem(0, "Пиво немецкое", "Очень-очень длинное описание немецкого пива", 120, 0, 0)));
        items.addAll(Collections.nCopies(
                5,
                new MenuItem(1, "Пиво чешское", "Очень-очень длинное описание чешского пива", 150, 0, 0)));
        Collections.shuffle(items);
        return items;
    }
}
