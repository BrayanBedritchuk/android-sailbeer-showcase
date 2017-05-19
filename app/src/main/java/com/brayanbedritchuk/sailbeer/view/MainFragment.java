package com.brayanbedritchuk.sailbeer.view;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.brayanbedritchuk.sailbeer.R;
import com.brayanbedritchuk.sailbeer.helper.ActivityHelper;
import com.brayanbedritchuk.sailbeer.helper.AnimationHelper;
import com.brayanbedritchuk.sailbeer.helper.PreferencesHelper;
import com.brayanbedritchuk.sailbeer.helper.ViewHelper;
import com.brayanbedritchuk.sailbeer.view.adapter.BeerAdapter;
import com.brayanbedritchuk.sailbeer.view.presenter.MainPresenter;
import com.brayanbedritchuk.sailbeer.view.presenter.MainView;

public class MainFragment extends Fragment implements MainView {

    private MainPresenter presenter;

    private AppBarLayout appBar;
    private Toolbar toolbar;

    private ImageView imgBackground;
    private ImageView imgCircle;
    private ImageView imgBeer;
    private TextView tvSailbeer;
    private TextView tvToolbarTitle;
    private FrameLayout animationFrame;
    private RecyclerView recyclerView;
    private BeerAdapter adapter;
    private CardView cardBeers;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        setHasOptionsMenu(true);
        initPresenter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.fragment_main, container, false);
        initViews(contentView);
        return contentView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_main_repeat_animation: {
                getPresenter().onClickMenuRepeatAnimation();
                return true;
            }
            default: {
                return super.onOptionsItemSelected(item);
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getPresenter().onResume();
    }

    private void initPresenter() {
        setPresenter(new MainPresenter(this));
    }

    private void initViews(View contentView) {
        inflateViews(contentView);
        initRecyclerView();
        initToolbar();
        initViewsVisibility();
    }

    public void performInitialAnimation() {
        ActivityHelper.blockScreenOrientation(getActivity());
        initViewsVisibilityBeforeAnimate();
        animateCircle();
    }

    @Override
    public void performCardBeersAnimation() {
        onPerformCardBeersAnimation();

        appBar.animate().alpha(1f).yBy(100f).setDuration(300).withEndAction(new Runnable() {
            @Override
            public void run() {
                cardBeers.setY(cardBeers.getY() + 100f);
                cardBeers.animate().alpha(1f).yBy(-100f).setDuration(300)
                        .withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        onCardBeersAnimationFinished();
                    }
                });
            }
        });
    }

    private void onCardBeersAnimationFinished() {
        ActivityHelper.unblockScreenOrientation(getActivity());
        PreferencesHelper.setInitialAnimationAsShown(getActivity());
        getPresenter().onLastAnimationFinished();
    }

    private void onPerformCardBeersAnimation() {
        appBar.setVisibility(View.VISIBLE);
        cardBeers.setVisibility(View.VISIBLE);

        appBar.setAlpha(0f);
        cardBeers.setAlpha(0f);

        appBar.setY(appBar.getY() - 100f);
    }

    @Override
    public void updateBeerList() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public Context getActivityContext() {
        return getActivity();
    }

    private void inflateViews(View contentView) {
        appBar = (AppBarLayout) contentView.findViewById(R.id.appbar);
        toolbar = (Toolbar) contentView.findViewById(R.id.toolbar);
        imgBackground = (ImageView) contentView.findViewById(R.id.frame_animation__img__background);
        imgCircle = (ImageView) contentView.findViewById(R.id.frame_animation__img__circle);
        imgBeer = (ImageView) contentView.findViewById(R.id.frame_animation__img__beer);
        tvToolbarTitle = (TextView) contentView.findViewById(R.id.toolbar_title);
        tvSailbeer = (TextView) contentView.findViewById(R.id.frame_animation__tv__sailbeer);
        animationFrame = (FrameLayout) contentView.findViewById(R.id.fragment_main__frame__animation);
        recyclerView = (RecyclerView) contentView.findViewById(R.id.fragment_main__recycler__beers);
        cardBeers = (CardView) contentView.findViewById(R.id.fragment_main__cardview__beers);
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new BeerAdapter(getPresenter().getBeers());
        recyclerView.setAdapter(adapter);
    }

    private void initToolbar() {
        initAppCompatActivity();
        initToolbarTitle();
    }

    protected void initAppCompatActivity() {
        AppCompatActivity appCompatActivity = ((AppCompatActivity) getActivity());
        appCompatActivity.setSupportActionBar(toolbar);
        appCompatActivity.getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private void initToolbarTitle() {
        tvToolbarTitle.setText(getString(R.string.app_name));
    }

    private void initViewsVisibility() {
        animationFrame.setVisibility(View.GONE);
    }

    private void initViewsVisibilityBeforeAnimate() {
        animationFrame.setVisibility(View.VISIBLE);

        appBar.setVisibility(View.GONE);
        cardBeers.setVisibility(View.GONE);

        ViewHelper.scaleUp(animationFrame);

        ViewHelper.scaleDown(imgBackground);
        ViewHelper.scaleDown(imgCircle);
        ViewHelper.scaleDown(imgBeer);
        ViewHelper.scaleDown(tvSailbeer);
    }

    private void animateCircle() {
        AnimationHelper.performScaleUpAnimation(imgCircle, new Runnable() {
            @Override
            public void run() {
                animateBackground();
            }
        });
    }

    private void animateBackground() {
        AnimationHelper.performScaleUpAnimation(imgBackground, new Runnable() {
            @Override
            public void run() {
                animateBeer();
            }
        });
    }

    private void animateBeer() {
        AnimationHelper.performScaleUpAnimation(imgBeer, new Runnable() {
            @Override
            public void run() {
                animateWelcomeText();
            }
        });
    }

    private void animateWelcomeText() {
        AnimationHelper.performScaleUpAnimation(tvSailbeer, new Runnable() {
            @Override
            public void run() {
                animateFrame();
            }
        });
    }

    private void animateFrame() {
        animationFrame.animate().scaleX(0)
                .scaleY(0)
                .setInterpolator(new FastOutSlowInInterpolator())
                .setStartDelay(3000).withEndAction(new Runnable() {
            @Override
            public void run() {
                onAnimationFrameFinished();
            }
        });
    }

    private void onAnimationFrameFinished() {
        animationFrame.setVisibility(View.GONE);
        performCardBeersAnimation();
    }

    public MainPresenter getPresenter() {
        return presenter;
    }

    public void setPresenter(MainPresenter presenter) {
        this.presenter = presenter;
    }

}
