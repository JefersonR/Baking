package br.udacity.controllers.mainImpl;

import java.util.List;

import br.udacity.models.response.BakingResponse;
import retrofit2.Call;
import retrofit2.http.GET;


/**
 * Created by jeferson on 22/11/15.
 */
public interface MainEndpoint {

    /*
    Request method and URL specified in the annotation
    Callback for the parsed response is the last parameter
    */

    @GET("android-baking-app-json")
    Call<List<BakingResponse>> getBaking();

}