package com.tl.discountsaroundme.ui_controllers;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tl.discountsaroundme.R;
import com.tl.discountsaroundme.activities.ItemDetailsActivity;
import com.tl.discountsaroundme.entities.Item;

import java.util.ArrayList;


public class ItemViewAdapter extends RecyclerView.Adapter<ItemViewAdapter.ItemView> {
    public final static String DATA_ITEM_NAME = "NAME";
    public final static String DATA_ITEM_DETAILS = "DETAILS";
    public final static String DATA_ITEM_PRICE = "PRICE";
    public final static String DATA_ITEM_STORE = "STORE";
    public final static String DATA_IMAGE = "BitmapImage";
    public final static String DATA_TYPE = "TYPE";
    public final static String DATA_DISCOUNT = "DISCOUNT";
    private Context context;
    private ArrayList<Item> items;

    public ItemViewAdapter(Context context, ArrayList<Item> items) {
        this.items = items;
        this.context = context;
    }

    @Override
    public ItemView onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new ItemView(layoutView);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(ItemView holder, int position) {
        holder.tvItemName.setText(items.get(position).getName());
        holder.tvItemDetails.setText(items.get(position).getDescription());
        holder.tvStoreName.setText(items.get(position).getStore());
        holder.imgString.setText(items.get(position).getPicture());
        holder.type.setText(items.get(position).getType());

        String priceString = "$" + items.get(position).getPrice();
        holder.tvPrice.setText(priceString);

        String discount = String.valueOf(items.get(position).getDiscount());
        holder.itemDiscount.setText(discount);

        GlideApp.with(context)
                .load(items.get(position).getPicture())
                .encodeQuality(10)
                .into(holder.imageView);

        GlideApp.with(context)
                .load("https://grandmall-varna.com/pictures/original_1373.jpg")
                .encodeQuality(5)
                .circleCrop()
                .into(holder.shopImage);

        holder.imageView.animate();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ItemView extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView tvItemName;
        TextView tvItemDetails;
        TextView tvStoreName;
        TextView tvPrice;
        TextView imgString;
        TextView type;
        TextView itemDiscount;
        ImageView shopImage;

        ItemView(final View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.img);
            tvItemName = itemView.findViewById(R.id.tvItemName);
            tvItemDetails = itemView.findViewById(R.id.tvItemDetail);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvStoreName = itemView.findViewById(R.id.tvStoreName);
            imgString = itemView.findViewById(R.id.imgString);
            type = itemView.findViewById(R.id.type);
            itemDiscount = itemView.findViewById(R.id.itemDiscount);
            shopImage = itemView.findViewById(R.id.shop_image);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String details = tvItemDetails.getText().toString();
                    String itemName = tvItemName.getText().toString();
                    String price = tvPrice.getText().toString();
                    String storeName = tvStoreName.getText().toString();
                    String img = imgString.getText().toString();
                    String discountData = itemDiscount.getText().toString();
                    String itemType = type.getText().toString();

                    Intent itemDetailsActivity = new Intent(context, ItemDetailsActivity.class);

                    itemDetailsActivity.putExtra(DATA_ITEM_DETAILS, details);
                    itemDetailsActivity.putExtra(DATA_ITEM_NAME, itemName);
                    itemDetailsActivity.putExtra(DATA_ITEM_STORE, storeName);
                    itemDetailsActivity.putExtra(DATA_ITEM_PRICE, price);
                    itemDetailsActivity.putExtra(DATA_IMAGE, img);
                    itemDetailsActivity.putExtra(DATA_TYPE, itemType);
                    itemDetailsActivity.putExtra(DATA_DISCOUNT, discountData);
                    context.startActivity(itemDetailsActivity);
                }
            });
        }
    }
}

