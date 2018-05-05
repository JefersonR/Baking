package br.udacity.widgets;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.TaskStackBuilder;
import android.widget.RemoteViews;

import java.util.List;

import br.udacity.R;
import br.udacity.models.response.BakingResponse;
import br.udacity.models.response.Step;
import br.udacity.ui.activities.MainActivity;

/**
 * Implementation of App Widget functionality.
 */
public class IngredientsListWidgetProvider extends AppWidgetProvider {

    public static final String NEXT_RECIPE = "NEXT_RECIPE";

    private static List<BakingResponse> recipeList;
    private static int position = 0;

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId, int pos) {

        if (recipeList != null && !recipeList.isEmpty()) {
            position = pos;
            if (position == recipeList.size()) {
                position = 0;
            }
            if (position >= 0 && position < recipeList.size()) {
                BakingResponse recipe = recipeList.get(position);
                RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.ingredients_list_widget);
                Intent pedingIntent = new Intent(context, IngredientsListWidgetProvider.class);
                pedingIntent.setAction(NEXT_RECIPE);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, pedingIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                views.setTextViewText(R.id.tv_recipe_title, recipe.getName());
                views.setOnClickPendingIntent(R.id.tv_recipe_title, pendingIntent);

                // Set's up the listview
                Intent adapterIntent = new Intent(context, RecipeWidgetService.class);
                views.setRemoteAdapter(R.id.lv_ingredients, adapterIntent);

                // template to handle the click listener for each item
                Intent clickIntentTemplate = new Intent(context, MainActivity.class);
                PendingIntent clickPendingIntentTemplate = TaskStackBuilder.create(context)
                        .addNextIntentWithParentStack(clickIntentTemplate)
                        .getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
                views.setPendingIntentTemplate(R.id.lv_ingredients, clickPendingIntentTemplate);
                // Instruct the widget manager to update the widget
                appWidgetManager.updateAppWidget(appWidgetId, views);
            }

        }
    }

    public static void updateAppWidgets(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds, int position) {
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId, position);
        }
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        BakingRecipeIntentService.startActionFetchRecipes(context);
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    public static List<Step> getSteps() {
        return recipeList.get(position).getSteps();
    }

    public static void setRecipeList(List<BakingResponse> bakingResponseList) {
        recipeList = bakingResponseList;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        int[] appWidgetsId = appWidgetManager.getAppWidgetIds(new ComponentName(context, IngredientsListWidgetProvider.class));
        if (intent != null && intent.getAction() != null && intent.getAction().equals(NEXT_RECIPE)) {
            position++;
            updateAppWidgets(context, appWidgetManager, appWidgetsId, position);
            // refresh all your widgets
            AppWidgetManager mgr = AppWidgetManager.getInstance(context);
            ComponentName cn = new ComponentName(context, IngredientsListWidgetProvider.class);
            mgr.notifyAppWidgetViewDataChanged(mgr.getAppWidgetIds(cn), R.id.lv_ingredients);
        }
        super.onReceive(context, intent);
    }
}

