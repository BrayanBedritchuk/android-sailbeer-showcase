package com.brayanbedritchuk.sailbeer.view;

import android.support.design.widget.AppBarLayout;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.brayanbedritchuk.sailbeer.R;
import com.brayanbedritchuk.sailbeer.helper.AnimationHelper;
import com.brayanbedritchuk.sailbeer.helper.PreferencesHelper;
import com.brayanbedritchuk.sailbeer.model.Beer;
import com.brayanbedritchuk.sailbeer.view.adapter.BeerAdapter;
import com.brayanbedritchuk.sailbeer.view.presenter.MainPresenter;

import java.util.List;

import br.com.sailboat.canoe.base.BaseFragment;
import br.com.sailboat.canoe.helper.UIHelper;
import br.com.sailboat.canoe.helper.ViewHelper;


public class MainFragment extends BaseFragment<MainPresenter> implements MainPresenter.View, BeerAdapter.Callback {

    private AppBarLayout appBar;
    private Toolbar toolbar;
    private ImageView imgBackground;
    private ImageView imgCircle;
    private ImageView imgBeer;
    private TextView tvSailbeer;
    private FrameLayout animationFrame;
    private RecyclerView recycler;

    @Override
    protected int getLayoutId() {
        return R.layout.frg_main;
    }

    @Override
    protected MainPresenter newPresenterInstance() {
        return new MainPresenter(this);
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
                presenter.onClickMenuRepeatAnimation();
                return true;
            }
            default: {
                return super.onOptionsItemSelected(item);
            }
        }
    }

    @Override
    protected void initViews(View view) {
        inflateViews(view);
        initRecyclerView();
        initToolbar();
        initViewsVisibility();
    }

    public void performInitialAnimation() {
        UIHelper.blockScreenOrientation(getActivity());
        initViewsVisibilityBeforeAnimate();
        animateCircle();
    }

    @Override
    public void performBeersAnimation() {
        onPerformBeersAnimation();

        appBar.animate().alpha(1f).yBy(100f).setDuration(300).withEndAction(new Runnable() {
            @Override
            public void run() {
                recycler.setY(recycler.getY() + 100f);
                recycler.animate().alpha(1f).yBy(-100f).setDuration(300)
                        .withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        onBeersAnimationFinished();
                    }
                });
            }
        });
    }

    private void onBeersAnimationFinished() {
        UIHelper.unblockScreenOrientation(getActivity());
        PreferencesHelper.setInitialAnimationAsShown(getActivity());
        presenter.onLastAnimationFinished();
    }

    private void onPerformBeersAnimation() {
        appBar.setVisibility(View.VISIBLE);
        recycler.setVisibility(View.VISIBLE);

        appBar.setAlpha(0f);
        recycler.setAlpha(0f);

        appBar.setY(appBar.getY() - 100f);
    }

    @Override
    public void updateBeerList() {
        recycler.getAdapter().notifyDataSetChanged();
    }

    @Override
    public List<Beer> getBeerList() {
        return presenter.getBeers();
    }

    private void inflateViews(View contentView) {
        appBar = (AppBarLayout) contentView.findViewById(R.id.appbar);
        toolbar = (Toolbar) contentView.findViewById(R.id.toolbar);
        imgBackground = (ImageView) contentView.findViewById(R.id.frame_animation__img__background);
        imgCircle = (ImageView) contentView.findViewById(R.id.frame_animation__img__circle);
        imgBeer = (ImageView) contentView.findViewById(R.id.frame_animation__img__beer);
        tvSailbeer = (TextView) contentView.findViewById(R.id.frame_animation__tv__sailbeer);
        animationFrame = (FrameLayout) contentView.findViewById(R.id.frame_animation);
        recycler = (RecyclerView) contentView.findViewById(R.id.recycler);
    }

    private void initRecyclerView() {
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycler.setAdapter(new BeerAdapter(this));
    }

    private void initToolbar() {
        AppCompatActivity appCompatActivity = ((AppCompatActivity) getActivity());
        appCompatActivity.setSupportActionBar(toolbar);
        appCompatActivity.getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle(R.string.app_name);
    }

    private void initViewsVisibility() {
        animationFrame.setVisibility(View.GONE);
    }

    private void initViewsVisibilityBeforeAnimate() {
        animationFrame.setVisibility(View.VISIBLE);

        appBar.setVisibility(View.GONE);
        recycler.setVisibility(View.GONE);

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
        performBeersAnimation();
    }

}
