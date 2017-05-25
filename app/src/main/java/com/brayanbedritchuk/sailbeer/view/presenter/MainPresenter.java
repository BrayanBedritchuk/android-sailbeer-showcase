package com.brayanbedritchuk.sailbeer.view.presenter;

import android.support.annotation.NonNull;

import com.brayanbedritchuk.sailbeer.R;
import com.brayanbedritchuk.sailbeer.helper.PreferencesHelper;
import com.brayanbedritchuk.sailbeer.model.Beer;
import com.brayanbedritchuk.sailbeer.model.Country;
import com.brayanbedritchuk.sailbeer.persistence.BeerDAO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.sailboat.canoe.base.BasePresenter;
import br.com.sailboat.canoe.helper.AsyncHelper;
import br.com.sailboat.canoe.helper.LogHelper;

public class MainPresenter extends BasePresenter<MainPresenter.View> {

    private MainViewModel viewModel = new MainViewModel();

    public MainPresenter(MainPresenter.View view) {
        super(view);
    }

    @Override
    protected void onResumeFirstSession() {
        loadBeers();
    }

    @Override
    protected void postResume() {
        performInitialAnimation();
    }

    public void onClickMenuRepeatAnimation() {
        if (!viewModel.isPerformingAnimation()) {
            PreferencesHelper.removeInitialAnimationAsShown(getContext());
            view.performInitialAnimation();
        }
    }

    public void onLastAnimationFinished() {
        viewModel.setPerformingAnimation(false);
    }


    public List<Beer> getBeers() {
        return viewModel.getBeerList();
    }

    private void loadBeers() {
        AsyncHelper.execute(new AsyncHelper.Callback() {

            List<Beer> beers;

            @Override
            public void doInBackground() throws Exception {
                beers = BeerDAO.getInstance(getContext()).getBeers();
                initFlagDrawableId();
            }

            @Override
            public void onSuccess() {
                viewModel.getBeerList().clear();
                viewModel.getBeerList().addAll(beers);
                getView().updateBeerList();

                if (!viewModel.isPerformingAnimation()) {
                    view.performBeersAnimation();
                }
            }

            @Override
            public void onFail(Exception e) {
                LogHelper.logException(e);
                showMessage(getString(R.string.msg_error));
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
        });

    }

    private void performInitialAnimation() {
        if (!PreferencesHelper.wasInitialAnimationShown(getContext())) {
            viewModel.setPerformingAnimation(true);
            view.performInitialAnimation();
        }
    }


    public interface View extends BasePresenter.View {
        void performInitialAnimation();
        void updateBeerList();
        void performBeersAnimation();
    }


}
