package com.example.hitc;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class CartAdapter extends ArrayAdapter<CartItem> {

    private final Activity context;
    private final List<CartItem> cartList;
    private final Runnable onCartChanged;

    public CartAdapter(Activity context, List<CartItem> cartList, Runnable onCartChanged) {
        super(context, R.layout.item_cart, cartList);
        this.context = context;
        this.cartList = cartList;
        this.onCartChanged = onCartChanged;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;

        if (rowView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            rowView = inflater.inflate(R.layout.item_cart, parent, false);
        }

        TextView tvCartName = rowView.findViewById(R.id.tvCartName);
        TextView tvCartPrice = rowView.findViewById(R.id.tvCartPrice);
        TextView tvCartQuantity = rowView.findViewById(R.id.tvCartQuantity);
        Button btnMinus = rowView.findViewById(R.id.btnMinus);
        Button btnPlus = rowView.findViewById(R.id.btnPlus);
        Button btnRemove = rowView.findViewById(R.id.btnRemove);

        CartItem item = cartList.get(position);

        tvCartName.setText(item.getProduct().getName());
        tvCartPrice.setText("Tạm tính: " + item.getTotalPrice() + " đ");
        tvCartQuantity.setText("Số lượng: " + item.getQuantity());

        btnPlus.setOnClickListener(v -> {
            CartManager.increase(item.getProduct());
            notifyDataSetChanged();
            onCartChanged.run();
        });

        btnMinus.setOnClickListener(v -> {
            CartManager.decrease(item.getProduct());
            notifyDataSetChanged();
            onCartChanged.run();
        });

        btnRemove.setOnClickListener(v -> {
            CartManager.remove(item.getProduct());
            notifyDataSetChanged();
            onCartChanged.run();
        });

        return rowView;
    }
}