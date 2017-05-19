package com.brayanbedritchuk.sailbeer.view.presenter;

import com.brayanbedritchuk.sailbeer.helper.PreferencesHelper;
import com.brayanbedritchuk.sailbeer.model.Beer;
import com.brayanbedritchuk.sailbeer.model.viewmodel.MainViewModel;

import java.util.List;

public class MainPresenter {

    private MainView view;
    private MainViewModel viewModel;

    public MainPresenter(MainView view) {
        setView(view);
        setViewModel(new MainViewModel());
    }

    public void onResume() {
        verifyAndPerformInitialAnimation();
        verifyAndLoadBeers();
        getViewModel().setFirstSession(false);
    }

    private void verifyAndPerformInitialAnimation() {
        if (!PreferencesHelper.wasInitialAnimationShown(getView().getActivityContext())) {
            getViewModel().setPerformingAnimation(true);
            getView().performInitialAnimation();
        }
    }

    private void verifyAndLoadBeers() {
        if (getViewModel().isFirstSession()) {
            new BeerLoaderAsyncTask(getView(), getViewModel()).execute();
        }
    }

    public void onClickMenuRepeatAnimation() {
        if (!getViewModel().isPerformingAnimation()) {
            PreferencesHelper.removeInitialAnimationAsShown(getView().getActivityContext());
            getView().performInitialAnimation();
        }
    }

    public void onLastAnimationFinished() {
        getViewModel().setPerformingAnimation(false);
    }

    public MainViewModel getViewModel() {
        return viewModel;
    }

    public void setViewModel(MainViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public MainView getView() {
        return view;
    }

    public void setView(MainView view) {
        this.view = view;
    }

    public List<Beer> getBeers() {
        return getViewModel().getBeerList();
    }

}
