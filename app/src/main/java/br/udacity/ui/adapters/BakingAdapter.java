package br.udacity.ui.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import br.udacity.R;
import br.udacity.components.OpenSansBoldTextView;
import br.udacity.models.response.BakingResponse;

/**
 * Created by Jeferson on 07/08/2016.
 */
public class BakingAdapter extends RecyclerView.Adapter<BakingAdapter.CardChangesViewHolder> {

    private List<BakingResponse> cardViewListItems;
    private Context context;
    private OnItemClick onItemClick;


    public BakingAdapter(List<BakingResponse> palettes, OnItemClick onItemClick) {
        this.cardViewListItems = new ArrayList<BakingResponse>();
        this.cardViewListItems.addAll(palettes);
        this.onItemClick = onItemClick;

    }

    @Override
    public CardChangesViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.item_recipe, viewGroup, false);
        context = viewGroup.getContext();
        return new CardChangesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final CardChangesViewHolder holder, int i) {

        if (cardViewListItems.get(holder.getAdapterPosition()) != null) {
            final BakingResponse cardViewListItem = cardViewListItems.get(holder.getAdapterPosition());
            try {

                holder.tvTitle.setText(cardViewListItem.getName());
                holder.cardItem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onItemClick.onItemClick(v, cardViewListItem, holder.getAdapterPosition());
                    }
                });


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

        private CardView cardItem;
        private OpenSansBoldTextView tvTitle;


        private CardChangesViewHolder(View rootView) {
            super(rootView);
            cardItem = (CardView) rootView.findViewById(R.id.card_item);
            tvTitle = (OpenSansBoldTextView) rootView.findViewById(R.id.tv_title);


        }
    }
    public interface OnItemClick {
         void onItemClick(View view, BakingResponse item, int position);
    }

}