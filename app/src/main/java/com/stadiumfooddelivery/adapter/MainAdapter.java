package com.stadiumfooddelivery.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.stadiumfooddelivery.R;
import com.stadiumfooddelivery.menu.MenuItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MenuItemViewHolder> {

    private final List<MenuItem> items;
    private final OnMenuItemClickListener listener;

    public MainAdapter(List<MenuItem> items, OnMenuItemClickListener listener) {
        this.items = items;
        this.listener = listener;
    }

    @Override
    public MenuItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_item, parent, false);
        return new MenuItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MenuItemViewHolder holder, int position) {
        holder.bind(items.get(holder.getAdapterPosition()), listener);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class MenuItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.title_item)
        TextView title;
        @BindView(R.id.description_item)
        TextView description;
        @BindView(R.id.price_item)
        TextView price;
        @BindView(R.id.image_item)
        ImageView image;
        @BindView(R.id.button_add_to_cart)
        Button button;

        public MenuItemViewHolder(View item) {
            super(item);
            ButterKnife.bind(this, item);
        }

        public void bind(final MenuItem menuItem, final OnMenuItemClickListener listener) {
            title.setText(menuItem.getTitle());
            description.setText(menuItem.getDescription());
            price.setText(menuItem.getPriceAsString());

            if (menuItem.getId() == 0) {
                image.setImageResource(R.drawable.beer);
            } else {
                image.setImageResource(R.drawable.beer1);
            }

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onMenuItemClick(menuItem);
                }
            });
        }
    }
}
