package br.udacity.controllers.detailImpl;

import android.content.Context;

import br.udacity.connection.interfaces.RetrofitInterface;
import br.udacity.controllers.mainImpl.MainImpl;

//Controller principal
public class DetailImpl extends MainImpl implements RetrofitInterface {

    public DetailImpl(Context context) {
        super(context);
    }
}
