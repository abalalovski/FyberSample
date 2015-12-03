package com.twodwarfs.fyber.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.twodwarfs.fyber.R;
import com.twodwarfs.fyber.model.BaseModel;
import com.twodwarfs.fyber.model.Offer;
import com.twodwarfs.fyber.model.OffersWrapper;

/**
 * Created by Aleksandar Balalovski Balalovski
 * <p/>
 * Offers Adapter class that implements the logics of multi-type RecyclerView Adapter.
 */

public class OffersAdapter extends RecyclerView.Adapter<OffersAdapter.ViewHolder> {

    /**
     * Main holder of all data
     **/
    private OffersWrapper mData = new OffersWrapper();

    /**
     * Initial unmodified data that serves as helper
     **/
    private Context mContext;

    /**
     * My implementation of OnItemClickListener interface.
     * In our case we don't need this.
     */
    public interface IOnItemClick {
        void onItemClick(View v, int position);
    }

    static IOnItemClick mOnItemClickListener;

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public View mRootView;

        public ViewHolder(View v) {
            super(v);
            mRootView = v;
            mRootView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(v, getPosition());
            }
        }
    }

    public OffersAdapter() {
    }

    public void setOnItemClickListener(IOnItemClick listener) {
        mOnItemClickListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View v = inflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Offer offer = mData.getOffers().get(position);

        ImageView imageView = (ImageView) holder.mRootView.findViewById(R.id.imageView_thumb);
        Glide.with(mContext).load(offer.getThumb().getHighRes()).into(imageView);

        TextView textViewName = (TextView) holder.mRootView.findViewById(R.id.textView_title);
        textViewName.setText(offer.getTitle());

        TextView textViewTeaser = (TextView) holder.mRootView.findViewById(R.id.textView_teaser);
        textViewTeaser.setText(offer.getTeaser());

        TextView textViewPayout = (TextView) holder.mRootView.findViewById(R.id.textView_payout);
        textViewPayout.setText("Payout: " + offer.getPayout());
    }

    public BaseModel getItem(int position) {
        return mData.getOffers().get(position);
    }

    @Override
    public int getItemCount() {
        return mData.getOffers().size();
    }

    public void setOffers(OffersWrapper items) {
        mData = items;
        notifyDataSetChanged();
    }
}
