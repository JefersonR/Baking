package br.udacity.connection.interfaces;

import okhttp3.ResponseBody;

/**
 * Created by Jeferson on 01/05/2017.
 */

public interface OnErrorServer<T> {
    public void onErrorResponse(ResponseBody errorBody);
}
