package br.udacity.connection;

import android.content.Context;

/**
 * Created by Jeferson on 14/07/2017.
 */

public abstract class BaseImpl<T> {

    protected abstract Context getMyContext();

    protected abstract T getApiService();

}

