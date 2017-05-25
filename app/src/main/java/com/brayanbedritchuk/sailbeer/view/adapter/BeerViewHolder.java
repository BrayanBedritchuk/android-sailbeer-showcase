package com.brayanbedritchuk.sailbeer.view.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.brayanbedritchuk.sailbeer.R;
import com.brayanbedritchuk.sailbeer.model.Beer;

import br.com.sailboat.canoe.base.BaseViewHolder;

public class BeerViewHolder extends BaseViewHolder {

    private TextView tvName;
    private TextView tvCountry;
    private ImageView imgFlag;


    public static BeerViewHolder newInstance(ViewGroup parent) {
        View view = inflateLayout(parent, R.layout.vh_beer);
        return new BeerViewHolder(view);
    }


    public BeerViewHolder(View itemView) {
        super(itemView);
        initViews(itemView);
    }

    public void bindItem(Beer beer) {
        tvName.setText(beer.getName());
        tvCountry.setText(beer.getCountry());
        imgFlag.setImageResource(beer.getFlagDrawableId());
    }

    private void initViews(View view) {
        tvName = (TextView) view.findViewById(R.id.vh_beer__tv__name);
        tvCountry = (TextView) view.findViewById(R.id.vh_beer__tv__country);
        imgFlag = (ImageView) view.findViewById(R.id.vh_beer__img__flag);
    }

}
