package com.brayanbedritchuk.sailbeer.view.presenter;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import com.brayanbedritchuk.sailbeer.model.Beer;
import com.brayanbedritchuk.sailbeer.model.Country;
import com.brayanbedritchuk.sailbeer.model.viewmodel.MainViewModel;
import com.brayanbedritchuk.sailbeer.persistence.BeerDAO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class BeerLoaderAsyncTask extends AsyncTask<Void, Integer, Exception> {

    private List<Beer> beers;
    private MainView view;
    private MainViewModel viewModel;

    public BeerLoaderAsyncTask(MainView view, MainViewModel viewModel) {
        setView(view);
        setViewModel(viewModel);
        beers = new ArrayList<>();
    }

    protected Exception doInBackground(Void... urls) {
        try {
            this.beers = getBeerDAO().getBeers();
            initFlagDrawableId();

            return null;
        } catch (Exception e) {
            return e;
        }
    }

    protected void onPostExecute(Exception exeption) {
        verifyAndHandleException(exeption);
        updateViewModelBeerList();
        getView().updateBeerList();

        if (!getViewModel().isPerformingAnimation()) {
            getView().performCardBeersAnimation();
        }
    }

    private void updateViewModelBeerList() {
        getViewModel().getBeerList().clear();
        getViewModel().getBeerList().addAll(beers);
    }

    private void initFlagDrawableId() {
        Map<String, Integer> mapCountries = getCountriesMapFromEnum();

        for (Beer beer : beers) {
            beer.setFlagDrawableId(mapCountries.get(beer.getCountry()));
        }
    }

    @NonNull
    private Map<String, Integer> getCountriesMapFromEnum() {
        Map<String, Integer> mapCountries = new HashMap<>();
        for (Country country : Country.values()) {
            mapCountries.put(country.getName(), country.getFlagId());
        }

        return mapCountries;
    }

    private void verifyAndHandleException(Exception exeption) {
        if (exeption != null) {
            Log.e("SAILBEER_EXCEPTION", "An error occurred while getting the list of beers", exeption);
            getView().showToast(exeption.getMessage());
        }
    }

    private BeerDAO getBeerDAO() {
        return BeerDAO.getInstance(getView().getActivityContext());
    }

    public MainView getView() {
        return view;
    }

    public void setView(MainView view) {
        this.view = view;
    }

    public MainViewModel getViewModel() {
        return viewModel;
    }

    public void setViewModel(MainViewModel viewModel) {
        this.viewModel = viewModel;
    }
}
