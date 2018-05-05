package br.udacity.connection;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.facebook.shimmer.ShimmerFrameLayout;

import br.udacity.R;
import br.udacity.utils.Constants;
import br.udacity.utils.DialogCustomUtil;

import br.udacity.connection.interfaces.OnError;
import br.udacity.connection.interfaces.OnErrorServer;
import br.udacity.connection.interfaces.OnSucess;
import br.udacity.models.ErrorResponse;
import br.udacity.utils.ConnectionChecker;
import br.udacity.utils.DialogSingleton;
import br.udacity.utils.Log;
import br.udacity.utils.Snackbar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Jeferson on 26/04/2017.
 */

public class GenericRestCallBack<T> implements Callback<T>, Constants {
    private OnSucess onSucessListener;
    private OnError onErrorListener;
    private OnErrorServer onErrorServer;
    private ProgressBar progressBar;
    private ShimmerFrameLayout shimmer;
    private SwipeRefreshLayout swipeRefreshLayout;
    private TextView txtNothing;
    private Context mContext;
    private Call<T> mCall;
    private boolean isBlocked;
    private ProgressDialog progressDialog;


    private Context getContext() {
        return mContext;
    }

    private void setContext(Context mContext) {
        this.mContext = mContext;
    }

    public void request(Context context, Call<T> call) {
        doRequest(context, call);
    }

    public void request(Context context, Call<T> call, ProgressBar progressBar) {
        this.progressBar = progressBar;
        doRequest(context, call);
    }

    public void request(Context context, Call<T> call, OnSucess onSucessListener) {
        this.onSucessListener = onSucessListener;
        doRequest(context, call);
    }

    public void request(Context context, Call<T> call, OnSucess onSucessListener, boolean isBlocked) {
        this.onSucessListener = onSucessListener;
        this.isBlocked = isBlocked;
        doRequestBlock(context, call);
    }

    public void request(Context context, Call<T> call, OnSucess onSucessListener, OnError onErrorListener) {
        this.onSucessListener = onSucessListener;
        this.onErrorListener = onErrorListener;
        doRequest(context, call);
    }

    public void request(Context context, Call<T> call, OnSucess onSucessListener, ProgressBar progressBar) {
        this.progressBar = progressBar;
        this.onSucessListener = onSucessListener;
        doRequest(context, call);
    }
    public void request(Context context, Call<T> call, OnSucess onSucessListener, ShimmerFrameLayout shimmer) {
        this.shimmer = shimmer;
        this.onSucessListener = onSucessListener;
        doRequest(context, call);
    }

    public void request(Context context, Call<T> call, OnSucess onSucessListener, SwipeRefreshLayout swipeRefreshLayout, TextView txtNothing) {
        this.onSucessListener = onSucessListener;
        this.swipeRefreshLayout = swipeRefreshLayout;
        this.txtNothing = txtNothing;
        doRequest(context, call);
    }

    public void request(Context context, Call<T> call, OnSucess onSucessListener, View progress, TextView txtNothing) {
        if (progress instanceof ProgressBar) {
            this.progressBar = (ProgressBar) progress;
        } else if (progress instanceof SwipeRefreshLayout) {
            this.swipeRefreshLayout = (SwipeRefreshLayout) progress;
        }else if(progress instanceof ShimmerFrameLayout){
            this.shimmer = (ShimmerFrameLayout) progress;
        }
        this.onSucessListener = onSucessListener;
        this.txtNothing = txtNothing;
        doRequest(context, call);
    }

    public void request(Context context, Call<T> call, OnSucess onSucessListener, OnError onErrorListener, View progress, TextView txtNothing) {
        if (progress instanceof ProgressBar) {
            this.progressBar = (ProgressBar) progress;
        } else if (progress instanceof SwipeRefreshLayout) {
            this.swipeRefreshLayout = (SwipeRefreshLayout) progress;
        }else if(progress instanceof ShimmerFrameLayout){
            this.shimmer = (ShimmerFrameLayout) progress;
        }
        this.onSucessListener = onSucessListener;
        this.onErrorListener = onErrorListener;
        this.txtNothing = txtNothing;
        doRequest(context, call);
    }


    public void request(Context context, Call<T> call, OnSucess onSucessListener, SwipeRefreshLayout swipeRefreshLayout) {
        this.onSucessListener = onSucessListener;
        this.swipeRefreshLayout = swipeRefreshLayout;
        doRequest(context, call);
    }

    public void request(Context context, Call<T> call, OnSucess onSucessListener, OnError onErrorListener, ProgressBar progressBar) {
        this.progressBar = progressBar;
        this.onErrorListener = onErrorListener;
        this.onSucessListener = onSucessListener;
        doRequest(context, call);
    }

    public void request(Context context, Call<T> call, OnSucess onSucessListener, OnErrorServer onErrorServer, ProgressBar progressBar) {
        this.progressBar = progressBar;
        this.onErrorServer = onErrorServer;
        this.onSucessListener = onSucessListener;
        doRequest(context, call);
    }

