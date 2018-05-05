package br.udacity.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import br.udacity.R;
import br.udacity.components.OpenSansBoldTextView;
import br.udacity.models.response.Ingredient;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.CardChangesViewHolder> {

    private List<Ingredient> cardViewListItems;
    private Context context;



    public IngredientsAdapter(List<Ingredient> palettes) {
        this.cardViewListItems = new ArrayList<Ingredient>();
        this.cardViewListItems.addAll(palettes);

    }

    @Override
    public CardChangesViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.item_ingredients, viewGroup, false);
        context = viewGroup.getContext();
        return new CardChangesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final CardChangesViewHolder holder, int i) {

        if (cardViewListItems.get(holder.getAdapterPosition()) != null) {
            final Ingredient item = cardViewListItems.get(holder.getAdapterPosition());
            try {
                holder.tvIngredient.setText(String.format(context.getString(R.string.str_ingredient), item.getIngredient(),
                        item.getQuantity(), item.getMeasure()));

            } catch (Exception e) {
                e.getMessage();
            }
        }
    }

    @Override
    public int getItemCount() {
        if (cardViewListItems != null)
            return cardViewListItems.size();
        else return 0;

    }

    class CardChangesViewHolder extends RecyclerView.ViewHolder {
        private OpenSansBoldTextView tvIngredient;

        private CardChangesViewHolder(View rootView) {
            super(rootView);
            tvIngredient = (OpenSansBoldTextView) rootView.findViewById(R.id.tv_ingredient);
        }
    }


}