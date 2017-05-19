package com.brayanbedritchuk.sailbeer.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.brayanbedritchuk.sailbeer.R;
import com.brayanbedritchuk.sailbeer.model.Beer;

import java.util.List;

public class BeerAdapter extends RecyclerView.Adapter<BeerViewHolder> {

    private List<Beer> beerList;

    public BeerAdapter(List<Beer> items) {
        setBeerList(items);
    }
    @Override
    public BeerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflateLayout(parent, R.layout.view_holder_beer);
        return new BeerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BeerViewHolder holder, int position) {
        Beer beer = getBeerList().get(position);
        holder.bindData(beer);
    }

    @Override
    public int getItemCount() {
        return getBeerList().size();
    }

    private View inflateLayout(ViewGroup parent, int layoutId) {
        return LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
    }

    public List<Beer> getBeerList() {
        return beerList;
    }

    public void setBeerList(List<Beer> beerList) {
        this.beerList = beerList;
    }

}
