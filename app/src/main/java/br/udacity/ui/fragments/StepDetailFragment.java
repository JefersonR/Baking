package br.udacity.ui.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.util.ArrayList;

import br.udacity.R;
import br.udacity.components.OpenSansBoldTextView;
import br.udacity.models.response.Step;
import br.udacity.ui.bases.BaseFragment;

/**
 * Created by Jeferson on 21/04/2018.
 */

public class StepDetailFragment extends BaseFragment {

    private ScrollView nestedScrollview;
    private ArrayList<Step> steps;
    private SimpleExoPlayer mExoPlayer;
    private SimpleExoPlayerView mPlayerView;
    private OpenSansBoldTextView tvTitle;
    private TextView tvDescription;
    private long currentVideo = 0;
    private boolean isReady = true;
    private boolean isMasterDetail = false;

    final static String STEPS = "STEPS";
    final static String POSITION = "POSITION";
    final static String POS = "POS";
    final static String MASTER_DETAIL = "MASTER_DETAIL";
    private static final String POSITION_VIDEO = "POSITION_VIDEO";
    private static final String PLAY_READY = "PLAY_READY";
    private int currentPosition;
    private Button btNext;
    private Button btPrevious;
    private OpenSansBoldTextView tvVideoNotFound;

    public static StepDetailFragment newInstance(ArrayList<Step> steps, int position, boolean isMasterDetail) {
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(STEPS, steps);
        bundle.putInt(POS, position);
        bundle.putBoolean(MASTER_DETAIL, isMasterDetail);
        StepDetailFragment fragment = new StepDetailFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void setLayout(View view) {
        nestedScrollview  = (ScrollView) view.findViewById(R.id.nestedScrollview);
        mPlayerView       = (SimpleExoPlayerView) view.findViewById(R.id.playerView);
        tvVideoNotFound   = (OpenSansBoldTextView) view.findViewById(R.id.tv_video_not_found);
        btPrevious   = (Button) view.findViewById(R.id.bt_previous);
        btNext   = (Button) view.findViewById(R.id.bt_next);
        tvTitle   = (OpenSansBoldTextView) view.findViewById(R.id.tv_title);
        tvDescription   = (TextView) view.findViewById(R.id.tv_description);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putIntArray(POSITION,
                new int[]{ nestedScrollview.getScrollX(), nestedScrollview.getScrollY()});
        outState.putLong(POSITION_VIDEO, currentVideo);
        outState.putBoolean(PLAY_READY, isReady);
        outState.putBoolean(MASTER_DETAIL, isMasterDetail);
        super.onSaveInstanceState(outState);

    }

    @Override
    protected void startProperties() {
        if(savedInstanceState != null) {
            final int[] position = savedInstanceState.getIntArray(POSITION);
            if (position != null)
                nestedScrollview.post(new Runnable() {
                    public void run() {
                        nestedScrollview.scrollTo(position[0], position[1]);
                    }
                });
            currentVideo = savedInstanceState.getLong(POSITION_VIDEO);
            isReady = savedInstanceState.getBoolean(PLAY_READY);
            isMasterDetail =  savedInstanceState.getBoolean(MASTER_DETAIL);
        }
        if( getArguments() != null) {
            steps = getArguments().getParcelableArrayList(STEPS);
            currentPosition = getArguments().getInt(POS);
            isMasterDetail  = getArguments().getBoolean(MASTER_DETAIL);
            if(steps != null) {
                if(steps.get(currentPosition).getVideoURL() != null && !steps.get(currentPosition).getVideoURL().isEmpty()){
                    mPlayerView.setVisibility(View.VISIBLE);
                    tvVideoNotFound.setVisibility(View.GONE);
                    initializePlayer(Uri.parse(steps.get(currentPosition).getVideoURL()));
                }else{
                    mPlayerView.setVisibility(View.GONE);
                    tvVideoNotFound.setVisibility(View.VISIBLE);
                }

                tvDescription.setText(steps.get(currentPosition).getDescription());

                tvTitle.setText(String.format(getString(R.string.label_step_number), currentPosition));
                btPrevious.setText((currentPosition - 1 == 0) ? getString(R.string.label_introduction) : String.format(getString(R.string.label_step_number), currentPosition - 1));
                btNext.setText(String.format(getString(R.string.label_step_number), currentPosition + 1));

                if(currentPosition == 0){
                    btPrevious.setVisibility(View.INVISIBLE);
                    tvTitle.setText(R.string.label_introduction);
                }

                if(currentPosition == steps.size() - 1){
                    btNext.setVisibility(View.INVISIBLE);
                }

            }
        }
    }


    @Override
    protected void defineListeners() {
        btPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(isMasterDetail? R.id.frame_tablet : R.id.frame,StepDetailFragment.newInstance(steps, currentPosition - 1,isMasterDetail), false, false);
            }
        });

        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(isMasterDetail? R.id.frame_tablet : R.id.frame,StepDetailFragment.newInstance(steps, currentPosition + 1,isMasterDetail), false, true);
            }
        });
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_step_detail;
    }
    /**
     * Initialize ExoPlayer.
     * @param mediaUri The URI of the sample to play.
     */
    private void initializePlayer(Uri mediaUri) {
        if (mExoPlayer == null) {
            // Create an instance of the ExoPlayer.
            TrackSelector trackSelector = new DefaultTrackSelector();
            LoadControl loadControl = new DefaultLoadControl();
            mExoPlayer = ExoPlayerFactory.newSimpleInstance(getActivity(), trackSelector, loadControl);
            mPlayerView.setPlayer(mExoPlayer);
            // Prepare the MediaSource.
            String userAgent = Util.getUserAgent(getActivity(), getString(R.string.app_name));
            MediaSource mediaSource = new ExtractorMediaSource(mediaUri, new DefaultDataSourceFactory(
                    getActivity(), userAgent), new DefaultExtractorsFactory(), null, null);
            mExoPlayer.prepare(mediaSource);
            // onRestore
            if (currentVideo != 0)
                mExoPlayer.seekTo(currentVideo);

            mExoPlayer.setPlayWhenReady(isReady);
        }
    }
    /**
     * Release ExoPlayer.
     */
    private void releasePlayer() {
        if (mExoPlayer != null) {
            isReady = mExoPlayer.getPlayWhenReady();
            currentVideo = mExoPlayer.getCurrentPosition();

            mExoPlayer.stop();
            mExoPlayer.release();
            mExoPlayer = null;
        }
    }

    /**
     * Release the player when the activity is destroyed.
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        releasePlayer();
    }

}
