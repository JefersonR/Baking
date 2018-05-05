package br.udacity.widgets;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import java.util.List;

import br.udacity.connection.interfaces.OnSucess;
import br.udacity.controllers.mainImpl.MainImpl;
import br.udacity.models.response.BakingResponse;
import retrofit2.Response;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * helper methods.
 */
public class BakingRecipeIntentService extends IntentService {

    public static final String RECIPES_LIST = "br.udacity.widgets.action.RECIPES_LIST";

    public static void startActionFetchRecipes(Context context) {
        Intent intent = new Intent(context, BakingRecipeIntentService.class);
        intent.setAction(RECIPES_LIST);
        context.startService(intent);
    }

    public BakingRecipeIntentService() {
        super("BakingRecipeIntentService");
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (RECIPES_LIST.equals(action)) {
                request();
            }
        }
    }
    private void request() {
       new MainImpl(this).getBaking(new OnSucess() {
            @Override
            public void onSucessResponse(Response response) {
                if (response != null && response.body() != null) {
                    List<BakingResponse> bakingResponses = (List<BakingResponse>) response.body();
                    if (bakingResponses != null && !bakingResponses.isEmpty()) {
                        IngredientsListWidgetProvider.setRecipeList(bakingResponses);
                        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(getApplicationContext());
                        int[] appWidgetsId = appWidgetManager.getAppWidgetIds(new ComponentName(getApplicationContext(), IngredientsListWidgetProvider.class));
                        IngredientsListWidgetProvider.updateAppWidgets(getApplicationContext(), appWidgetManager, appWidgetsId, 0);
                    }
                }
            }
        }, null,null, null);
    }
}