    private void progressBar(boolean isVisible) {
        if (progressBar != null) {
            progressBar.setVisibility(isVisible ? View.VISIBLE : View.GONE);
        }
        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setRefreshing(isVisible);
        }

    }

    public void setShimmerFrameLayout(boolean animation) {
        if(shimmer != null) {
            if (animation) {
                shimmer.startShimmerAnimation();
            } else {
                shimmer.stopShimmerAnimation();
            }
        }
    }

    private void txtNothing(boolean isVisible) {
        if (txtNothing != null) {
            txtNothing.setVisibility(isVisible ? View.VISIBLE : View.GONE);
        }
    }

    private void setProgressdialog(boolean isVisible) {
        if (isVisible) {
            progressDialog = new ProgressDialog(mContext);
            progressDialog.setTitle(mContext.getString(R.string.label_wait));
            progressDialog.setMessage(mContext.getString(R.string.label_loading));
            progressDialog.setCancelable(false);
            progressDialog.show();

        } else {
            if (progressDialog != null) {
                progressDialog.dismiss();
            }
        }
    }

    private void doRequest(Context context, Call<T> call) {
        progressBar(true);
        setShimmerFrameLayout(true);
        setContext(context);
        mCall = call;
        if (ConnectionChecker.checkConnection(context)) {
            call.enqueue(this);
        } else {
            progressBar(false);
            setShimmerFrameLayout(false);
        }

    }

    private void doRequestBlock(Context context, Call<T> call) {
        setContext(context);
        mCall = call;
        setProgressdialog(true);
        if (ConnectionChecker.checkConnection(context)) {
            call.enqueue(this);
        } else {
            setProgressdialog(false);
        }

    }

    private void requestLastCall() {
        progressBar(true);
        setShimmerFrameLayout(true);
        setProgressdialog(isBlocked);
        if (mContext != null && ConnectionChecker.checkConnection(mContext)) {
            Call<T> newCall = mCall.clone();
            newCall.enqueue(this);
        } else {
            progressBar(false);
            setShimmerFrameLayout(false);
            setProgressdialog(false);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onResponse(@NonNull Call<T> call, @NonNull Response<T> response) {
        if (response.isSuccessful()) {
            txtNothing(false);
            if (onSucessListener != null) {
                onSucessListener.onSucessResponse(response);
            }
        } else {
            txtNothing(true);
            if (onErrorListener != null) {
                onErrorListener.onErrorResponse(ErrorResponse.getResponseError(response.errorBody(), response.code()));
            } else if (onErrorServer != null) {
                onErrorServer.onErrorResponse(response.errorBody());
            } else {
                errorResponse(response);

            }

        }
        progressBar(false);
        setShimmerFrameLayout(false);
        setProgressdialog(false);

    }

    @Override
    public void onFailure(@NonNull Call<T> call, @NonNull Throwable t) {
        progressBar(false);
        setShimmerFrameLayout(false);
        setProgressdialog(false);
        txtNothing(true);
        if (onErrorListener != null) {
            onErrorListener.onErrorResponse(null);
        } else if (onErrorServer != null) {
            onErrorServer.onErrorResponse(null);
        } else {
            errorResponse(null);
        }
        try {
            Log.e(t.getMessage());
        } catch (Exception e) {
            e.getMessage();
        }
    }

    private void errorResponse(Response<T> response) {
        if (response == null) {
            onError(ErrorResponse.getGsonParseError());
        } else if (response.code() >= Constants.REQUEST_FAIL) {
            onError(ErrorResponse.getExceptionFail(response.errorBody(), response.code()));
        } else if (response.code() == Constants.REQUEST_UPDATE) {
            onError(ErrorResponse.getUpdateApplicationError());
        } else if (response.code() == Constants.REQUEST_EXPIRED) {
            onError(ErrorResponse.setSessionError(response.errorBody(), response.code()));
        } else {
            onError(ErrorResponse.getResponseError(response.errorBody(), response.code()));
        }
    }


    private void onError(ErrorResponse error) {
        DialogCustomUtil.OnItemClick onItemClickRepeat = new DialogCustomUtil.OnItemClick() {
            @Override
            public void onItemClick(View view) {
                requestLastCall();
            }
        };
        if (error.getCode() != null) {
            switch (error.getCode()) {
                case ErrorResponse.UPDATE_VERSION:
                    DialogSingleton.getInstance().dialog(getContext(), mContext.getString(R.string.label_update), error.getMessage(), false, null);
                    break;
                case ErrorResponse.UNEXPECTED:
                    DialogSingleton.getInstance().dialog(getContext(), mContext.getString(R.string.label_error), error.getMessageServer(), true, onItemClickRepeat);
                    break;
                case ErrorResponse.NETWORK_DISABLE:
                    DialogSingleton.getInstance().dialog(getContext(), mContext.getString(R.string.label_connection), error.getMessage(), false, null);
                    break;
                case ErrorResponse.SESSION:
                    DialogSingleton.getInstance().dialog(getContext(), mContext.getString(R.string.label_session), error.getMessage(), new DialogCustomUtil.OnItemClick() {
                        @Override
                        public void onItemClick(View view) {
                            //clearInfo
                        }
                    });
                    break;
                case ErrorResponse.FAIL:
                    DialogSingleton.getInstance().dialog(getContext(), mContext.getString(R.string.label_fail), error.getMessage(), true, onItemClickRepeat);
                    break;
                default:
                    Snackbar.make(getContext(), error.getMessage());
                    break;
            }
        } else {
            Snackbar.make(getContext(), error.getMessage());
        }
    }


}

