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
import br.udacity.models.response.Step;

/**
 * Created by Jeferson on 07/08/2016.
 */
public class StepsAdapter  extends RecyclerView.Adapter<StepsAdapter.CardChangesViewHolder> {

    private List<Step> cardViewListItems;
    private Context context;
    private OnItemClick onItemClick;


    public StepsAdapter(List<Step> palettes, OnItemClick onItemClick) {
        this.cardViewListItems = new ArrayList<Step>();
        this.cardViewListItems.addAll(palettes);
        this.onItemClick = onItemClick;

    }

    @Override
    public CardChangesViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.item_step, viewGroup, false);
        context = viewGroup.getContext();
        return new CardChangesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final CardChangesViewHolder holder, int i) {

        if (cardViewListItems.get(holder.getAdapterPosition()) != null) {
            final Step cardViewListItem = cardViewListItems.get(holder.getAdapterPosition());
            try {
                if(holder.getAdapterPosition() != 0) {
                    holder.tvTitle.setText(String.format(context.getString(R.string.str_step), holder.getAdapterPosition(), cardViewListItem.getShortDescription()));
                }else{
                    holder.tvTitle.setText(cardViewListItem.getShortDescription());
                }
                holder.tvTitle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onItemClick.onItemClick(v, cardViewListItems, holder.getAdapterPosition());
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


        private OpenSansBoldTextView tvTitle;


        private CardChangesViewHolder(View rootView) {
            super(rootView);
            tvTitle = (OpenSansBoldTextView) rootView.findViewById(R.id.tv_title);
        }
    }

    public interface OnItemClick {
         void onItemClick(View view, List<Step> items, int position);
    }


}