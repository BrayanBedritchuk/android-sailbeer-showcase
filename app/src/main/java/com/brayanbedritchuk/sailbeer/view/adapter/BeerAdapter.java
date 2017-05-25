package com.brayanbedritchuk.sailbeer.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.brayanbedritchuk.sailbeer.model.Beer;

import java.util.List;

public class BeerAdapter extends RecyclerView.Adapter<BeerViewHolder> {

    private BeerAdapter.Callback callback;

    public BeerAdapter(Callback callback) {
        this.callback = callback;
    }

    @Override
    public BeerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return BeerViewHolder.newInstance(parent);
    }

    @Override
    public void onBindViewHolder(BeerViewHolder holder, int position) {
        Beer beer = callback.getBeerList().get(position);
        holder.bindItem(beer);
    }

    @Override
    public int getItemCount() {
        return callback.getBeerList().size();
    }


    public interface Callback {
        List<Beer> getBeerList();
    }


}
