package br.udacity.utils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

/**
 * Created by Jeferson on 13/07/2017.
 */

public  class CastListUtil<T> {
    @SuppressWarnings("unchecked")
    public List<T> setItems(Response response) {
        List<?> result = (List<?>) response.body();
        List<T> tempList = new ArrayList<>();
        for (Object object : result) {
                tempList.add((T) object);
        }
        return tempList;
    }
}
