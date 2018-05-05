package br.udacity.connection.interfaces;

import br.udacity.models.ErrorResponse;

/**
 * Created by Jeferson on 01/05/2017.
 */

public interface OnError<T> {
    public void onErrorResponse(ErrorResponse response);
}
