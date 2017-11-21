package com.stadiumfooddelivery.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.stadiumfooddelivery.R;
import com.stadiumfooddelivery.menu.MenuItem;
import com.stadiumfooddelivery.menu.ShoppingCart;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MenuItemViewHolder> {

    @NonNull
    ShoppingCart shoppingCart;
    @NonNull
    private final List<MenuItem> items;
    @NonNull
    private final OnCartItemClickListener listener;

    public CartAdapter(@NonNull ShoppingCart cart, @NonNull OnCartItemClickListener listener) {
        this.items = cart.getAddedItems();
        this.listener = listener;
        this.shoppingCart = cart;
    }

    @Override
    public MenuItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false);
        return new CartAdapter.MenuItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MenuItemViewHolder holder, int position) {
        MenuItem menuItem = items.get(holder.getAdapterPosition());
        holder.bind(menuItem, shoppingCart.getCount(menuItem), holder.getAdapterPosition(), listener);
    }

    public void removeItem(int position) {
        items.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, items.size());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class MenuItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.title_item)
        TextView title;
        @BindView(R.id.price_item)
        TextView price;
        @BindView(R.id.image_item)
        ImageView image;
        @BindView(R.id.button_minus)
        ImageButton buttonMinus;
        @BindView(R.id.button_plus)
        ImageButton buttonPlus;
        @BindView(R.id.text_count)
        TextView countText;

        public MenuItemViewHolder(View item) {
            super(item);
            ButterKnife.bind(this, item);
        }

        public void bind(final MenuItem menuItem,
                         int count,
                         final int position,
                         final OnCartItemClickListener listener) {
            title.setText(menuItem.getTitle());
            price.setText(menuItem.getPriceAsString());

            if (menuItem.getId() == 0) {
                image.setImageResource(R.drawable.beer);
            } else {
                image.setImageResource(R.drawable.beer1);
            }

            countText.setText(count + "шт");
            buttonMinus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onMinusClick(menuItem, position);
                }
            });
            buttonPlus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onPlusClick(menuItem, position);
                }
            });
        }
    }
}
