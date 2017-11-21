package com.stadiumfooddelivery;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.stadiumfooddelivery.adapter.CartAdapter;
import com.stadiumfooddelivery.adapter.OnCartItemClickListener;
import com.stadiumfooddelivery.menu.MenuItem;
import com.stadiumfooddelivery.menu.ShoppingCart;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShoppingCartFragment extends Fragment implements OnCartItemClickListener {

    @Inject
    ShoppingCart shoppingCart;

    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    @BindView(R.id.layout_cart)
    ScrollView cartView;

    @BindView(R.id.layout_image_with_text)
    LinearLayout emptyCartContent;

    @BindView(R.id.image)
    ImageView imageView;

    @BindView(R.id.text_description)
    TextView emptyCartText;

    @BindView(R.id.text_total_price)
    TextView totalPrice;

    private CartAdapter adapter;

    @Override
    public void onResume() {
        super.onResume();
        Activity activity = getActivity();
        if (activity instanceof ToolbarTitleSetter) {
            ((ToolbarTitleSetter) activity).setToolbarTitle(getString(R.string.shoppingCart));
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StadiumfoodApplication.getApplication(getContext()).getApplicationComponent().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_shopping_cart, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        updateContent();

        adapter = new CartAdapter(shoppingCart, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }

    private void updateContent() {
        if (shoppingCart.isEmpty()) {
            cartView.setVisibility(View.GONE);
            emptyCartContent.setVisibility(View.VISIBLE);
            emptyCartText.setText(R.string.empty_cart);
            imageView.setImageResource(R.drawable.ic_cart_grey);
            updatePriceText();
        } else {
            cartView.setVisibility(View.VISIBLE);
            emptyCartContent.setVisibility(View.GONE);
            updatePriceText();
        }
    }

    public static ShoppingCartFragment newInstance() {
        return new ShoppingCartFragment();
    }

    private void updatePriceText() {
        totalPrice.setText(shoppingCart.totalPriceAsString());
    }

    @Override
    public void onMinusClick(MenuItem menuItem, int position) {
        if (shoppingCart.removeFromCart(menuItem)) {
            adapter.removeItem(position);
            updateContent();
        } else {
            adapter.notifyItemChanged(position);
        }
        updatePriceText();
    }

    @Override
    public void onPlusClick(MenuItem menuItem, int position) {
        shoppingCart.addToCart(menuItem);
        adapter.notifyItemChanged(position);
        updatePriceText();
    }
}
