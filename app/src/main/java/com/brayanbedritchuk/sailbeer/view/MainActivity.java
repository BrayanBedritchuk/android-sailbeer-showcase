package com.brayanbedritchuk.sailbeer.view;

import br.com.sailboat.canoe.base.BaseActivitySingleFragment;

public class MainActivity extends BaseActivitySingleFragment<MainFragment> {

    @Override
    protected MainFragment newFragmentInstance() {
        return new MainFragment();
    }

}
