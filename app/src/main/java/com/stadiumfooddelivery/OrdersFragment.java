package com.stadiumfooddelivery;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrdersFragment extends Fragment {

    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    @BindView(R.id.layout_image_with_text)
    LinearLayout noOrdersContent;

    @BindView(R.id.image)
    ImageView imageView;

    @BindView(R.id.text_description)
    TextView noOrdersText;

    @Override
    public void onResume() {
        super.onResume();
        Activity activity = getActivity();
        if (activity instanceof ToolbarTitleSetter) {
            ((ToolbarTitleSetter) activity).setToolbarTitle(getString(R.string.orders));
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recycler_or_image, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        updateContent();
    }

    private void updateContent() {
        recyclerView.setVisibility(View.GONE);
        noOrdersContent.setVisibility(View.VISIBLE);
        imageView.setImageResource(R.drawable.ic_orders);
        noOrdersText.setText("Чтобы посмотреть историю заказов, войдите в свой профиль");
    }

    public static OrdersFragment newInstance() {
        return new OrdersFragment();
    }
}
