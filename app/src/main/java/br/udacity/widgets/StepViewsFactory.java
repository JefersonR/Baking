package br.udacity.widgets;

import android.content.Context;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.util.List;

import br.udacity.R;
import br.udacity.models.response.Step;

public class StepViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    private Context mContext;
    private List<Step> steps;

    public StepViewsFactory(Context applicationContext) {
        mContext = applicationContext;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {
        steps = IngredientsListWidgetProvider.getSteps();
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return steps == null ? 0 : steps.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        Step step = steps.get(position);
        RemoteViews row = new RemoteViews(mContext.getPackageName(), R.layout.item_ingredients_widget);
        row.setTextViewText(R.id.tv_ingredient, String.format(mContext.getString(R.string.str_step_simple), position, step.getShortDescription()));
        return (row);
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
