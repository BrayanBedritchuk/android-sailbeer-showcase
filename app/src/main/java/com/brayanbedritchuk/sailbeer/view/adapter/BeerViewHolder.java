package com.brayanbedritchuk.sailbeer.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.brayanbedritchuk.sailbeer.R;
import com.brayanbedritchuk.sailbeer.model.Beer;

public class BeerViewHolder extends RecyclerView.ViewHolder {

    private TextView tvName;
    private TextView tvCountry;
    private ImageView imgFlag;

    public BeerViewHolder(View itemView) {
        super(itemView);
        initViews(itemView);
    }

    private void initViews(View view) {
        tvName = (TextView) view.findViewById(R.id.view_holder_beer__tv__name);
        tvCountry = (TextView) view.findViewById(R.id.view_holder_beer__tv__country);
        imgFlag = (ImageView) view.findViewById(R.id.view_holder_beer__img_flag);
    }

    public void bindData(Beer beer) {
        tvName.setText(beer.getName());
        tvCountry.setText(beer.getCountry());
        imgFlag.setImageResource(beer.getFlagDrawableId());
    }

}
