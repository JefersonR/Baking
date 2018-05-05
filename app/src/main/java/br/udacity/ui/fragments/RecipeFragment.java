package br.udacity.ui.fragments;

import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import br.udacity.R;
import br.udacity.models.response.BakingResponse;
import br.udacity.models.response.Step;
import br.udacity.ui.adapters.IngredientsAdapter;
import br.udacity.ui.adapters.StepsAdapter;
import br.udacity.ui.bases.BaseFragment;

/**
 * Created by Jeferson on 21/04/2018.
 */

public class RecipeFragment extends BaseFragment implements StepsAdapter.OnItemClick {


    private RecyclerView rcIngredients;
    private RecyclerView rcSteps;
    private NestedScrollView nestedScrollview;
    private boolean isMasterDetail = false;

    final static String MASTER_DETAIL = "MASTER_DETAIL";
    final static String BAKING = "BAKING";
    final static String POSITION = "POSITION";

    public static RecipeFragment newInstance(BakingResponse bakingResponse, boolean isMasterDetail) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(BAKING, bakingResponse);
        bundle.putBoolean(MASTER_DETAIL, isMasterDetail);
        RecipeFragment fragment = new RecipeFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void setLayout(View view) {
        rcIngredients = (RecyclerView) view.findViewById(R.id.rc_ingredients);
        rcSteps = (RecyclerView) view.findViewById(R.id.rc_steps);
        nestedScrollview = (NestedScrollView) view.findViewById(R.id.nestedScrollview);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putIntArray(POSITION,
                new int[]{nestedScrollview.getScrollX(), nestedScrollview.getScrollY()});
        outState.putBoolean(MASTER_DETAIL, isMasterDetail);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void startProperties() {
        if (savedInstanceState != null) {
            final int[] position = savedInstanceState.getIntArray(POSITION);
            if (position != null)
                nestedScrollview.post(new Runnable() {
                    public void run() {
                        nestedScrollview.scrollTo(position[0], position[1]);
                    }
                });
            isMasterDetail =  savedInstanceState.getBoolean(MASTER_DETAIL);
        }
        if (getArguments() != null) {
            BakingResponse bakingResponse = getArguments().getParcelable(BAKING);
            isMasterDetail  = getArguments().getBoolean(MASTER_DETAIL);
            if (bakingResponse != null) {
                fillLists(true, rcSteps, new StepsAdapter(bakingResponse.getSteps(), this));
                fillLists(false, rcIngredients, new IngredientsAdapter(bakingResponse.getIngredients()));
            }
        }
    }

    @Override
    protected void defineListeners() {

    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_recipe;
    }

    private void fillLists(boolean hasDivider, RecyclerView recyclerView, RecyclerView.Adapter adapter) {
        recyclerView.setAdapter(adapter);
        LinearLayoutManager llm = new LinearLayoutManager(getMyContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        llm.setAutoMeasureEnabled(true);
        if (hasDivider) {
            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                    llm.getOrientation());
            recyclerView.addItemDecoration(dividerItemDecoration);
        }
        recyclerView.setLayoutManager(llm);
        recyclerView.setNestedScrollingEnabled(false);
    }


    @Override
    public void onItemClick(View view, List<Step> items, int position) {
            setFragment(isMasterDetail? R.id.frame_tablet : R.id.frame,StepDetailFragment.newInstance(new ArrayList<>(items), position, isMasterDetail), !isMasterDetail, false);

    }
}
