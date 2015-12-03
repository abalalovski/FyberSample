package com.twodwarfs.fyber.ui.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.twodwarfs.fyber.R;
import com.twodwarfs.fyber.model.OffersWrapper;
import com.twodwarfs.fyber.net.FyberApi;
import com.twodwarfs.fyber.ui.adapters.OffersAdapter;
import com.twodwarfs.fyber.utils.Logger;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Aleksandar Balalovski Balalovski
 * <p/>
 * Main Fragment, all logics is here.
 */

public class OffersFragment extends Fragment {

    private ProgressDialog mProgressDialog;

    private RecyclerView mRecyclerView;
    private OffersAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public OffersFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_offers, container, false);

        setHasOptionsMenu(true);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView_images);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new OffersAdapter();

        initData();
        mRecyclerView.setAdapter(mAdapter);

        return rootView;
    }

    private void initData() {
        mProgressDialog = ProgressDialog.show(getActivity(),
                "", "Loading");

        Call<OffersWrapper> call = FyberApi.getInstance().getOffers();

        call.enqueue(new Callback<OffersWrapper>() {
            @Override
            public void onResponse(Response<OffersWrapper> response, Retrofit retrofit) {

                mAdapter.setOffers(response.body());

                if (mProgressDialog != null) {
                    mProgressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Logger.doLogException(t);
            }
        });
    }
}
