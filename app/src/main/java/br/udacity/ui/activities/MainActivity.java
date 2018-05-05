package br.udacity.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.List;

import br.udacity.R;
import br.udacity.components.CustomTextView;
import br.udacity.connection.interfaces.OnError;
import br.udacity.connection.interfaces.OnSucess;
import br.udacity.controllers.mainImpl.MainImpl;
import br.udacity.models.ErrorResponse;
import br.udacity.models.response.BakingResponse;
import br.udacity.ui.adapters.BakingAdapter;
import br.udacity.ui.bases.BaseActivity;
import br.udacity.utils.SimpleIdlingResource;
import retrofit2.Response;

public class MainActivity extends BaseActivity implements BakingAdapter.OnItemClick {

    private RecyclerView rcBakings;
    private SwipeRefreshLayout swipeView;
    private CustomTextView tvNothing;
    private ShimmerFrameLayout shimmer;
    final static String RECYCLER = "RECYCLER";
    final static String RECEIPT = "RECEIPT";
    private Parcelable layoutManagerSavedState;
    @Nullable
    private SimpleIdlingResource mIdlingResource;


    @Override
    protected void setLayout(View view) {
        rcBakings = (RecyclerView) fid(R.id.rc_bakings);
        swipeView = (SwipeRefreshLayout) fid(R.id.swipe);
        tvNothing = (CustomTextView) fid(R.id.tv_nothing);
        shimmer = (ShimmerFrameLayout) fid(R.id.shimmer);
    }

    @Override
    protected void startProperties() {
        setToolbar(false, getString(R.string.str_app_name));
        swipeView.setColorSchemeColors(
                ContextCompat.getColor(getMyContext(), R.color.colorAccent),
                ContextCompat.getColor(getMyContext(), R.color.red_gplus),
                ContextCompat.getColor(getMyContext(), R.color.darker_gray)
        );
        if (savedInstanceState != null) {
            layoutManagerSavedState = savedInstanceState.getParcelable(RECYCLER);
        }
        request(shimmer);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (rcBakings != null && rcBakings.getLayoutManager() != null)
            outState.putParcelable(RECYCLER, rcBakings.getLayoutManager().onSaveInstanceState());
        super.onSaveInstanceState(outState);
    }


    //Faz a requisição ao serviço de receitas
    private void request(final View progress) {
        // Flag for test wait
        if (mIdlingResource != null) {
            mIdlingResource.setIdleState(false);
        }

        getControllerImpl().getBaking(new OnSucess() {
            @Override
            public void onSucessResponse(Response response) {
                rcBakings.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.white_background));
                if (response != null && response.body() != null) {
                    List<BakingResponse> bakingResponses = (List<BakingResponse>) response.body();
                    if (bakingResponses != null && !bakingResponses.isEmpty()) {
                        fillLists(rcBakings, new BakingAdapter(bakingResponses, MainActivity.this));
                    } else {
                        tvNothing.setVisibility(View.VISIBLE);
                    }
                    // Flag for test continue
                    if (mIdlingResource != null) {
                        mIdlingResource.setIdleState(true);
                    }
                }
            }
        }, new OnError() {
            @Override
            public void onErrorResponse(ErrorResponse response) {
                // Flag for test continue
                if (mIdlingResource != null) {
                    mIdlingResource.setIdleState(true);
                }
            }
        },progress, tvNothing);

    }


    private void fillLists(RecyclerView recyclerView, RecyclerView.Adapter adapter) {
        recyclerView.setAdapter(adapter);
        LinearLayoutManager llm = new LinearLayoutManager(getMyContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        llm.setAutoMeasureEnabled(true);
        recyclerView.setLayoutManager(llm);
        if (layoutManagerSavedState != null) {
            recyclerView.getLayoutManager().onRestoreInstanceState(layoutManagerSavedState);
        }
    }


    @Override
    protected void defineListeners() {
        swipeView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                layoutManagerSavedState = null;
                request(swipeView);
            }
        });
    }

    @Override
    protected int getActivityLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected Context getMyContext() {
        return MainActivity.this;
    }

    @Override
    protected MainImpl getControllerImpl() {
        return new MainImpl(getMyContext());
    }

    @Override
    public void onItemClick(View view, BakingResponse items, int position) {
        Intent intent = new Intent(this, RecipeDetailActivity.class);
        intent.putExtra(RECEIPT, items);
        startActivity(intent);
    }

    @VisibleForTesting
    @NonNull
    public IdlingResource getIdlingResource() {
        if (mIdlingResource == null) {
            mIdlingResource = new SimpleIdlingResource();
        }
        return mIdlingResource;
    }

}
