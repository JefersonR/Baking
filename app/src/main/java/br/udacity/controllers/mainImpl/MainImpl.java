package br.udacity.controllers.mainImpl;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import br.udacity.connection.BaseImpl;
import br.udacity.connection.GenericRestCallBack;
import br.udacity.connection.interfaces.OnError;
import br.udacity.connection.interfaces.OnSucess;
import br.udacity.connection.interfaces.RetrofitInterface;
import br.udacity.models.response.BakingResponse;

//Controller principal
public class MainImpl extends BaseImpl implements RetrofitInterface {

    private Context context;
    public MainImpl(Context context) {
        this.context = context;
    }

    public void getBaking(OnSucess onSucessListener, OnError onError, View progress, TextView nothing){
        new GenericRestCallBack<List<BakingResponse>>().request(getMyContext(), getApiService().getBaking(),onSucessListener, onError, progress, nothing);
    }

    @Override
    protected Context getMyContext() {
        return context;
    }

    @Override
    protected MainEndpoint getApiService() {
        return retrofit.create(MainEndpoint.class);
    }
}
