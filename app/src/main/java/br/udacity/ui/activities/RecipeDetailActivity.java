package br.udacity.ui.activities;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import java.util.ArrayList;

import br.udacity.R;
import br.udacity.controllers.detailImpl.DetailImpl;
import br.udacity.models.response.BakingResponse;
import br.udacity.ui.bases.BaseActivityForFragment;
import br.udacity.ui.fragments.RecipeFragment;
import br.udacity.ui.fragments.StepDetailFragment;

public class RecipeDetailActivity extends BaseActivityForFragment {

    private BakingResponse bakingResponse;
    private int intialPos = 0;
    @Override
    protected void start() {
        if(savedInstanceState != null){
            bakingResponse = savedInstanceState.getParcelable(MainActivity.RECEIPT);
        }
        setToolbar(true, String.format(getString(R.string.title_recipe), bakingResponse.getName()));
    }

    @Override
    protected Fragment getInitFragment() {
        bakingResponse = getIntent().getParcelableExtra(MainActivity.RECEIPT);
        if(findViewById(R.id.frame_tablet) != null) {
            setFragment(R.id.frame_tablet, StepDetailFragment.newInstance(new ArrayList<>(bakingResponse.getSteps()), intialPos,true),false,false);
            return RecipeFragment.newInstance(bakingResponse, true);
        }else{
            return RecipeFragment.newInstance(bakingResponse, false);
        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(MainActivity.RECEIPT, bakingResponse);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected DetailImpl getControllerImpl() {
        return new DetailImpl(this);
    }

    @Override
    protected void setLayout(View view) {

    }

    @Override
    protected void defineListeners() {

    }

    @Override
    protected Context getMyContext() {
        return RecipeDetailActivity.this;
    }



}




